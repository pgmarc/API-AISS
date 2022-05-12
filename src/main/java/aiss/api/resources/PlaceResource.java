package aiss.api.resources;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

import org.jboss.resteasy.spi.BadRequestException;
import org.jboss.resteasy.spi.NotFoundException;

import aiss.model.Place;
import aiss.model.Review;
import aiss.model.repository.MapPlaceRepository;
import aiss.model.repository.PlaceRepository;

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
	public Response getAll() {
		List<Place> places = new ArrayList<Place>(placeRepository.getAllPlaces());
		return Response.status(Status.OK).entity(places).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Response getPlace(@PathParam("id") Integer placeId) {
		Place place = placeRepository.getPlace(placeId);
		return Response.status(Status.OK).entity(place).build();
	}
	
	@GET
	@Path("/{id}/around")
	@Produces("application/json")
	public Response getPlacesAround(@PathParam("id") Integer placeId,
			@QueryParam("minRadius") Double minRadius, @QueryParam("maxRadius") Double maxRadius) {
		List<Place> placesInArea = List.copyOf(placeRepository.getPlacesOnRadius(placeId, minRadius, maxRadius));
		return Response.status(Status.OK).entity(placesInArea).build();
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addPlace(@Context UriInfo uriInfo, Place place) {
		
		if (place.getAddress() == null || "".equals(place.getAddress()))
			throw new BadRequestException("The address of a place must not be null");
		
		if (place.getId() !=null)
			throw new BadRequestException("The place id must not been given as a parameter.");

		placeRepository.addPlace(place);

		// Builds the response. Returns the place the has just been added.
		UriBuilder ub = uriInfo.getAbsolutePathBuilder(). path(this.getClass(), "getPlace");
		URI uri = ub.build(place.getId());
		ResponseBuilder response = Response.created(uri);
		response.entity(place);			
		return response.build();
	}
	
	@PUT
	@Path("/{id}")
	@Consumes("application/json")
	public Response updatePlace(@PathParam("id") Integer placeId,Place place) {
		Place oldPlace = placeRepository.getPlace(placeId);
		if (oldPlace == null)
			throw new NotFoundException("The place with id="+ place.getId() +" was not found");			
		
		if (place.getAddress() != null)
			oldPlace.setAddress(place.getAddress());
		
		if (place.getEmail() != null)
			oldPlace.setEmail(place.getEmail());
		
		if (place.getName() != null)
			oldPlace.setName(place.getName());
		
		if (place.getWebsite() != null)
			oldPlace.setWebsite(place.getWebsite());
		
		placeRepository.updatePlace(oldPlace);
		
		return Response.status(Status.NO_CONTENT).entity(oldPlace).build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response deletePlace(@PathParam("id") Integer placeId) {
		Place placeToBeRemoved = placeRepository.getPlace(placeId);
		if (placeToBeRemoved == null)
			throw new NotFoundException("The place with id=" + placeId + " was not found");
		else
			placeRepository.deletePlace(placeId);
		
		return Response.noContent().build();
	}
	
	//REVIEWS
	@GET
	@Path("/{id}/reviews/{reviewId}")
	@Produces("application/json")
	public Response getReview(@PathParam("id") Integer placeId,
			@PathParam("reviewId") Integer reviewId) {
		
		Place place = placeRepository.getPlace(placeId);
		Review review = placeRepository.getReview(placeId,reviewId);
		
		if(place.equals(null)) {
			throw new NotFoundException("The place with id: " + placeId + " was not found.");
		}
		if(review.equals(null)) {
			throw new NotFoundException("The review with id: " + reviewId + " was not found in the place.");
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
		if(place.equals(null))
			throw new NotFoundException("The place with id: " + placeId + " was not found.");
		
		if (review.getUsername()==null) review.setUsername("Anonymous");

		if (review.getDescription()==null) review.setDescription("");
		
		if (review.getRating() == null)
			throw new BadRequestException("The rating must not be null");
		
		if (review.getRating() < 0 || review.getRating() > 5)
			throw new BadRequestException("The rating must be a value between 0 and 5");
		
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
	public Response updateReview(@PathParam("id") Integer placeId,
			@PathParam("reviewId") Integer reviewId, Review review) {
		
		Place place = placeRepository.getPlace(placeId);
		
		if (place == null)
			throw new NotFoundException("The place with id="+ placeId +" was not found");		
		
		Review oldReview = placeRepository.getReview(placeId, reviewId);
		
		if (oldReview == null)
			throw new NotFoundException("The review with id="+ reviewId +
					" was not found in that place");
		
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
	public Response deleteReview(@PathParam("id") Integer placeId,
			@PathParam("reviewId") Integer reviewId) {
		
		Place place = placeRepository.getPlace(placeId);
		
		if (place == null)
			throw new NotFoundException("The place with id=" + placeId + " was not found");

		Review reviewToBeDeleted= placeRepository.getReview(placeId, reviewId);
		
		if(reviewToBeDeleted==null)
			throw new NotFoundException("The review with id=" + reviewId +
					" was not found in that place");
	
		placeRepository.deleteReview(placeId, reviewId);
		
		return Response.noContent().build();
	}
}
