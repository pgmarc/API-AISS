package aiss.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.BeforeClass;
import org.junit.Test;

import aiss.model.Place;
import aiss.model.PlaceCategory;
import aiss.model.repository.MapPlaceRepository;

public class PlacesFilterTest {
	
	public static List<Place> places = new ArrayList<Place>();
	
	@BeforeClass
	public static void init() {
		 places = MapPlaceRepository.getInstance()
				.getAllPlaces().stream().collect(Collectors.toList());
	}

	@Test
	public void EmptyListWithOnlyCommas() {
		String categories = ",,,";
		Set<PlaceCategory> expected = Set.of(PlaceCategory.UNDEFINED);
		Set<PlaceCategory> result = PlacesUtil.parseCategoriesToFilter(categories);
		assertEquals(expected, result);
	}
	
	@Test 
	public void ShouldGetCategoriesToFilter() {
		String categories = "retail,business ,food_drink, disco,"
				+ "university, undefined, accomodation";
		
		Set<PlaceCategory> expected = Set.of(PlaceCategory.ACCOMODATION,
				PlaceCategory.BUSINESS,
				PlaceCategory.DISCO,
				PlaceCategory.FOOD_DRINK,
				PlaceCategory.RETAIL,
				PlaceCategory.UNDEFINED,
				PlaceCategory.UNIVERSITY);
		
		Set<PlaceCategory> result = PlacesUtil.parseCategoriesToFilter(categories);
		assertEquals(expected, result);
	}
	
	@Test 
	public void ShouldGetCategoriesNothingToFilter() {
		String categories = ",a,$, ,";
		
		Set<PlaceCategory> expected = Set.of(PlaceCategory.UNDEFINED);
		
		Set<PlaceCategory> result = PlacesUtil.parseCategoriesToFilter(categories);
		assertEquals(expected, result);
	}
	
	@Test 
	public void ShouldGetUndefinedAndCategoryToFilter() {
		String categories = ",a,university, ,";
		
		Set<PlaceCategory> expected = Set.of(
				PlaceCategory.UNDEFINED,
				PlaceCategory.UNIVERSITY);
		
		Set<PlaceCategory> result = PlacesUtil.parseCategoriesToFilter(categories);
		assertEquals(expected, result);
	}
	
	@Test
	public void ShouldGetPlacesByCategory() {
		String categories = "retail, business";
		List<Place> expected = places.stream()
				.filter(place -> place.getCategory().equals(PlaceCategory.RETAIL)
						|| place.getCategory().equals(PlaceCategory.BUSINESS))
				.collect(Collectors.toList());
		List<Place> result = PlacesUtil.filterPlacesByCategory(places, categories);
		assertEquals(expected, result);
	}
	
	@Test
	public void ShouldGetUndefinedPlaces() {
		String categories = "EstoEsUntest,,,$";
		List<Place> expected = places.stream()
				.filter(place -> place.getCategory().equals(PlaceCategory.UNDEFINED))
				.collect(Collectors.toList());
		List<Place> result = PlacesUtil.filterPlacesByCategory(places, categories);
		assertEquals(expected, result);
	}
}
