package aiss.model.repository;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;

import aiss.model.Accomodation;
import aiss.model.Coordinates;
import aiss.model.Place;
import aiss.model.Review;
import aiss.util.FileReader;

public class MapPlaceRepository implements PlaceRepository{

	private Map<Integer, Place> placesMap;
	private static MapPlaceRepository instance = null;
	private Integer placeIndex = 0;
	
	public void init() {
		
		this.placesMap = new HashMap<Integer, Place>();
		/*
		Voomm,fsnoday5@51.la,102 Elka Terrace,0,http://wisc.edu,62.2666019,27.1252002
		Babbleset,csarch6@globo.com,1827 Manufacturers Road,1,http://diigo.com,38.1748383,20.5829927*/
		List<Place> places = FileReader.readPlacesFromCSV("files/places.csv");
		for (Place place: places) {
			addPlace(place);
		}
		InputStream a = ServletContext.class.getResourceAsStream("resources/places.csv");
		
		Place place1 = new Place();
		place1.setName("Quatz");
		place1.setEmail("wzaniolini0@amazonaws.com");
		place1.setAddress("6086 Morrow Park");
		place1.setWebsite("https://goo.gl");
		place1.setLocation(Coordinates.of(48.8466523,2.2582125));
		addPlace(place1);

		Place place2 = new Place();
		place2.setName("Demivee");
		place2.setEmail("dcoughlin1@hhs.gov");
		place2.setAddress("7347 Graceland Road");
		place2.setWebsite(",http://deliciousdays.com");
		place2.setLocation(Coordinates.of(11.5399857,-85.6986957));
		addPlace(place2);
		
		Place place3 = new Place();
		place3.setName("Aimbu");
		place3.setEmail("ocrigane2@alexa.com");
		place3.setAddress("36581 Hauk Point");
		place3.setWebsite("https://sourceforge.netm");
		place3.setLocation(Coordinates.of(42.8043197,132.8288963));
		addPlace(place3);
		

		Review review1 = new Review("Zarzu","Todo perfecto",4.5);
		
		addReview(place1.getId(),review1);
		addReview(place2.getId(),review1);
		addReview(place3.getId(),review1);

		Place place4 = new Place();
		place4.setName("Twimm");
		place4.setEmail("ccaddick3@taobao.com");
		place4.setAddress("616 Talisman Terrace");
		place4.setWebsite("http://quantcast.com");
		place4.setLocation(Coordinates.of(52.1584604,20.9110916));
		addPlace(place4);
		
		Place place5 = new Place();
		place5.setName("Jazzy");
		place5.setEmail("rnairne4@un.org");
		place5.setAddress("09 Montana Place");
		place5.setWebsite("https://reference.com");
		place5.setLocation(Coordinates.of(-42.7700601,-65.0306302));
		addPlace(place5);
		
	}
	
	public static MapPlaceRepository getInstance() {
		
		if (instance == null) {
			instance = new MapPlaceRepository();
			instance.init();
		}
		return instance;
	}
	
	@Override
	public void addPlace(Place place) {
		place.setId(placeIndex);
		placesMap.put(place.getId(), place);
		placeIndex++;
	}

	@Override
	public Collection<Place> getAllPlaces() {
		return placesMap.values();
	}

	@Override
	public Place getPlace(Integer placeId) {
		return placesMap.get(placeId);
	}

	@Override
	public void updatePlace(Place place) {
		placesMap.put(place.getId(), place);
		
	}

	@Override
	public void deletePlace(Integer placeId) {
		placesMap.remove(placeId);
	}
	
	@Override
	public Collection<Place> getPlacesOnRadius(Integer placeId, Double minRadius,
			Double maxRadius) {
		
		List<Place> places = this.placesWithoutLocation(placeId);
		Coordinates currentLocation = this.getPlace(placeId).getLocation();
		Double minimunRadius = Optional.ofNullable(minRadius).orElse(0.0);
		Double maximunRadius = Optional.ofNullable(maxRadius).orElse(Double.MAX_VALUE);
		return places.stream()
				.filter(place -> 
				minimunRadius <= Place.getDistance(currentLocation, place.getLocation())
				&& Place.getDistance(currentLocation, place.getLocation()) <= maximunRadius)
				.collect(Collectors.toList());
	}
	
	private List<Place> placesWithoutLocation(Integer placeId) {
		return this.placesMap.entrySet().stream().filter(place -> place.getKey() != placeId)
				.map(place -> place.getValue()).collect(Collectors.toList());
	}

	//REVIEWS
	@Override
	public void addReview(Integer placeId, Review review) {
		review.setDate(LocalDateTime.now());
		placesMap.get(placeId).addReview(review);
	}

	@Override
	public List<Review> getAllReviews(Integer placeId) {
		return placesMap.get(placeId).getReviews();
	}
	
	@Override
	public Review getReview(Integer placeId, Integer reviewId) {
		return placesMap.get(placeId).getReviews().get(reviewId);
	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public void updateReview(Integer placeId, Review review) {
		placesMap.get(placeId).getReviews().remove(review.getId());
		placesMap.get(placeId).getReviews().add(review.getId(), review);
	}

	@Override
	public void deleteReview(Integer placeId, Integer reviewId) {
		Review reviewToDelete = placesMap.get(placeId).getReviews().get(reviewId);
		placesMap.get(placeId).getReviews().remove(reviewToDelete);
	}

	@Override
	public Accomodation getAccomodation(Integer placeId) {
		return placesMap.get(placeId).getAccomodation();
	}

	@Override
	public void addAccomodation(Integer placeId, Accomodation accomodation) {
		placesMap.get(placeId).setAccomodation(accomodation);
		
	}

	@Override
	public void updateAccomodation(Integer placeId, Accomodation accomodation) {
		placesMap.get(placeId).setAccomodation(accomodation);
	}

	@Override
	public void deleteAccomodation(Integer placeId) {
		placesMap.get(placeId).setAccomodation(null);
	}


}
