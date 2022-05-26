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

public class PlaceFilterCategoryTest {
	
	public static List<Place> places = new ArrayList<Place>();
	
	@BeforeClass
	public static void init() {
		 places = MapPlaceRepository.getInstance()
				.getAllPlaces().stream().collect(Collectors.toList());
	}

	@Test
	public void SholudFilterWithAllCategoriesWhenGivingAllSemicolons() {
		String categories = ";;;";
		Set<PlaceCategory> expected = Set.of(PlaceCategory.values());
		Set<PlaceCategory> result = PlacesUtil.getCategoriesToFilter(categories);
		assertEquals(expected, result);
	}
	
	@Test 
	public void ShouldGetCategoriesToFilterUpperCased() {
		String categories = "";
		categories += "SUPERMARKET;PHARMACY;ATM;BUSINESS;BAR;RESTAURANT;CHURCH;CINEMA;PARK;NIGHT_CLUB;MUSEUM;UNIVERSITY;GYM;CONGRESS;HOSPITAL;";
		categories += "POLICE;TOURIST_ATTRACTION;ACCOMODATION;STADIUM;GAS_STATION;TRAIN_STATION;BUS_STATION;AIRPORT;PARKING;";
		categories+= "TAXI_STAND;SUBWAY_STATION;UNDEFINED";

				
		Set<PlaceCategory> expected = Set.of(PlaceCategory.values());
		
		Set<PlaceCategory> result = PlacesUtil.getCategoriesToFilter(categories);
		assertEquals(expected, result);
	}
	
	@Test 
	public void ShouldGetCategoriesToFilterLowerCased() {
		String categories = "";
		categories += "supermarket;pharmacy;atm;business;bar;restaurant;church;cinema;park;night_club;museum;university;gym;hospital;";
		categories += "police;tourist_attraction;congress;accomodation;stadium;gas_station;train_station;bus_station;airport;parking;";
		categories+= "taxi_stand;subway_station;undefined";

				
		Set<PlaceCategory> expected = Set.of(PlaceCategory.values());
		
		Set<PlaceCategory> result = PlacesUtil.getCategoriesToFilter(categories);
		assertEquals(expected, result);
	}
	
	@Test 
	public void ShouldGetCategoriesToFilterLowerCasedSpaced() {
		String categories = "";
		categories += "supermarket ;pharmacy; atm; congress; business; bar; restaurant; church; cinema;park; night_club; museum; university; gym; hospital;";
		categories += "police; tourist_attraction; accomodation; stadium;  gas_station; train_station; bus_station; airport; parking;";
		categories+= "taxi_stand;  subway_station;  undefined";

				
		Set<PlaceCategory> expected = Set.of(PlaceCategory.values());
		
		Set<PlaceCategory> result = PlacesUtil.getCategoriesToFilter(categories);
		assertEquals(expected, result);
	}
	
	@Test 
	public void ShouldGetAllCategories() {
		String categories = ";a;$; ;";
		
		Set<PlaceCategory> expected = Set.of(PlaceCategory.values());
		
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
}
