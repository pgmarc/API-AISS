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
	
	public static List<Place> getPagination(List<Place> places, 
			Integer limit, Integer offset) {
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
