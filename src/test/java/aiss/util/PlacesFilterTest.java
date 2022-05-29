//package aiss.util;
//
//import static org.junit.Assert.*;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import aiss.model.accommodation.accommodationType;
//import aiss.model.Place;
//import aiss.model.PlaceCategory;
//import aiss.model.repository.MapPlaceRepository;
//
//public class PlacesFilterTest {
//	
//	public static List<Place> places = new ArrayList<Place>();
//	
//	@BeforeClass
//	public static void init() {
//		 places = MapPlaceRepository.getInstance()
//				.getAllPlaces().stream().collect(Collectors.toList());
//	}
//
//	@Test
//	public void ShouldGetPlacesByCategory() {
//		String filter = "type= supermarket; business";
//		List<Place> expected = places.stream()
//				.filter(place -> place.getCategory().equals(PlaceCategory.SUPERMARKET)
//						|| place.getCategory().equals(PlaceCategory.BUSINESS))
//				.collect(Collectors.toList());
//		List<Place> result = PlacesUtil.filterPlaces(places, filter);
//		assertEquals(expected, result);
//	}
//	
//	@Test
//	public void ShouldGetUndefinedPlaces() {
//		String filters = "type = EstoEsUntest;;;$";
//		List<Place> expected = places.stream()
//				.filter(place -> place.getCategory().equals(PlaceCategory.UNDEFINED))
//				.collect(Collectors.toList());
//		List<Place> result = PlacesUtil.filterPlaces(places, filters);
//		assertEquals(expected, result);
//	}
//	
//	@Test
//	public void searchByName() {
//		String filters = "name = estanislao";
//		List<Place> expected = places.stream()
//				.filter(place->place.getName().contains("Estanislao"))
//				.collect(Collectors.toList());
//		List<Place> result = PlacesUtil.filterPlaces(places, filters);
//		assertEquals(expected, result);
//	}
//	
//	@Test 
//	public void searchByaccommodationType() {
//		String filters = "accommodationType = flat";
//		List<Place> expected = places.stream()
//				.filter(place->place.accommodationMatches(ac->ac.getType().equals(accommodationType.FLAT)))
//				.collect(Collectors.toList());
//		List<Place> result = PlacesUtil.filterPlaces(places, filters);
//		assertEquals(expected, result);
//	}
//	
//	@Test
//	public void handleIncorrectEquals() {
//		String filters = "name=";
//		List<Place> result = PlacesUtil.filterPlaces(places, filters);
//		assertEquals(List.of(), result);
//	}
//}
