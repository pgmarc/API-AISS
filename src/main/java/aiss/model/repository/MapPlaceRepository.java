package aiss.model.repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


import aiss.model.Accomodation;
import aiss.model.Coordinates;
import aiss.model.Place;
import aiss.model.Review;
import aiss.util.PlaceData;

public class MapPlaceRepository implements PlaceRepository{

	private Map<Integer, Place> placesMap;
	private static MapPlaceRepository instance = null;
	private Integer placeIndex = 0;
	
	public void init() {
		
		this.placesMap = new HashMap<Integer, Place>();
		
		PlaceData.getPlacesInfo().forEach(place -> addPlace(place));
		
		Review review1 = new Review("Zarzu","Todo perfecto",4.5);
	
		Review review2 = new Review("pgmarc","Sitio perfecto para estar en familia",2.0);
		
		Review review3 = new Review();
		review3.setUsername("Beni135");
		review3.setDescription("Un sitio muy agradable, gran servicio y increibles precios");
		review3.setRating(4.5);
		
		Review review4 = new Review();
		review4.setUsername("Killerpro");
		review4.setDescription("De las mejores cervezas de la zona por un precio asequible,"
				+ " ademas tiene un pequeño menú por si entra hambre");
		review4.setRating(4.0);
		
		Review review5 = new Review();
		review5.setUsername("Pablo456");
		review5.setDescription("Un sitio agradable, no es el mejor en su "
				+ "categoria pero no está mal");
		review5.setRating(2.5);
		
		Review review6 = new Review();
		review6.setUsername("Jaime");
		review6.setDescription("Un pesimo servicio y precios desorbitados, "
				+ "ademas una localización espantosa en medio de la nada");
		review6.setRating(0.);
		
		addReview(0, review1);
		addReview(1, review2);
		addReview(2, review3);
		addReview(3, review4);
		addReview(4, review5);
		addReview(5, review6);
		addReview(6, review1);
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
		place.setReviews(new HashMap<Integer, Review>());
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
		return placesMap.get(placeId).getReviews().values().stream().collect(Collectors.toList());
	}
	
	@Override
	public Review getReview(Integer placeId, Integer reviewId) {
		return placesMap.get(placeId).getReviews().get(reviewId);
	}

	@Override
	public void updateReview(Integer placeId, Review review) {
		placesMap.get(placeId).getReviews().put(review.getId(), review);
	}

	@Override
	public void deleteReview(Integer placeId, Integer reviewId) {
		placesMap.get(placeId).getReviews().remove(reviewId);
	}

	//ACCOMODATION
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
