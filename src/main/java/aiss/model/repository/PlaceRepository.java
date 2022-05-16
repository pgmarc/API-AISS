package aiss.model.repository;

import java.util.Collection;

import aiss.model.Accomodation;
import aiss.model.Place;
import aiss.model.Review;


public interface PlaceRepository {
	
	public void addPlace(Place place);
	public Collection<Place> getAllPlaces();
	public Place getPlace(Integer placeId);
	public void updatePlace(Place place);
	public void deletePlace(Integer placeId);
	public Collection<Place> getPlacesOnRadius(Integer placeId, Double minRadius, Double maxRadius);
	
	public Collection<Review> getAllReviews(Integer placeId);
	public Review getReview(Integer placeId, Integer reviewId);
	public void addReview(Integer placeId, Review review);
	public void updateReview(Integer placeId, Review review);
	public void deleteReview(Integer placeId, Integer reviewId);
	
	public Accomodation getAccomodation(Integer placeId);
	public void addAccomodation(Integer placeId, Accomodation accomodation);
	public void updateAccomodation(Integer placeId, Accomodation accomodation);
	public void deleteAccomodation(Integer placeId);
}
