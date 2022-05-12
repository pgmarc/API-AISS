package aiss.model.repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import aiss.model.Event;


public class MapEventRepository implements EventRepository {
	private Map<Integer, Event> eventMap;
	private static MapEventRepository instance = null;
	private Integer eventIndex = 0;
	
	public static void main(String[] args) {
		System.out.println(getInstance().eventMap);
		System.out.println(getInstance().getEvent(1));
		getInstance().deleteEvent(2);
		System.out.println(getInstance().getAllEvents());
		
	}
	
	public void init() {
		this.eventMap = new HashMap<Integer, Event>();
		Event event1 = new Event("Sergio Dalma","Concert",
				51,LocalDateTime.of(2022, 05, 21, 21, 00),"https://www.sergiodalma.es/event/sevilla/",
				"info.cite@eulen.com","Concert","Bus","Cartuja Center");
		addEvent(event1);

		
		Event event2 = new Event("Noches de la Maestranza","Opera",
			150,LocalDateTime.of(2022, 9, 17, 21, 30),"https://www.entradas.com/event/placido-domingo-noches-de-la-maestranza-plaza-de-toros-de-la-maestranza-14621134/",
				"taquilla@teatrodelamaestranza.es","Opera","Bus","La Maestranza");
		addEvent(event2);
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
		eventMap.put(event.getId(), event);
		eventIndex++;
	}

	@Override
	public Collection<Event> getAllEvents() {
		return eventMap.values();
	}

	@Override
	public Event getEvent(Integer eventId) {
		return eventMap.get(eventId);
	}

	@Override
	public void updateEvent(Event event) {
		eventMap.put(event.getId(), event);
	}

	@Override
	public void deleteEvent(Integer eventId) {
		eventMap.remove(eventId);
	}

}
