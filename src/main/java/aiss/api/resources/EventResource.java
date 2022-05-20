package aiss.api.resources;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import aiss.model.Event;
import aiss.model.Review;
import aiss.model.repository.EventRepository;
import aiss.model.repository.MapEventRepository;


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
	public Response getAllEvents(@QueryParam ("initialDate") String initialDateString,
			@QueryParam ("finalDate") String finalDateString ) {
		LocalDateTime dateNow = LocalDateTime.now();
		LocalDateTime dateNowPlusMonth = dateNow.plusDays(30);
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		String initialDate = Optional.ofNullable(initialDateString).orElse(dateNow.format(dateFormat));
		String finalDate = Optional.ofNullable(finalDateString).orElse(dateNowPlusMonth.format(dateFormat));
		LocalDateTime minDate= LocalDateTime.parse(initialDate);  
		LocalDateTime maxDate= LocalDateTime.parse(finalDate); 
		
		return Response.status(Status.OK).entity(null).build(); 
	}
	
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Response getEvent(@PathParam("id") Integer eventId) {
		Event event = eventRepository.getEvent(eventId);
		return Response.status(Status.OK).entity(event).build();
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addEvent(@Context UriInfo uriInfo, Event event) {
		
		if (event.getName() == null || "".equals(event.getName()))
			throw new BadEntityRequestException("The name of an event must not be null");
		
		if (event.getPrice() == null || "".equals(event.getPrice()))
			throw new BadEntityRequestException("The price of an event must not be null");
		
		if (event.getDate() == null || "".equals(event.getDate()))
			throw new BadEntityRequestException("The date of an event must not be null");
		
		if (event.getContactEmail() == null || "".equals(event.getContactEmail()))
			throw new BadEntityRequestException("The email of contact of an event must not be null");
		
		if (event.getOrganizators() == null || "".equals(event.getOrganizators()))
			throw new BadEntityRequestException("The organizatos of an event must not be null");
		
		if (event.getId() !=null)
			throw new BadEntityRequestException("The event id must not been given as a parameter.");

		eventRepository.addEvent(event);
		// Builds the response. Returns the event the has just been added.
		UriBuilder ub = uriInfo.getAbsolutePathBuilder(). path(this.getClass(), "getEvent");
		URI uri = ub.build(event.getId());
		ResponseBuilder response = Response.created(uri);
		response.entity(event);			
		return response.build();
	}
	
	@PUT
	@Path("/{id}")
	@Consumes("application/json")
	public Response updateEvent(@PathParam("id") Integer eventId,Event event) {
		Event oldEvent = eventRepository.getEvent(eventId);
		if (oldEvent == null)
			throw new EntityNotFoundException("The event with id="+ event.getId() +" was not found");			
		
		if (event.getName() != null)
			oldEvent.setName(event.getName());
		
		if (event.getContactEmail() != null)
			oldEvent.setContactEmail(event.getContactEmail());
		
		if (event.getPrice() != null)
			oldEvent.setPrice(event.getPrice());
		
		if (event.getDate() != null)
			oldEvent.setDate(event.getDate());
		if (event.getOrganizators() != null)
			oldEvent.setOrganizators(event.getOrganizators());
		
		
		eventRepository.updateEvent(oldEvent);
		
		return Response.status(Status.NO_CONTENT).entity(oldEvent).build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response deleteEvent(@PathParam("id") Integer eventId) {
		Event eventToBeRemoved = eventRepository.getEvent(eventId);
		if (eventToBeRemoved == null)
			throw new EntityNotFoundException("The event with id=" + eventId + " was not found");
		else
			eventRepository.deleteEvent(eventId);
		
		return Response.noContent().build();
	}
	
	//REVIEWS
	@GET
	@Path("/{id}/reviews/{reviewId}")
	@Produces("application/json")
	public Review getReview(@PathParam("id") Integer eventId,
			@PathParam("reviewId") Integer reviewId) {
		
		Event event = eventRepository.getEvent(eventId);
		Review review = eventRepository.getReview(eventId,reviewId);
		
		if(event==null) {
			throw new EntityNotFoundException("The event with id: " + eventId + " was not found.");
		}
		if(review==null) {
			throw new EntityNotFoundException("The review with id: " + reviewId + " was not found in the event.");
		}
		return review;
	}
	
	
	@POST
	@Path("/{id}/reviews")
	@Consumes("application/json")
	@Produces("application/json")
	public Response addReview(@PathParam("id") Integer eventId, @Context UriInfo uriInfo,
			Review review) {
		
		Event event = eventRepository.getEvent(eventId);
		
		if(event==null)
			throw new EntityNotFoundException("The event with id: " + eventId + " was not found.");
		
		if (review.getId() !=null)
			throw new BadEntityRequestException("The review id must not been given as a parameter.");
		
		if (review.getUsername()==null) review.setUsername("Anonymous");

		if (review.getDescription()==null) review.setDescription("");
		
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
	public Response updateReview(@PathParam("id") Integer eventId,
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
		
		return Response.status(Status.NO_CONTENT).entity(oldReview).build();
	}
	
	@DELETE
	@Path("/{id}/reviews/{reviewId}")
	public Response deleteReview(@PathParam("id") Integer eventId,
			@PathParam("reviewId") Integer reviewId) {
		
		Event event = eventRepository.getEvent(eventId);
		
		if (event == null)
			throw new EntityNotFoundException("The event with id=" + eventId + " was not found");

		Review reviewToBeDeleted= eventRepository.getReview(eventId, reviewId);
		
		if(reviewToBeDeleted==null)
			throw new EntityNotFoundException("The review with id=" + reviewId +
					" was not found in that event");
	
		eventRepository.deleteReview(eventId, reviewId);
		
		return Response.noContent().build();
	}
}
