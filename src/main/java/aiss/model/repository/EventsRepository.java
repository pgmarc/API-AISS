package aiss.model.repository;

import java.util.Collection;

import aiss.model.Event;


public interface EventsRepository {
	
	public void addEvent(Event event);
	public Collection<Event> getAllEvents();
	public Event getEvents(Integer eventId);
	public void updateEvent(Event event);
	public void deleteEvent(Integer eventId);


	
	

}
