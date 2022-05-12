package aiss.model.repository;

import java.util.Collection;

import aiss.model.Event;
import aiss.model.Review;


public interface EventRepository {
	
	public void addEvent(Event event);
	public Collection<Event> getAllEvents();
	public Event getEvent(Integer eventId);
	public void updateEvent(Event event);
	public void deleteEvent(Integer eventId);
	public Collection<Review> getAllReviews(Integer eventId);
	public Review getReview(Integer eventId, Integer reviewId);
	public void addReview(Integer eventId, Review review);
	public void updateReview(Integer eventId, Review review);
	public void deleteReview(Integer eventId, Integer reviewId);
	
}
