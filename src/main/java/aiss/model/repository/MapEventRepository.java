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
		
		Review review1 = new Review("Zarzu","Todo perfecto",4.5);
		
		Review review2 = new Review("pgmarc","Sitio perfecto para estar en familia",2.0);
		
		Review review3 = new Review();
		review3.setUsername("Beni135");
		review3.setDescription("Un sitio muy agradable, gran servicio y increibles precios");
		review3.setRating(4.5);
		
		Review review4 = new Review();
		review4.setUsername("Killerpro");
		review4.setDescription("De las mejores cervezas de la zona por un precio asequible,"
				+ " ademas tiene un pequeño menú por si entra hambre");
		review4.setRating(4.0);
		
		Review review5 = new Review();
		review5.setUsername("Pablo456");
		review5.setDescription("Un sitio agradable, no es el mejor en su "
				+ "categoria pero no está mal");
		review5.setRating(2.5);
		
		Review review6 = new Review();
		review6.setUsername("Jaime");
		review6.setDescription("Un pesimo servicio y precios desorbitados, "
				+ "ademas una localización espantosa en medio de la nada");
		review6.setRating(0.);
		
		addReview(0, review1);
		addReview(1, review2);
		addReview(2, review3);
		addReview(3, review4);
		addReview(4, review5);
		addReview(5, review6);
		addReview(6, review1);
		
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

	