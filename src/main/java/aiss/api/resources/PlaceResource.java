package aiss.api.resources;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;


import aiss.exceptions.BadEntityRequestException;
import aiss.exceptions.EntityNotFoundException;
import aiss.model.Place;
import aiss.model.Review;
import aiss.model.repository.MapPlaceRepository;
import aiss.model.repository.PlaceRepository;
import aiss.util.PlacesUtil;
import aiss.util.validation.PlaceCoordinatesValidation;

@Path("/places")
public class PlaceResource {
	
	// Singleton	
	private static PlaceResource placeResource = null;
	private PlaceRepository placeRepository;
	
	private PlaceResource() {
		this.placeRepository = MapPlaceRepository.getInstance();
	}
	
	public static PlaceResource getInstance() {
		if (placeResource == null) {
			placeResource = new PlaceResource();
		}
		return placeResource;
	}
	
	@GET
	@Produces("application/json")
	public Response getAllPlaces(@QueryParam("offset") Integer offset,
			@QueryParam("limit") Integer limit,
			@QueryParam("categories") String categories,
			@QueryParam("order") String sortValue,
			@QueryParam("placeId") Integer placeId,
			@QueryParam("minRadius") Double minRadius,
			@QueryParam("maxRadius") Double maxRadius) {
		
		offset = Optional.ofNullable(offset).orElse(0);
		limit = Optional.ofNullable(limit).orElse(10);
		
		if (limit <= 0 | offset < 0)
			throw new BadEntityRequestException("Invalid values for limit, offset. "
					+ "limit must be positive and greater than zero and offset must be postive");
		
		List<Place> places = new ArrayList<Place>(placeRepository.getAllPlaces());
		
		if (placeId != null && (minRadius != null || maxRadius != null)) {
			places = List.copyOf(placeRepository.getPlacesOnRadius(placeId, minRadius, maxRadius));
		}
		
		if (categories != null) {
			places = PlacesUtil.filterPlacesByCategory(places, categories);
		}
		
		System.out.println(sortValue);
		
		if (sortValue != null) {
			places.sort(PlacesUtil.parseSort(sortValue));
		}
		
		places = PlacesUtil.getPagination(places, limit, offset);
		return Response.status(Status.OK).entity(places).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Response getPlace(@PathParam("id") Integer placeId) {
		Place place = placeRepository.getPlace(placeId);
		
		if (place == null)
			throw new EntityNotFoundException("Place with id=" + placeId + " not found");
		
		return Response.status(Status.OK).entity(place).build();
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addPlace(@Context UriInfo uriInfo, Place place) {

		if (place.getId() !=null)
			throw new BadEntityRequestException("The place id must not been given as a parameter.");

		if (place.getName() == null || place.getName().isEmpty())
			throw new BadEntityRequestException("The place must have a name or alias to identify it");
		
		if (place.getAddress() == null || place.getAddress().isEmpty())
			throw new BadEntityRequestException("The address of a place must not be null"
					+ " (A place has to be located somewhere)");
		
		if (place.getLocation() == null)
			throw new BadEntityRequestException("The place must have coordinates");
		
		if (!PlaceCoordinatesValidation.validCoordinates(place.getLocation()))
			throw new BadEntityRequestException("Invalid values for latitude and longitude."
			+ " Latitude should be within [-90, 90]  and longitude within [-180, 180]");
		

		placeRepository.addPlace(place);

		UriBuilder ub = uriInfo.getAbsolutePathBuilder(). path(this.getClass(), "getPlace");
		URI uri = ub.build(place.getId());
		ResponseBuilder response = Response.created(uri);
		response.entity(place);			
		return response.build();
	}
	
	@PUT
	@Path("/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response updatePlace(@PathParam("id") Integer placeId, Place place) {
		Place oldPlace = placeRepository.getPlace(placeId);
		
		if (oldPlace == null)
			throw new EntityNotFoundException("The place with id=" + place.getId() +" not found");
		
		if (place.getAddress() != null && (place.getAddress().isEmpty() 
				|| place.getAddress().isBlank()))
			throw new BadEntityRequestException("A place must have an address."
					+ " a place address MUST NOT be empty or blank");
		
		if (place.getAddress() != null)
			oldPlace.setAddress(place.getAddress());
		
		if (place.getEmail() != null)
			oldPlace.setEmail(place.getEmail());
		
		if (place.getCategory() != null)
			oldPlace.setCategory(place.getCategory());
		
		if (place.getName() != null && (place.getName().isEmpty() || place.getName().isBlank()))
			throw new BadEntityRequestException("A place must have a name."
					+ " a place name MUST NOT be empty or blank");
			
		if (place.getName() != null)
			oldPlace.setName(place.getName());
		
		if (place.getWebsite() != null)
			oldPlace.setWebsite(place.getWebsite());
		
		if (place.getReviews() != null)
			throw new BadEntityRequestException("Reviews must not be initialized again"
					+ " when updating a place. "
					+ "If you want to add reviews to a place GO TO POST /places/{placeId}/reviews");
				
		if (place.getAccomodation() != null)
			throw new BadEntityRequestException("Accomodation must not be initialized"
					+ " when creating a place (Not the right operation)");
		

		placeRepository.updatePlace(oldPlace);
		
		return Response.status(Status.NO_CONTENT).entity(oldPlace).build();
	}
	
	@DELETE
	@Path("/{id}")
	@Produces("application/json")
	public Response deletePlace(@PathParam("id") Integer placeId) {
		Place placeToBeRemoved = placeRepository.getPlace(placeId);
		if (placeToBeRemoved == null)
			throw new EntityNotFoundException("The place with id=" + placeId + " was not found");
		
		placeRepository.deletePlace(placeId);
		
		return Response.noContent().build();
	}
	
	//REVIEWS
	@GET
	@Path("/{id}/reviews")
	@Produces("application/json")
	public Response getAllReviews(@PathParam("id") Integer placeId,
			@QueryParam("rating")Double rating,
			@QueryParam("word")String word) {
		Collection<Review> reviews= placeRepository.getAllReviews(placeId);
		Place place= placeRepository.getPlace(placeId);
		
		if (place == null)
			throw new EntityNotFoundException("The place with id=" +placeId+ " was not found");	
		
		if(reviews == null || reviews.isEmpty())
			throw new EntityNotFoundException("This place has no reviews yet");

		return Response.status(Status.OK).entity(reviews).build();
	}

	@GET
	@Path("/{id}/reviews/{reviewId}")
	@Produces("application/json")
	public Response getReview(@PathParam("id") Integer placeId,
			@PathParam("reviewId") Integer reviewId) {
		Place place = placeRepository.getPlace(placeId);
		Review review = placeRepository.getReview(placeId, reviewId);
		if (place == null) {
			throw new EntityNotFoundException("The place with id=" +placeId+ " was not found");
		}
		
		if (review == null) {
			throw new EntityNotFoundException("The review with id=" +reviewId+ " was not found");
		}
		return Response.status(Status.OK).entity(review).build();
	}
	
	
	@POST
	@Path("/{id}/reviews")
	@Consumes("application/json")
	@Produces("application/json")
	public Response addReview(@PathParam("id") Integer placeId, @Context UriInfo uriInfo,
			Review review) {
		
		Place place = placeRepository.getPlace(placeId);
		
		if(place == null)
			throw new EntityNotFoundException("The place with id=" +placeId+ " was not found");
		
		if (review.getId() != null) 
			throw new BadEntityRequestException("The review id must not be included as a parameter");
		
		if (review.getUsername() == null) review.setUsername("Anonymous");

		if (review.getDescription() == null) review.setDescription("No description");
		
		if (review.getRating() == null)
			throw new BadEntityRequestException("The review rating must be included as a parameter");
		
		if (review.getRating() < 0 || review.getRating() > 5)
			throw new BadEntityRequestException("The review rating must be between 0 and 5");
		
		placeRepository.addReview(placeId, review); 

		// Builds the response. Returns the review the has just been added.
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "getReview");
		URI uri = ub.build(place.getId(),review.getId());
		ResponseBuilder resp = Response.created(uri);
		resp.entity(review);			
		return resp.build();
	}
	
	@PUT
	@Path("/{id}/reviews/{reviewId}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response updateReview(@PathParam("id") Integer placeId,
			@PathParam("reviewId") Integer reviewId, Review review) {
		
		Place place = placeRepository.getPlace(placeId);
		Review oldReview = placeRepository.getReview(placeId, reviewId);
		
		if (place == null) 
			throw new EntityNotFoundException("The place with id=" +placeId+ " was not found");
		
		if (oldReview == null)
			throw new EntityNotFoundException("The review with id=" +reviewId+ " was not found");

		
		if(review.getRating() < 0 || review.getRating() > 5)
			throw new BadEntityRequestException("The rating must be between 0 and 5");
		
		if (review.getUsername() != null)
			oldReview.setUsername(review.getUsername());
	
		if (review.getDescription() != null)
			oldReview.setDescription(review.getDescription());
	
		if (review.getRating() != null)
			oldReview.setRating(review.getRating());
	
		oldReview.setDate(LocalDateTime.now());
	
		placeRepository.updateReview(placeId, oldReview);
	
		return Response.status(Status.NO_CONTENT).entity(oldReview).build();
	}
	
	@DELETE
	@Path("/{id}/reviews/{reviewId}")
	@Produces("application/json")
	public Response deleteReview(@PathParam("id") Integer placeId,
			@PathParam("reviewId") Integer reviewId) {
		
		Place place = placeRepository.getPlace(placeId);
		
		if (place == null)
			throw new EntityNotFoundException("The place with id=" + placeId + " was not found");
		
		Review reviewToBeDeleted= placeRepository.getReview(placeId, reviewId);
		
		if(reviewToBeDeleted == null)
			throw new EntityNotFoundException("The review with id=" +reviewId+ " was not found");
		
		placeRepository.deleteReview(placeId, reviewId);
		return Response.noContent().build();
	}
}
