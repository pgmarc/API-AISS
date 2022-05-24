package aiss.model.repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import aiss.model.Event;
import aiss.model.Review;
import aiss.util.EventData;


public class MapEventRepository implements EventRepository {
	private Map<Integer, Event> eventsMap;
	private static MapEventRepository instance = null;
	private Integer eventIndex = 0;
	
	public static void main(String[] args) {
		System.out.println(getInstance().eventsMap);
		System.out.println(getInstance().getEvent(1));
		getInstance().deleteEvent(2);
		System.out.println(getInstance().getAllEvents());
		
	}
	
	public void init() {
		this.eventsMap = new HashMap<Integer, Event>();
			
		Event event1 = new Event("Sergio Dalma", 100.0,LocalDateTime.of(2022, 5, 21, 21, 0),
		"info.cite@eulen.com", "Cartuja Center");
		
		Event event2 = new Event("Noches de la Maestranza", 150.0, LocalDateTime.of(2022, 9, 17, 21, 30),
				"taquilla@teatrodelamaestranza.es", "La Maestranza");
		
		addEvent(event1);
		addEvent(event2);
		
		EventData.getEventsInfo().forEach(event -> addEvent(event));
		
		
	}
		
	public static MapEventRepository getInstance() {
		
		if (instance == null) {
			instance = new MapEventRepository();
			instance.init();
		}
		return instance;
	}
	
	@Override
	public void addEvent(Event event) {
		event.setId(eventIndex);
		event.setReviews(new HashMap<Integer, Review>());
		eventsMap.put(event.getId(), event);
		eventIndex++;
	}

	@Override
	public Collection<Event> getAllEvents() {
		return eventsMap.values();
	}

	@Override
	public Event getEvent(Integer eventId) {
		return eventsMap.get(eventId);
	}

	@Override
	public void updateEvent(Event event) {
		eventsMap.put(event.getId(), event);
	}

	@Override
	public void deleteEvent(Integer eventId) {
		eventsMap.remove(eventId);
	}

	//REVIEWS
	@Override
	public List<Review> getAllReviews(Integer eventId) {
		return eventsMap.get(eventId).getReviews().values().stream().collect(Collectors.toList());
	}
	
	@Override
	public Review getReview(Integer eventId, Integer reviewId) {
		return eventsMap.get(eventId).getReviews().get(reviewId);
	}
	
	@Override
	public void addReview(Integer eventId, Review review) {
		review.setDate(LocalDateTime.now());
		eventsMap.get(eventId).addReview(review);
	}

	@Override
	public void updateReview(Integer eventId, Review review) {
		eventsMap.get(eventId).getReviews().put(eventId, review);
	}

	@Override
	public void deleteReview(Integer eventId, Integer reviewId) {
		eventsMap.get(eventId).getReviews().remove(reviewId);
	}
}

	