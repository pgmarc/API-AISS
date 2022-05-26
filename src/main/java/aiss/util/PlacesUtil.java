package aiss.util;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import aiss.model.Place;
import aiss.model.PlaceCategory;

public class PlacesUtil {
		
	public static List<Place> getPagination(List<Place> places, Integer limit, Integer offset) {
		return places.stream().skip(offset).limit(limit).collect(Collectors.toList());
		
 	}
	
	public static PlaceCategory getValidPlaceCategory(String categoryName) {
		
		if (!EnumValidator.isValidEnum(PlaceCategory.class, categoryName.toUpperCase())) {
			return  PlaceCategory.UNDEFINED;
		} 
		
		return PlaceCategory.valueOf(categoryName.toUpperCase());
	}
	
	public static Set<PlaceCategory> getCategoriesToFilter(String categories) {
		List<String> categoryNames = getListOfCategoryNamesSeparatedBySemicolon(categories);
		Set<PlaceCategory> placeCategories = categoryNames.stream()
				.map(categoryName -> getValidPlaceCategory(categoryName))
				.collect(Collectors.toSet());
		
		if (categories == null || categories.isEmpty() || categories.equals(" "))
			return Set.of(PlaceCategory.values());
		
		if (placeCategories.isEmpty())
			return Set.of(PlaceCategory.values());
		
		if (placeCategories.size() == 1 && placeCategories.contains(PlaceCategory.UNDEFINED))
			return Set.of(PlaceCategory.values());
		
		return placeCategories; 
	}
	
	private static List<String> getListOfCategoryNamesSeparatedBySemicolon(String categories) {
		return Arrays.asList(categories.replace(" ", "").split(";"));
	}
	
	public static <E extends Enum<E>> boolean stringMatchesEnum(E enumValue, String stringValue) {
		return enumValue.toString().equals(stringValue.toUpperCase());
	}
	
	public static List<Place> filterPlacesByCategory(List<Place> places, String categories) {
		Set<PlaceCategory> categoriesToFilter = getCategoriesToFilter(categories);
		return places.stream()
				.filter(place -> categoriesToFilter.contains(place.getCategory()))
				.collect(Collectors.toList());
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
