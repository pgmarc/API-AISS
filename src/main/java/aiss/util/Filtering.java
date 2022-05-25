package aiss.util;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

import aiss.model.Place;
import aiss.model.Review;

public class Filtering {
	
	public static final Map<String, Function<String, Predicate<Place>>> placeFilters = Map.ofEntries(
		
			Map.entry("type", toFilter->(p->PlacesUtil.parseCategoriesToFilter(toFilter).contains(p.getCategory()))),
			
			Map.entry("name", toFilter-> (p->p.getName().toLowerCase().contains(toFilter.toLowerCase()))),
			
			Map.entry("accomodationType", toFilter-> (p->p.accomodationMatches(
					ac-> PlacesUtil.stringMatchesEnum(ac.getType(), toFilter)))),
			
			Map.entry("paymentPeriod", toFilter-> (p->p.accomodationMatches(
					ac-> ac.anyPaymentsMatch(pay->PlacesUtil.stringMatchesEnum(pay.getPaymentPeriod(),toFilter))))),
			
			Map.entry("mealService", toFilter -> (p->p.accomodationMatches(
					ac-> ac.anyPaymentsMatch(pay->PlacesUtil.stringMatchesEnum(pay.getMealService(),toFilter))))),
			
			Map.entry("roomType", toFilter -> (p->p.accomodationMatches(
					ac-> ac.anyPaymentsMatch(pay->PlacesUtil.stringMatchesEnum(pay.getRoomType(),toFilter))))),
			
			Map.entry("minAccomodationPrice", toFilter->(p->p.accomodationMatches(
					ac -> ac.getMaxMonthlyPrice() >= PlacesUtil.safeParseDouble(toFilter)))),
			
			Map.entry("maxAccomodationPrice", toFilter->(p->p.accomodationMatches(
					ac -> ac.getMinMonthlyPrice() <= PlacesUtil.safeParseDouble(toFilter)))),
			
			Map.entry("minRating", toFilter->(p->p.getRating() >= PlacesUtil.safeParseDouble(toFilter))),
			
			Map.entry("maxRating", toFilter->(p->p.getRating() <= PlacesUtil.safeParseDouble(toFilter))),
			
			Map.entry("minReviews", toFilter->(p->p.getNumReviews() >= PlacesUtil.safeParseDouble(toFilter))),
			
			Map.entry("maxReviews", toFilter->(p->p.getNumReviews() <= PlacesUtil.safeParseDouble(toFilter)))
			
			);
	
	public static final Map<String, Function<String, Predicate<Review>>> reviewFilters = Map.ofEntries(
			
			Map.entry("minRating", toFilter->(review->review.getRating() >= PlacesUtil.safeParseDouble(toFilter))),
			
			Map.entry("maxRating", toFilter->(review->review.getRating() >= PlacesUtil.safeParseDouble(toFilter))),
			
			Map.entry("username", toFilter->(review->review.getUsername().contains(toFilter))),
			
			Map.entry("description", toFilter->(review->review.getDescription().contains(toFilter)))
			);
	
	
	
	public static Predicate<Place> parsePlaceFilter(String filters) {
		return Arrays.stream(filters.replace(" ", "").split(","))
				.map(s->s.split("="))
				.filter(ar->ar.length == 2 && !ar[0].isEmpty() && !ar[1].isEmpty())
				.map(ar->placeFilters.get(ar[0]).apply(ar[1]))
				.filter(pred->pred != null)
				.reduce((pred1,pred2)->pred1.and(pred2))
				.orElse(p -> false);
	}
	
	public static Predicate<Review> parseReviewFilter(String filters) {
		return Arrays.stream(filters.replace(" ", "").split(","))
				.map(s->s.split("="))
				.filter(ar->ar.length == 2 && !ar[0].isEmpty() && !ar[1].isEmpty())
				.map(ar->reviewFilters.get(ar[0]).apply(ar[1]))
				.filter(pred->pred != null)
				.reduce((pred1,pred2)->pred1.and(pred2))
				.orElse(p -> false);
	}
}
