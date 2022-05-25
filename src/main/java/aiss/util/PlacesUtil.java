package aiss.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import aiss.model.Accomodation;
import aiss.model.Place;
import aiss.model.PlaceCategory;

public class PlacesUtil {
		
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
		List<String> categoryNames = getListOfCategoryNamesSeparatedBySemicolon(categories);
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
	
	private static List<String> getListOfCategoryNamesSeparatedBySemicolon(String categories) {
		return Arrays.asList(categories.replace(" ", "").split(";"));
	}
	
	public static <E extends Enum<E>> boolean stringMatchesEnum(E enumValue, String stringValue) {
		return enumValue.toString().equals(stringValue.toUpperCase());
	}
	
	public static List<Place> filterPlacesByCategory(List<Place> places, String categories) {
		Set<PlaceCategory> categoriesToFilter = parseCategoriesToFilter(categories);
		return places.stream()
				.filter(place -> 
				categoriesToFilter.contains(place.getCategory())).collect(Collectors.toList());
	}
	
	public static List<Place> filterPlaces(List<Place> places, String filters) {
		return places.stream().filter(Filtering.parsePlaceFilter(filters))
				.collect(Collectors.toList());
	}
	
	public static Double safeParseDouble(String s) {
		try {
			return Double.valueOf(s);
		} catch (Exception e) {
			return null;
		}
	}
}
