package aiss.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.BeforeClass;
import org.junit.Test;

import aiss.model.Accomodation.AccomodationType;
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
		String categories = ";;;";
		Set<PlaceCategory> expected = Set.of(PlaceCategory.UNDEFINED);
		Set<PlaceCategory> result = PlacesUtil.getCategoriesToFilter(categories);
		assertEquals(expected, result);
	}
	
	@Test 
	public void ShouldGetCategoriesToFilterUpperCased() {
		String categories = "";
		categories += "SUPERMARKET;PHARMACY;ATM;BUSINESS;BAR;RESTAURANT;CHURCH;CINEMA;PARK;NIGTH_CLUB;MUSEUM;UNIVERSITY;GYM;HOSPITAL;";
		categories += "POLICE;TOURIST_ATTRACTION;CONGRESS;ACCOMODATION;STADIUM;GAS_STATION;TRAIN_STATION;BUS_STATION;AIRPORT;PARKING;";
		categories+= "TAXI_STAND;SUBWAY_STATION;UNDEFINED";

				
		Set<PlaceCategory> expected = Set.of(PlaceCategory.values());
		
		Set<PlaceCategory> result = PlacesUtil.getCategoriesToFilter(categories);
		assertEquals(expected, result);
	}
	
	@Test 
	public void ShouldGetCategoriesToFilterLowerCased() {
		String categories = "";
		categories += "supermarket;pharmacy;atm;business;bar;restaurant;church;cinema;park;nigth_club;museum;university;gym;hospital;";
		categories += "police;tourist_attraction;congress;accomodation;stadium;gas_station;train_station;bus_station;airport;parking;";
		categories+= "taxi_stand;subway_station;undefined";

				
		Set<PlaceCategory> expected = Set.of(PlaceCategory.values());
		
		Set<PlaceCategory> result = PlacesUtil.getCategoriesToFilter(categories);
		assertEquals(expected, result);
	}
	
	@Test 
	public void ShouldGetCategoriesToFilterLowerCasedSpaced() {
		String categories = "";
		categories += "supermarket ;pharmacy; atm; congress; business; bar; restaurant; church; cinema;park; nigth_club; museum; university; gym; hospital;";
		categories += "police; tourist_attraction; accomodation; stadium;  gas_station; train_station; bus_station; airport; parking;";
		categories+= "taxi_stand;  subway_station;  undefined";

				
		Set<PlaceCategory> expected = Set.of(PlaceCategory.values());
		
		Set<PlaceCategory> result = PlacesUtil.getCategoriesToFilter(categories);
		assertEquals(expected, result);
	}
	
	@Test 
	public void ShouldGetCategoriesNothingToFilter() {
		String categories = ";a;$; ;";
		
		Set<PlaceCategory> expected = Set.of(PlaceCategory.UNDEFINED);
		
		Set<PlaceCategory> result = PlacesUtil.getCategoriesToFilter(categories);
		assertEquals(expected, result);
	}
	
	@Test 
	public void ShouldGetUndefinedAndCategoryToFilter() {
		String categories = ";a;university; ;";
		
		Set<PlaceCategory> expected = Set.of(
				PlaceCategory.UNDEFINED,
				PlaceCategory.UNIVERSITY);
		
		Set<PlaceCategory> result = PlacesUtil.getCategoriesToFilter(categories);
		assertEquals(expected, result);
	}
	
	@Test
	public void ShouldGetPlacesByCategory() {
		String filter = "type= supermarket; business";
		List<Place> expected = places.stream()
				.filter(place -> place.getCategory().equals(PlaceCategory.SUPERMARKET)
						|| place.getCategory().equals(PlaceCategory.BUSINESS))
				.collect(Collectors.toList());
		List<Place> result = PlacesUtil.filterPlaces(places, filter);
		assertEquals(expected, result);
	}
	
	@Test
	public void ShouldGetUndefinedPlaces() {
		String filters = "type = EstoEsUntest;;;$";
		List<Place> expected = places.stream()
				.filter(place -> place.getCategory().equals(PlaceCategory.UNDEFINED))
				.collect(Collectors.toList());
		List<Place> result = PlacesUtil.filterPlaces(places, filters);
		assertEquals(expected, result);
	}
	
	@Test
	public void searchByName() {
		String filters = "name = estanislao";
		List<Place> expected = places.stream()
				.filter(place->place.getName().contains("Estanislao"))
				.collect(Collectors.toList());
		List<Place> result = PlacesUtil.filterPlaces(places, filters);
		assertEquals(expected, result);
	}
	
	@Test 
	public void searchByAccomodationType() {
		String filters = "accomodationType = flat";
		List<Place> expected = places.stream()
				.filter(place->place.accomodationMatches(ac->ac.getType().equals(AccomodationType.FLAT)))
				.collect(Collectors.toList());
		List<Place> result = PlacesUtil.filterPlaces(places, filters);
		assertEquals(expected, result);
	}
	
	@Test
	public void handleIncorrectEquals() {
		String filters = "name=";
		List<Place> result = PlacesUtil.filterPlaces(places, filters);
		assertEquals(List.of(), result);
	}
}
