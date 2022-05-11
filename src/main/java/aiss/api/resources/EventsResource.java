package aiss.api.resources;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
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

import org.jboss.resteasy.spi.BadRequestException;
import org.jboss.resteasy.spi.NotFoundException;

import aiss.model.Event;
import aiss.model.Review;
import aiss.model.repository.MapApplicationRepository;
import aiss.model.repository.EventsRepository;

	
	@Path("/events")
	public class EventsResource {

		public static EventsResource _instance=null;
		EventsRepository repository;
		
		private EventsResource(){
			//TODO Create MapEventRepository class file
			//repository=MapApplicationRepository.getInstance();
		}
		
		public static EventsResource getInstance()
		{
			if(_instance==null)
				_instance=new EventsResource();
			return _instance; 
		}
		

//		@GET
//		@Produces("application/json")
//		public Collection<Event> getAll(@QueryParam("order") String order, @QueryParam("q") String query)
//		{
//			List<Event> result = new ArrayList<Event>();
//			for (Event event : repository.getAllEvents()) {
//				if (query == null || query.equalsIgnoreCase(event.getName()) 
//						|| query.equalsIgnoreCase(event.getDescription()) 
//						|| query.equalsIgnoreCase(event.getPrice()) 
//						|| query.equalsIgnoreCase(event.getDate())) {
//					result.add(event);
//				}
//				
//			}
//			return result;
//		}

		@GET
		@Produces("application/json")
		public Collection<Event> getAll(@QueryParam("order") String order,
				@QueryParam("q") String query) {
			List<Event> result = new ArrayList<Event>();
			return result;
		}

		
		@GET
		@Path("/{id}")
		@Produces("application/json")
		public Event get(@PathParam("id") Integer eventId)
		{
			Event event = repository.getEvents(eventId);
			if(event.equals(null)) {
				throw new NotFoundException("The event with id: " + eventId + " was not found.");
			}
			return event;
		}
		

//		@POST
//		@Consumes("application/json")
//		@Produces("application/json")
//		public Response addEvent(@Context UriInfo uriInfo, Event event) {
//			
//			if (event.getName() == null || "".equals(event.getName()))
//				throw new BadRequestException("The name of the event must not be null");
//			
//			if (event.getId() !=null)
//				throw new BadRequestException("The event id must not been given as a parameter.");
//
//			repository.addEvent(event);
//
//			// Builds the response. Returns the song the has just been added.
//			UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
//			URI uri = ub.build(event.getId());
//			ResponseBuilder resp = Response.created(uri);
//			resp.entity(event);			
//			return resp.build();
//		}

		@POST
		@Consumes("application/json")
		@Produces("application/json")
		public Response addEvent(@Context UriInfo uriInfo, Event event) {
			//TODO ADD EVENT
			return Response.ok().build();
		}

		

		
//		@PUT
//		@Consumes("application/json")
//		public Response updateEvent(Event event) {
//			Event oldEvent = repository.getEvent(event.getId());
//			if (oldEvent == null) {
//				throw new NotFoundException("The song with id="+ song.getId() +" was not found");			
//			}
//			
//			// Update Album
//			if (event.getLocation()!=null)
//				oldEvent.setAlbum(event.getLocation());
//			
//			// Update artist
//			if (event.getName()!=null)
//				oldEvent.setArtist(event.getName());
//			
//			// Update title
//			if(event.getDate() != null) {
//				oldEvent.setTitle(event.getDate());
//			}
//			
//			// Update year
//			if(event.getPrice() != null) {
//				oldEvent.setYear(event.getPrice());
//			}
//			
//			return Response.noContent().build();
//		}

		@PUT
		@Consumes("application/json")
		public Response updateEvent(Event event) {
			//TODO Update Event
			
			return Response.ok().build();
		}
		
		@DELETE
		@Path("/{id}")
		public Response removeEvent(@PathParam("id") Integer eventId) {
			Event eventToBeRemoved = repository.getEvents(eventId);
			if (eventToBeRemoved == null)
				throw new NotFoundException("The event with id="+ eventId +" was not found");
			else
				repository.deleteEvent(eventId);
			
			return Response.noContent().build();
		}
		
	//REVIEWS
		@POST
		@Path("/{id}/reviews")
		@Consumes("application/json")
		@Produces("application/json")
		public Response addReview(@PathParam("id") Integer eventId, @Context UriInfo uriInfo, Review review) {
			
			Event event = repository.getEvents(eventId);
			if(event.equals(null)) {
				throw new NotFoundException("The event with id: " + eventId + " was not found.");
			}
			if (review.getRating() == null)
				throw new BadRequestException("The rating must not be null");
			else if(review.getRating()<0||review.getRating()>5)
				throw new BadRequestException("The rating must be a value between 0 and 5");
			
			if (review.getId() !=null)
				throw new BadRequestException("The review id must not been given as a parameter.");
			if (review.getDescription()==null)
				review.setDescription("");
			if(review.getUsername()==null)
				review.setUsername("Anonymous");
			if(review.getDate()==null)
				review.setDate(LocalDateTime.now());
			repository.addReview(eventId,review);

			// Builds the response. Returns the review that has just been added.
			UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
			URI uri = ub.build(review.getId());
			ResponseBuilder resp = Response.created(uri);
			resp.entity(review);			
			return resp.build();
		}
		
		@GET
		@Path("/{id}/reviews/{reviewId}")
		@Produces("application/json")
		public Review getReview(@PathParam("id") Integer eventId,@PathParam("reviewId") Integer reviewId)
		{
			Review review = repository.getReview(eventId,reviewId);
			Event event = repository.getEvents(eventId);
			
			if(event.equals(null)) {
				throw new NotFoundException("The event with id: " + eventId + " was not found.");
			}
			if(review.equals(null)) {
				throw new NotFoundException("The review with id: " + reviewId + " was not found in the event.");
			}
			return review;
		}
}
