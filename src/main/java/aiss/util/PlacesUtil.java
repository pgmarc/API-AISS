package aiss.util;

import java.util.List;
import java.util.stream.Collectors;

import aiss.model.Place;

public class PlacesUtil {
	
	public static List<Place> getPagination(List<Place> places, 
			Integer limit, Integer offset) {
		return places.stream().skip(offset).limit(limit).collect(Collectors.toList());
		
 	}

}
