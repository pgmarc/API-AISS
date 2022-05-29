package aiss.util;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import aiss.model.Event;

public class EventsUtil {
	
	public static List<Event> getEventsInDateRange(List<Event> events, LocalDate initialDate,
			LocalDate finalDate) {
		return events.stream().filter(event -> event.getLocalDateTime() != null)
				.filter(event -> event.getLocalDate().isAfter(initialDate) 
				&& event.getLocalDate().isBefore(finalDate)).collect(Collectors.toList());
	}
}
