package aiss.model.repository;

import java.util.Collection;

import aiss.model.Place;


public interface PlaceRepository {
	
	public void addPlace(Place place);
	public Collection<Place> getAllPlaces();
	public Place getPlace(Integer placeId);
	public void updatePlace(Place place);
	public void deletePlace(Integer placeId);
}
