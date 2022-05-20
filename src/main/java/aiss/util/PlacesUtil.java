package aiss.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import aiss.model.Place;
import aiss.model.PlaceCategory;

public class PlacesUtil {
	
	public static final Map<String, Comparator<Place>> sorts = Map.ofEntries(
			Map.entry("+name", (p1,p2) -> p1.getName().compareTo(p2.getName())),
			Map.entry("-name", (p1, p2) -> p2.getName().compareTo(p1.getName())),
			Map.entry("+rating", (p1,p2) -> p1.getRating().compareTo(p2.getRating())),
			Map.entry("-rating", (p1,p2) -> p2.getRating().compareTo(p1.getRating())),
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
			.filter(s-> !s.isEmpty())
			.map(s-> sorts.get(s))
			.filter(c-> c!=null)
			.reduce((c1,c2)-> c1.thenComparing(c2))
			.orElse((p1,p2)-> 0);
	}
	
	public static List<Place> getPagination(List<Place> places, Integer limit, Integer offset) {
		return places.stream().skip(offset).limit(limit).collect(Collectors.toList());
		
 	}
	
	public static PlaceCategory getValidPlaceCategory(String categoryName) {
		String categoryNameUppercased = parseCategoryName(categoryName).toUpperCase();
		
		if (categoryNameUppercased.isEmpty() || !isAValidPlaceCategory(categoryNameUppercased)) {
			return  PlaceCategory.UNDEFINED;
		} 
		
		return PlaceCategory.valueOf(categoryNameUppercased);
	}
	
	public static Set<PlaceCategory> parseCategoriesToFilter(String categories) {
		Set<PlaceCategory> categoriesToFilter = new HashSet<>();
		List<String> categoryNames = getListOfCategoryNamesSeparatedByCommas(categories);
		if (categoryNames.isEmpty()) {
			return Set.of(PlaceCategory.UNDEFINED);
		}
		
		for (String categoryName : categoryNames) {
			PlaceCategory category = getValidPlaceCategory(categoryName.trim());
			categoriesToFilter.add(category);
		}
		
		return categoriesToFilter;
	}
	
	private static boolean isAValidPlaceCategory(String categoryName) {
		return getPlacesCategoriesByName().contains(categoryName);
	}
	
	private static String parseCategoryName(String categoryName) {
		return Optional.ofNullable(categoryName).orElse("");
	}
	
	private static List<String> getPlacesCategoriesByName() {
		return Place.getPlaceCategories().stream()
				.map(category -> category.toString()).collect(Collectors.toList());
	}
	
	private static List<String> getListOfCategoryNamesSeparatedByCommas(String categories) {
		return Arrays.asList(categories.trim().split(","));
	}
	
	public static List<Place> filterPlacesByCategory(List<Place> places, String categories) {
		Set<PlaceCategory> categoriesToFilter = parseCategoriesToFilter(categories);
		return places.stream()
				.filter(place -> 
				categoriesToFilter.contains(place.getCategory())).collect(Collectors.toList());
	}
}
