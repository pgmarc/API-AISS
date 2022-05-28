package aiss.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

import aiss.model.Accommodation;
import aiss.model.Event;
import aiss.model.Place;
import aiss.model.Review;

public class Sorting {
	
	public static final Map<String, Comparator<Event>> eventSorts = Map.ofEntries(
			
			Map.entry("+date", (e1,e2)->e1.getLocalDate().compareTo(e2.getLocalDate())),
			
			Map.entry("-date", (e1,e2)->e2.getLocalDate().compareTo(e1.getLocalDate()))
		);

	public static final Map<String, Comparator<Review>> reviewSorts = Map.ofEntries(
			
			Map.entry("+date", (r1,r2)->r1.getCompleteDate().compareTo(r2.getCompleteDate())),
			
			Map.entry("-date", (r1,r2)->r2.getCompleteDate().compareTo(r1.getCompleteDate())),
			
			Map.entry("+rating", (r1,r2)->r1.getRating().compareTo(r2.getRating())),
			
			Map.entry("-rating", (r1,r2)->r2.getRating().compareTo(r1.getRating()))
		);
	
	public static final Map<String, Comparator<Place>> placeSorts = Map.ofEntries(
			
			Map.entry("+name", (p1,p2)->p1.getName().compareTo(p2.getName())),
			
			Map.entry("-name", (p1, p2)->p2.getName().compareTo(p1.getName())),
			
			Map.entry("+rating", (p1,p2)->p2.getRating().compareTo(p1.getRating())),
			
			Map.entry("-rating", (p1,p2)->p1.getRating().compareTo(p2.getRating())),
			
			Map.entry("+reviews", (p1,p2)->p1.getNumReviews().compareTo(p2.getNumReviews())),
			
			Map.entry("-reviews", (p1,p2)->p2.getNumReviews().compareTo(p1.getNumReviews())),
			
			Map.entry("type", (p1,p2)->p1.getCategory().compareTo(p2.getCategory())),
			
			Map.entry("accommodationType", (p1,p2)->
					Comparator.nullsLast((ac1,ac2)->((Accommodation) ac1).getType().compareTo(((Accommodation) ac2).getType()))
						.compare(p1.getaccommodation(), p2.getaccommodation())),
			
			Map.entry("+accommodationPrice", (p1,p2)->
					Comparator.nullsLast((ac1,ac2)->((Accommodation) ac2).getMaxMonthlyPrice().compareTo(((Accommodation) ac1).getMaxMonthlyPrice()))
						.compare(p1.getaccommodation(), p2.getaccommodation())),
			
			Map.entry("-accommodationPrice", (p1,p2)->
			Comparator.nullsLast((ac1,ac2)->((Accommodation) ac1).getMinMonthlyPrice().compareTo(((Accommodation) ac2).getMinMonthlyPrice()))
				.compare(p1.getaccommodation(), p2.getaccommodation()))
		);
	
	public static Comparator<Place> parsePlaceSort(String sort) {
		return Arrays.stream(sort.replace(" ", "").split(","))
			.map(s->placeSorts.get(s))
			.filter(c->c!=null)
			.reduce((c1,c2)->c1.thenComparing(c2))
			.orElse((p1,p2)->0);
	}
	
	public static Comparator<Event> parseEventSort(String sort) {
		return Arrays.stream(sort.replace(" ", "").split(","))
			.map(s->eventSorts.get(s))
			.filter(c->c!=null)
			.reduce((c1,c2)->c1.thenComparing(c2))
			.orElse((e1,e2)->0);
	}
	
	public static Comparator<Review> parseReviewSort(String sort) {
		return Arrays.stream(sort.replace(" ", "").split(","))
			.map(s->reviewSorts.get(s))
			.filter(c->c!=null)
			.reduce((c1,c2)->c1.thenComparing(c2))
			.orElse((r1,r2)->0);
	}
}
