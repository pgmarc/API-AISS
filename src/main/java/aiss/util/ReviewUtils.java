package aiss.util;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import aiss.model.Review;

public class ReviewUtils {
	
	public static List<Review> filterReviews(Collection<Review> reviews, String filter) {
		return reviews.stream()
				.filter(Filtering.parseReviewFilter(filter))
				.collect(Collectors.toList());
	}	
}
