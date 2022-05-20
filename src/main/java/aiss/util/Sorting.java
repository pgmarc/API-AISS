package aiss.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

import aiss.model.Accomodation;
import aiss.model.Place;

public class Sorting {

	public static final Map<String, Comparator<Place>> sorts = Map.ofEntries(
	Map.entry("+name", (p1,p2)->p1.getName().compareTo(p2.getName())),
	Map.entry("-name", (p1, p2)->p2.getName().compareTo(p1.getName())),
	Map.entry("+rating", (p1,p2)->p2.getRating().compareTo(p1.getRating())),
	Map.entry("-rating", (p1,p2)->p1.getRating().compareTo(p2.getRating())),
	Map.entry("type", (p1,p2)->p1.getCategory().compareTo(p2.getCategory())),
	Map.entry("accomodationType", (p1,p2)->
			Comparator.nullsLast((ac1,ac2)->((Accomodation) ac1).getType().compareTo(((Accomodation) ac2).getType()))
				.compare(p1.getAccomodation(), p2.getAccomodation())),
	Map.entry("+accomodationPrice", (p1,p2)->
			Comparator.nullsLast((ac1,ac2)->((Accomodation) ac2).getMaxMonthlyPrice().compareTo(((Accomodation) ac1).getMaxMonthlyPrice()))
				.compare(p1.getAccomodation(), p2.getAccomodation())),
	Map.entry("-accomodationPrice", (p1,p2)->
	Comparator.nullsLast((ac1,ac2)->((Accomodation) ac1).getMinMonthlyPrice().compareTo(((Accomodation) ac2).getMinMonthlyPrice()))
		.compare(p1.getAccomodation(), p2.getAccomodation()))
	);

	public static Comparator<Place> parseSort(String sort) {
		return Arrays.stream(sort.replace(" ", "").split(","))
			.filter(s->!s.isEmpty())
			.map(s->sorts.get(s))
			.filter(c->c!=null)
			.reduce((c1,c2)->c1.thenComparing(c2))
			.orElse((p1,p2)->0);
	}
	


	
	
}
