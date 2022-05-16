package aiss.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.BeforeClass;
import org.junit.Test;

import aiss.model.Coordinates;
import aiss.model.Place;
import aiss.model.repository.MapPlaceRepository;

public class PlacesUtilTest {
	
	public static List<Place> places = new ArrayList<Place>();
	
	@BeforeClass
	public static void init() {
		 places = MapPlaceRepository.getInstance()
				.getAllPlaces().stream().collect(Collectors.toList());
	}

	@Test
	public void SholudStartFromPositionOffsetAndSizeEqualLimit() {
		Integer limit = 3;
		Integer offset = 1;
		Place placeExpected = new Place();
		placeExpected.setId(1);
		placeExpected.setName("Demivee");
		placeExpected.setEmail("dcoughlin1@hhs.gov");
		placeExpected.setAddress("7347 Graceland Road");
		placeExpected.setWebsite("http://deliciousdays.com");
		placeExpected.setLocation(Coordinates.of(11.5399857,-85.6986957));
		List<Place> result = List.copyOf(PlacesUtil.getPagination(places, limit, offset));
		assertEquals(placeExpected, result.get(0));
		assertEquals(3, result.size());
	}
	
	@Test 
	public void OffsetOutOfBoundsReturnsAnEmpyList() {
		Integer limit = 3;
		Integer offset = 40;
		List<Place> expected = new ArrayList<Place>();
		List<Place> result = List.copyOf(PlacesUtil.getPagination(places, limit, offset));
		assertEquals(expected, result);
	}
	
	@Test
	public void ZeroLimitReturnsAnEmptyList() {
		Integer limit = 0;
		Integer offset = 40;
		List<Place> expected = new ArrayList<Place>();
		List<Place> result = List.copyOf(PlacesUtil.getPagination(places, limit, offset));
		assertEquals(expected, result);
	}
}
