package aiss.model.repository;

import java.util.Collection;

import java.util.HashMap;
import java.util.Map;

import aiss.model.Coordinates;
import aiss.model.Place;
import aiss.model.Event;

public class MapApplicationRepository implements PlaylistRepository, EventsRepository {

	private Map<Integer, Place> placesMap;
	private static MapApplicationRepository instance = null;
	private Integer placeIndex = 0;
	
	public static void main(String[] args) {
		System.out.println(getInstance().placesMap.get(2));
	}
	
	public void init() {
		this.placesMap = new HashMap<Integer, Place>();
		/*Twimm,ccaddick3@taobao.com,616 Talisman Terrace,1,http://quantcast.com,52.1584604,20.9110916
		Jazzy,rnairne4@un.org,09 Montana Place,4,https://reference.com,-42.7700601,-65.0306302
		Voomm,fsnoday5@51.la,102 Elka Terrace,0,http://wisc.edu,62.2666019,27.1252002
		Babbleset,csarch6@globo.com,1827 Manufacturers Road,1,http://diigo.com,38.1748383,20.5829927*/
		
		Place place1 = new Place();
		place1.setName("Quatz");
		place1.setEmail("wzaniolini0@amazonaws.com");
		place1.setAddress("6086 Morrow Park");
		place1.setRating(4);
		place1.setWebsite("https://goo.gl");
		place1.setLocation(Coordinates.of(48.8466523,2.2582125));
		addPlace(place1);

		
		Place place2 = new Place();
		place2.setName("Demivee");
		place2.setEmail("dcoughlin1@hhs.gov");
		place2.setAddress("7347 Graceland Road");
		place2.setRating(3);
		place2.setWebsite(",http://deliciousdays.com");
		place2.setLocation(Coordinates.of(11.5399857,-85.6986957));
		addPlace(place2);
		
		Place place3 = new Place();
		place3.setName("Aimbu");
		place3.setEmail("ocrigane2@alexa.com");
		place3.setAddress("36581 Hauk Point");
		place3.setRating(0);
		place3.setWebsite("https://sourceforge.netm");
		place3.setLocation(Coordinates.of(42.8043197,132.8288963));
		addPlace(place3);
	}
	
	public static MapApplicationRepository getInstance() {
		
		if (instance == null) {
			instance = new MapApplicationRepository();
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
