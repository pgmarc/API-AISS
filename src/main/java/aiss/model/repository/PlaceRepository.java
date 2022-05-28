package aiss.model.repository;

import java.util.Collection;
import java.util.List;

import aiss.model.Accommodation;
import aiss.model.AccommodationPayment;
import aiss.model.Place;
import aiss.model.Review;


public interface PlaceRepository {
	
	public void addPlace(Place place);
	public Collection<Place> getAllPlaces();
	public Place getPlace(Integer placeId);
	public void updatePlace(Place place);
	public void deletePlace(Integer placeId);
	public Collection<Place> getPlacesOnRadius(Integer placeId, Double minRadius, Double maxRadius);
	
	public List<Review> getAllReviews(Integer placeId);
	public Review getReview(Integer placeId, Integer reviewId);
	public void addReview(Integer placeId, Review review);
	public void updateReview(Integer placeId, Review review);
	public void deleteReview(Integer placeId, Integer reviewId);
	
	public Accommodation getAccommodation(Integer placeId);
	public void addAccommodation(Integer placeId, Accommodation accommodation);
	public void updateAccommodation(Integer placeId, Accommodation accommodation);
	public void deleteAccommodation(Integer placeId);
	
	public AccommodationPayment getAccommodationPayment(Integer placeId, Integer paymentId);
	public void addAccommodationPayment(Integer placeId, AccommodationPayment payment);
	public void updateAccommodationPayment(Integer placeId, Integer paymentId, AccommodationPayment payment);
	public void deleteAccommodationPayment(Integer placeId, Integer paymentId);
}
