package aiss.model.repository;

import java.util.Collection;

import aiss.model.Place;
import aiss.model.Review;


public interface PlaceRepository {
	
	public void addPlace(Place place);
	public Collection<Place> getAllPlaces();
	public Place getPlace(Integer placeId);
	public void updatePlace(Place place);
	public void deletePlace(Integer placeId);
	public void addReview(Integer placeId, Review review);
	public Review getReview(Integer placeId, Integer reviewId);
}
