package aiss.api.resources;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
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
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import aiss.exceptions.BadEntityRequestException;
import aiss.exceptions.EntityNotFoundException;
import aiss.model.Accomodation;
import aiss.model.AccomodationPayment;
import aiss.model.Place;
import aiss.model.PlaceCategory;
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
			@QueryParam("order") String orderValue,
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
		
		System.out.println(orderValue);
		if (orderValue != null) {
			places.sort(PlacesUtil.parseSort(orderValue));
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
	
	//Accommodations
	@GET
	@Path("/{placeId}/accommodation")
	@Produces("application/json")
	public Response getAccommodation(@PathParam("placeId") Integer placeId) {
		
		Place place = placeRepository.getPlace(placeId);
		
		if(place == null)
			throw new BadEntityRequestException("The place with id=" + placeId +" not found");
		
		Accomodation accomodation = place.getAccomodation();
		
		if(accomodation == null)
			throw new BadEntityRequestException("The place does not have an associated accommodation");
		
		return Response.status(Status.OK).entity(accomodation).build();
	}
	
	@POST
	@Path("/{placeId}/accommodation")
	@Consumes("application/json")
	@Produces("application/json")
	public Response addAccommodation(@Context UriInfo uriInfo, @PathParam("placeId") Integer placeId, 
			Accomodation accommodation) {
		
		Place place = placeRepository.getPlace(placeId);
		
		if(place == null)
			throw new BadEntityRequestException("The place with id=" + placeId +" not found");
		
		if(!place.getCategory().equals(PlaceCategory.ACCOMODATION))
			throw new BadEntityRequestException("Cannot add accommodation data to a place not classified "
					+ "as an accomodation");
		
		if(accommodation.getNumberOfRooms() == null)
			throw new BadEntityRequestException("An accomodation must have a number of rooms");
		
		if(accommodation.getNumberOfRooms() < 0)
			throw new BadEntityRequestException("The number of rooms in an accommodation must be "
					+ "greater than zero");
		
		if(accommodation.getType() == null)
			throw new BadEntityRequestException("The accommodation type must be specified");
		
		if(accommodation.getPayments() != null)
			throw new BadEntityRequestException("Accommodation payments must be added separately");
		accommodation.setPayments(new HashMap<>());
		
		System.out.println("Added with type: " + accommodation.getType());
		placeRepository.addAccomodation(placeId, accommodation);
		
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path("accommodation");
		URI uri = ub.build(placeId);
		ResponseBuilder response = Response.created(uri);
		response.entity(accommodation);			
		return response.build();
	}
	
	@PUT
	@Path("/{placeId}/accommodation")
	@Consumes("application/json")
	@Produces("application/json")
	public Response updateAccomodation(@Context UriInfo uriInfo, @PathParam("placeId") Integer placeId, Accomodation accommodation) {
		
		Place place = placeRepository.getPlace(placeId);
		
		if(place == null)
			throw new EntityNotFoundException("The place with id=" + placeId +" was not found");
		
		Accomodation oldAccommodation = place.getAccomodation();
		
		if(oldAccommodation == null)
			throw new BadEntityRequestException("Cannot update a nonexistent accomodation, please create "
					+ "an accommodation in the place first");
		
		if(accommodation.getPayments() != null)
			throw new BadEntityRequestException("Accommodation payments must be updated individually through their operations");
		
		if(accommodation.getNumberOfRooms() != null) {
			if(accommodation.getNumberOfRooms() < 0)
				throw new BadEntityRequestException("The number of rooms in an accommodation must be "
					+ "greater than zero");
			oldAccommodation.setNumberOfRooms(accommodation.getNumberOfRooms());
		}
		
		if(accommodation.getType() != null)
			oldAccommodation.setType(accommodation.getType());
		
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path("accommodation");
		URI uri = ub.build(placeId);
		ResponseBuilder response = Response.ok(uri);
		response.entity(place.getAccomodation());			
		return response.build();
	}
	
	@DELETE
	@Path("/{placeId}/accommodation")
	@Produces("application/json")
	public Response deleteAccommodation(@PathParam("placeId") Integer placeId) {
		Place place = placeRepository.getPlace(placeId);
		if (place == null)
			throw new EntityNotFoundException("The place with id=" + placeId + " was not found");
		
		placeRepository.deleteAccomodation(placeId);
		
		return Response.noContent().build();
	}
	
	
	//PAYMENTS
	@GET
	@Path("/{placeId}/accommodation/payment/{payId}")
	@Produces("application/json")
	public Response getAccommodationPayment(@PathParam("placeId") Integer placeId, 
			@PathParam("payId") Integer paymentId) {
		
		Place place = placeRepository.getPlace(placeId);
		
		if(place == null)
			throw new BadEntityRequestException("The place with id=" + placeId +" not found");
		
		Accomodation accommodation = place.getAccomodation();
		
		if(accommodation == null)
			throw new BadEntityRequestException("The place does not have an associated accommodation");
		
		AccomodationPayment payment = accommodation.getPayment(paymentId);
		
		return Response.status(Status.OK).entity(payment).build();
	}
	
	@POST
	@Path("/{placeId}/accommodation/payment")
	@Consumes("application/json")
	@Produces("application/json")
	public Response addAccommodationPayment(@Context UriInfo uriInfo, 
			@PathParam("placeId") Integer placeId, AccomodationPayment payment) {
		
		Place place = placeRepository.getPlace(placeId);
		
		if(place == null)
			throw new BadEntityRequestException("The place with id=" + placeId +" not found");
		
		Accomodation accommodation = place.getAccomodation();
			
		if(accommodation == null)
			throw new BadEntityRequestException("The place does not have any accommodation data to modify");
		
		if(payment.getDescription() == null || payment.getDescription().isEmpty())
			throw new BadEntityRequestException("The payment description must not be empty");
		
		if(payment.getMealService() == null)
			throw new BadEntityRequestException("A meal service must be specified");
		
		if(payment.getPrice() == null)
			throw new BadEntityRequestException("A price must be specified");
		
		if(payment.getPrice() < 0)
			throw new BadEntityRequestException("Price specified must be greater than zero");
		
		if(payment.getRoomType() == null)
			throw new BadEntityRequestException("A room type must be specified");
		
		if(payment.getPaymentPeriod() == null)
			throw new BadEntityRequestException("A payment period must be specified");
		
		placeRepository.addAccommodationPayment(placeId, payment);
		
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "getAccommodationPayment");
		URI uri = ub.build(placeId, payment.getId());
		ResponseBuilder response = Response.created(uri);
		response.entity(accommodation);			
		return response.build();
	}
	
	@PUT
	@Path("/{placeId}/accommodation/payment/{payId}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response updateAccomodationPayment(@PathParam("placeId") Integer placeId, 
			@PathParam("payId") Integer paymentId, AccomodationPayment payment) {
		
		Place place = placeRepository.getPlace(placeId);
		
		if(place == null)
			throw new BadEntityRequestException("The place with id=" + placeId +" not found");
		
		Accomodation accommodation = place.getAccomodation();
		
		if(accommodation == null)
			throw new BadEntityRequestException("The place does not have any accommodation data to modify");
		
		AccomodationPayment oldPayment = accommodation.getPayment(paymentId);
		
		if(oldPayment == null)
			throw new BadEntityRequestException("Payment with id " + paymentId + " not found");
		
		if(payment.getDescription() != null) {
			if(payment.getDescription().isEmpty())
				throw new BadEntityRequestException("Description of the payment cannot be empty");
			oldPayment.setDescription(payment.getDescription());
		}
		
		if(payment.getMealService() != null) 
			oldPayment.setMealService(payment.getMealService());
		
		if(payment.getPaymentPeriod() != null) 
			oldPayment.setPaymentPeriod(payment.getPaymentPeriod());
		
		if(payment.getPrice() != null)
			oldPayment.setPrice(payment.getPrice());
		
		if(payment.getRoomType() != null)
			oldPayment.setRoomType(payment.getRoomType());
		
		return Response.status(Status.NO_CONTENT).entity(payment).build();
	}
	
	@DELETE
	@Path("/{placeId}/accommodation/payment/{payId}")
	@Produces("application/json")
	public Response deleteAccommodationPayment(@PathParam("placeId") Integer placeId, 
			@PathParam("payId") Integer paymentId) {
		Place place = placeRepository.getPlace(placeId);
		if (place == null)
			throw new EntityNotFoundException("The place with id=" + placeId + " was not found");
		
		placeRepository.deleteAccommodationPayment(placeId, paymentId);
		
		return Response.noContent().build();
	}
	
	
	//REVIEWS
	@GET
	@Path("/{placeId}/reviews")
	@Produces("application/json")
	public Response getAllReviews(@PathParam("placeId") Integer placeId,
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
