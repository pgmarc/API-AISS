package aiss.util;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.BeforeClass;
import org.junit.Test;

import aiss.model.Accomodation;
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
	
	@Test
	public void sortByNameDescending() {
		List<Place> placesToSort = new ArrayList<>(places);
		List<Place> expectedSorting = new ArrayList<>(places);
		expectedSorting.sort(Comparator.comparing(p->((Place) p).getName()));
		placesToSort.sort(Sorting.parsePlaceSort("+name"));
		assertEquals(expectedSorting, placesToSort);
	}
	
	@Test
	public void sortByNameAscending() {
		List<Place> placesToSort = new ArrayList<>(places);
		List<Place> expectedSorting = new ArrayList<>(places);
		expectedSorting.sort(Comparator.comparing(p->((Place) p).getName()).reversed());
		placesToSort.sort(Sorting.parsePlaceSort("-name"));
		assertEquals(expectedSorting, placesToSort);
	}
	
	@Test
	public void emptySort() {
		List<Place> placesToSort = new ArrayList<>(places);
		List<Place> expectedSorting = new ArrayList<>(places);
		placesToSort.sort(Sorting.parsePlaceSort(""));
		assertEquals(expectedSorting, placesToSort);
	}
	
	@Test
	public void invalidSorts() {
		List<Place> placesToSort = new ArrayList<>(places);
		List<Place> expectedSorting = new ArrayList<>(places);
		placesToSort.sort(Sorting.parsePlaceSort("hello,invalid,"));
		assertEquals(expectedSorting, placesToSort);
	}
	
	@Test
	public void sortByRatingAscending() {
		List<Place> placesToSort = new ArrayList<>(places);
		List<Place> expectedSorting = new ArrayList<>(places);
		placesToSort.sort(Sorting.parsePlaceSort("-rating"));
		expectedSorting.sort(Comparator.comparing(p->((Place) p).getRating()));
	}
	
	@Test
	public void sortByRatingDescending() {
		List<Place> placesToSort = new ArrayList<>(places);
		List<Place> expectedSorting = new ArrayList<>(places);
		placesToSort.sort(Sorting.parsePlaceSort("+rating"));
		expectedSorting.sort(Comparator.comparing(p->((Place) p).getRating()).reversed());
	}
	
	@Test
	public void sortByAccomodationType() {
		List<Place> placesToSort = new ArrayList<>(places);
		List<Place> expectedSorting = new ArrayList<>(places);
		placesToSort.sort(Sorting.parsePlaceSort("accomodationType"));
		expectedSorting.sort((p1,p2)->
					Comparator.nullsLast((ac1,ac2)->((Accomodation) ac1).getType().compareTo(((Accomodation) ac2).getType()))
						.compare(p1.getAccomodation(), p2.getAccomodation()));
		assertEquals(expectedSorting, placesToSort);
	}
	
	
}
