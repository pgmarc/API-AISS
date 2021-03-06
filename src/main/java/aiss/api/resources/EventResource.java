package aiss.api.resources;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
import aiss.model.Event;
import aiss.model.Place;
import aiss.model.Review;
import aiss.model.repository.EventRepository;
import aiss.model.repository.MapEventRepository;
import aiss.model.repository.MapPlaceRepository;
import aiss.util.EventsUtil;
import aiss.util.Sorting;
import aiss.util.validation.DateValidation;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;



@Path("/events")
public class EventResource {
	
	// Singleton	
	private static EventResource eventResource = null;
	private EventRepository eventRepository;
	
	private EventResource() {
		this.eventRepository = MapEventRepository.getInstance();
	}
	
	public static EventResource getInstance() {
		if (eventResource == null) {
			eventResource = new EventResource();
		}
		return eventResource;
	}
	
	@GET
	@Produces("application/json")
	public Response getAllEvents(@QueryParam("initialDate") String initialDateString,
			@QueryParam("finalDate") String finalDateString,
			@QueryParam("order") String orderValue) {
		
		List<Event> events = new ArrayList<Event>(eventRepository.getAllEvents());
		
		String currentDateString = DateValidation.currentDateFormated();
		String currentDatePlusMonthString = DateValidation.currentDatePlusMonthFormated();
		
		if (initialDateString != null && !DateValidation.validDate(initialDateString))
			throw new BadEntityRequestException("Invalid format for initialDate");
		
		if (finalDateString != null && !DateValidation.validDate(finalDateString))
			throw new BadEntityRequestException("Invalid format for finalDate");
		
		String initialDate = Optional.ofNullable(initialDateString).orElse(currentDateString);
		String finalDate = Optional.ofNullable(finalDateString).orElse(currentDatePlusMonthString);
		
		LocalDate minDate = DateValidation.parseLocalDate(initialDate);  
		LocalDate maxDate = DateValidation.parseLocalDate(finalDate); 
	
		events = EventsUtil.getEventsInDateRange(events, minDate, maxDate);
		
		if (orderValue != null)
			events.sort(Sorting.parseEventSort(orderValue));
		
		return Response.status(Status.OK).entity(events).build(); 
	}
	
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Response getEvent(@PathParam("id") Integer eventId) {
		Event event = eventRepository.getEvent(eventId);
		
		if (event == null)
			throw new EntityNotFoundException("The event with id=" + eventId + " was not found");
			
		return Response.status(Status.OK).entity(event).build();
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addEvent(@Context UriInfo uriInfo, Event event) {
				
		if (event.getId() != null)
			throw new BadEntityRequestException("The event id must not been given as a parameter.");
		
		if (event.getName() == null || event.getName().isEmpty())
			throw new BadEntityRequestException("An event must have a name");
		
		if (event.getPrice() == null)
			throw new BadEntityRequestException("An event must have a price");
		
		if (event.getPrice() != null && event.getPrice() < 0)
			throw new BadEntityRequestException("Negative price error. The price must be greater than or equal to zero");
		
		if (event.getLocalDateTime() == null)
			throw new BadEntityRequestException("The date of an event must not be null");
		
		if (DateValidation.isBeforeCurrentDate(event.getLocalDateTime()))
			throw new BadEntityRequestException("Cannot create events before the current date");
		
		if (event.getContactEmail() == null || event.getContactEmail().isEmpty())
			throw new BadEntityRequestException("The contact email of an event must have a value.");
		
		if (event.getOrganizers() == null || event.getOrganizers().isEmpty())
			throw new BadEntityRequestException("The organizers of an event must not be null,"
					+ " has to be managed by someone");
		

		eventRepository.addEvent(event);

		UriBuilder ub = uriInfo.getAbsolutePathBuilder(). path(this.getClass(), "getEvent");
		URI uri = ub.build(event.getId());
		ResponseBuilder response = Response.created(uri);
		response.entity(event);			
		return response.build();
	}
	
	@PUT
	@Path("/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response updateEvent(@Context UriInfo uriInfo,
			@PathParam("id") Integer eventId, Event event) {
		
		Event oldEvent = eventRepository.getEvent(eventId);
		
		if (oldEvent == null)
			throw new EntityNotFoundException("The event with id="+ eventId +" was not found");			
		
		if (event.getName() != null && event.getName().isEmpty())
			throw new BadEntityRequestException("An event must have a name");
		
		if (event.getName() != null)
			oldEvent.setName(event.getName());
		
		
		if (event.getContactEmail() != null && event.getContactEmail().isEmpty())
			throw new BadEntityRequestException("An event must have a contact email");
		
		if (event.getContactEmail() != null)
			oldEvent.setContactEmail(event.getContactEmail());
		
		
		if (event.getPrice() != null && event.getPrice() < 0)
			throw new BadEntityRequestException("Cannot update event price."
					+ " The price must be greater than or equal to zero");
		
		if (event.getPrice() != null)
			oldEvent.setPrice(event.getPrice());
		
		
		if (event.getLocalDateTime() != null && DateValidation.isBeforeCurrentDate(event.getLocalDateTime()))
			throw new BadEntityRequestException("Cannot update an event before the current date");

		if (event.getLocalDateTime() != null)
			oldEvent.setDate(event.getLocalDateTime());
		
		
		if (event.getOrganizers() != null && event.getOrganizers().isEmpty())
			throw new BadEntityRequestException("An event must have someone that manages the event");
		
		if (event.getOrganizers() != null)
			oldEvent.setOrganizers(event.getOrganizers());
		
		
		eventRepository.updateEvent(oldEvent);
		
		UriBuilder ub = uriInfo.getAbsolutePathBuilder();
		URI uri = ub.build();
		ResponseBuilder response = Response.ok(uri);
		response.entity(oldEvent);			
		return response.build();
	}
	
	@DELETE
	@Path("/{id}")
	@Produces("application/json")
	public Response deleteEvent(@PathParam("id") Integer eventId) {
		Event eventToBeRemoved = eventRepository.getEvent(eventId);
		if (eventToBeRemoved == null)
			throw new EntityNotFoundException("The event with id=" + eventId + " was not found");
	
		eventRepository.deleteEvent(eventId);
		
		return Response.noContent().build();
	}
	
	//REVIEWS
	@GET
	@Path("/{eventId}/reviews")
	@Produces("application/json")
	public Response getAllReviews(@PathParam("eventId") Integer eventId) {
		Collection<Review> reviews= eventRepository.getAllReviews(eventId);
		Event event= eventRepository.getEvent(eventId);
		
		if (event == null)
			throw new EntityNotFoundException("The event with id=" +eventId+ " was not found");	
		
		if(reviews == null || reviews.isEmpty())
			throw new EntityNotFoundException("This event has no reviews yet");

		return Response.status(Status.OK).entity(reviews).build();
	}
	
	@GET
	@Path("/{id}/reviews/{reviewId}")
	@Produces("application/json")
	public Review getReview(@PathParam("id") Integer eventId,
			@PathParam("reviewId") Integer reviewId) {
		
		Event event = eventRepository.getEvent(eventId);
		Review review = eventRepository.getReview(eventId,reviewId);
		
		if(event == null)
			throw new EntityNotFoundException("The event with id: " + eventId + " was not found.");
		
		if(review == null)
			throw new EntityNotFoundException("The review with id: " + reviewId 
					+ " was not found in the event.");
		
		return review;
	}
	
	
	@POST
	@Path("/{id}/reviews")
	@Consumes("application/json")
	@Produces("application/json")
	public Response addReview(@PathParam("id") Integer eventId, @Context UriInfo uriInfo,
			Review review) {
		
		Event event = eventRepository.getEvent(eventId);
		
		if(event == null)
			throw new EntityNotFoundException("The event with id: " + eventId + " was not found.");
		
		if (review.getId() != null)
			throw new BadEntityRequestException("The review id must not been given as a parameter.");
		
		if (review.getUsername() == null) review.setUsername("Anonymous");

		if (review.getDescription() == null) review.setDescription("");
		
		if (review.getRating() == null)
			throw new BadEntityRequestException("The rating must be given as a parameter");
		
		if (review.getRating() < 0 || review.getRating() > 5)
			throw new BadEntityRequestException("The rating must be a value between 0 and 5");
		
		eventRepository.addReview(eventId, review);

		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "getReview");
		URI uri = ub.build(event.getId(), review.getId());
		ResponseBuilder resp = Response.created(uri);
		resp.entity(review);			
		return resp.build();
	}
	
	@PUT
	@Path("/{id}/reviews/{reviewId}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response updateReview(@Context UriInfo uriInfo,@PathParam("id") Integer eventId,
			@PathParam("reviewId") Integer reviewId, Review review) {
		
		Event event = eventRepository.getEvent(eventId);
		
		if (event == null)
			throw new EntityNotFoundException("The event with id="+ eventId +" was not found");		
		
		Review oldReview = eventRepository.getReview(eventId, reviewId);
		
		if (oldReview == null)
			throw new EntityNotFoundException("The review with id="+ reviewId +
					" was not found in that event");
		
		if (review.getUsername() != null)
			oldReview.setUsername(review.getUsername());
		
		if (review.getDescription() != null)
			oldReview.setDescription(review.getDescription());
		
		if (review.getRating() != null)
			oldReview.setRating(review.getRating());
		
		oldReview.setDate(LocalDateTime.now());
		eventRepository.updateReview(eventId, oldReview);
		
		UriBuilder ub = uriInfo.getAbsolutePathBuilder();
		URI uri = ub.build();
		ResponseBuilder response = Response.ok(uri);
		response.entity(oldReview);			
		return response.build();
	}
	
	@DELETE
	@Path("/{id}/reviews/{reviewId}")
	@Produces("application/json")
	public Response deleteReview(@PathParam("id") Integer eventId,
			@PathParam("reviewId") Integer reviewId) {
		
		Event event = eventRepository.getEvent(eventId);
		
		if (event == null)
			throw new EntityNotFoundException("The event with id=" + eventId + " was not found");

		Review reviewToBeDeleted= eventRepository.getReview(eventId, reviewId);
		
		if(reviewToBeDeleted == null)
			throw new EntityNotFoundException("The review with id=" + reviewId +
					" was not found in that event");
	
		eventRepository.deleteReview(eventId, reviewId);
		
		return Response.noContent().build();
	}
	
	// Event Place
	@GET
	@Path("/{id}/place")
	@Produces("application/json")
	public Response getPlace(@PathParam("id") Integer eventId) {
		
		Event event = eventRepository.getEvent(eventId);
		
		if (event == null)
			throw new EntityNotFoundException("The event with id=" + eventId+ " not found");
		
		return Response.status(Status.OK).entity(event.getPlace()).build();
	}
	
	
	@POST
	@Path("/{id}/place/{placeId}")
	@Produces("application/json")
	public Response addPlace(@Context UriInfo uriInfo, @PathParam("id") Integer eventId,
			@PathParam("placeId") Integer placeId) {
		
		Event event = eventRepository.getEvent(eventId);
		Place place = MapPlaceRepository.getInstance().getPlace(placeId);
		
		if(event == null)
			throw new EntityNotFoundException("The event with id=" + eventId+ " not found");
		
		if (place == null)
			throw new EntityNotFoundException("The with id=" + placeId + 
					" not found cannot be added to an event");

		
		eventRepository.addPlace(eventId, placeId);
		
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "getPlace");
		URI uri = ub.build(eventId);
		ResponseBuilder resp = Response.created(uri);
		resp.entity(event);			
		return resp.build();
	}
	
	@PUT
	@Path("/{id}/place/{placeId}")
	@Produces("application/json")
	public Response updatePlace(@Context UriInfo uriInfo, @PathParam("id") Integer eventId,
			@PathParam("placeId") Integer placeId) {
		
		Event event = eventRepository.getEvent(eventId);
		Place place = MapPlaceRepository.getInstance().getPlace(placeId);
		
		if (event == null) 
			throw new EntityNotFoundException("The event with id=" + eventId + " not found");
		
		if (place == null)
			throw new EntityNotFoundException("The with id=" + placeId + 
					" not found cannot be added to an event");
		
		eventRepository.updatePlace(eventId, placeId);
		
		UriBuilder ub = uriInfo.getAbsolutePathBuilder();
		URI uri = ub.build();
		ResponseBuilder response = Response.ok(uri);
		response.entity(event);			
		return response.build();		
	}
	
	@DELETE
	@Path("/{id}/place")
	@Produces("application/json")
	public Response deletePlace(@PathParam("id") Integer eventId) {
		
		Event event = eventRepository.getEvent(eventId);
		
		if (event == null)
			throw new EntityNotFoundException("The event with id=" + eventId + " was not found");
		
		eventRepository.deletePlace(eventId);
		
		return Response.noContent().build();
	}
}
