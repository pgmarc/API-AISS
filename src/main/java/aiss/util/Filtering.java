package aiss.util;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

import aiss.model.Place;

public class Filtering {
	
	public static final Map<String, Function<String, Predicate<Place>>> placeFilters = Map.ofEntries(
		Map.entry("type", o->(p->PlacesUtil.parseCategoriesToFilter(o).contains(p.getCategory()))),
		Map.entry("name", o-> (p->p.getName().contains(o))),
		Map.entry("accomodationType", o-> (p->p.accomodationMatches(
				ac-> PlacesUtil.stringMatchesEnum(ac.getType(), o)))),
		Map.entry("paymentPeriod", o-> (p->p.accomodationMatches(
				ac-> ac.anyPaymentsMatch(pay->PlacesUtil.stringMatchesEnum(pay.getPaymentPeriod(),o))))),
		Map.entry("mealService", o -> (p->p.accomodationMatches(
				ac-> ac.anyPaymentsMatch(pay->PlacesUtil.stringMatchesEnum(pay.getMealService(),o))))),
		Map.entry("roomType", o -> (p->p.accomodationMatches(
				ac-> ac.anyPaymentsMatch(pay->PlacesUtil.stringMatchesEnum(pay.getRoomType(),o))))),
		Map.entry("minAccomodationPrice", o->(p->p.accomodationMatches(
				ac -> ac.getMaxMonthlyPrice() >= PlacesUtil.safeParseDouble(o)))),
		Map.entry("maxAccomodationPrice", o->(p->p.accomodationMatches(
				ac -> ac.getMinMonthlyPrice() <= PlacesUtil.safeParseDouble(o)))),
		Map.entry("minRating", o->(p->p.getRating() >= PlacesUtil.safeParseDouble(o))),
		Map.entry("maxRating", o->(p->p.getRating() <= PlacesUtil.safeParseDouble(o)))
		);
	
	public static Predicate<Place> parsePlaceFilter(String filters) {
		return Arrays.stream(filters.replace(" ", "").split(","))
				.map(s->s.split("="))
				.filter(ar->ar.length == 2 && !ar[0].isEmpty() && !ar[1].isEmpty())
				.map(ar->placeFilters.get(ar[0]).apply(ar[1]))
				.filter(pred->pred != null)
				.reduce((pred1,pred2)->pred1.and(pred2))
				.orElse(p->false);
	}
}
