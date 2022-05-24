package aiss.util;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import aiss.model.Event;

public class EventsUtil {
	
	public static List<Event> sortEvent(List<Event> events, LocalDate initialDate,
			LocalDate finalDate) {
		return events.stream().filter(event -> event.getDate().toLocalDate().isAfter(initialDate)
				&& event.getDate().toLocalDate().isBefore(finalDate)).collect(Collectors.toList());
	}
}
