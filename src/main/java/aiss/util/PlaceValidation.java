package aiss.util;

import aiss.model.Coordinates;

public class PlaceValidation {
	
	public static boolean validCoordinates(Coordinates coordinates) {
		if (!validLatitude(coordinates.getLatitude()) 
				|| !validLongitude(coordinates.getLongitude())) {
			return false;
		}
		
		return true;
	}
	
	public static boolean validLatitude(Double latitude) {
		Double latitudeAbs = Math.abs(latitude);
		return 0 <= latitudeAbs && latitudeAbs <= 90;  
	}
	
	public static boolean validLongitude(Double longitude) {
		Double longitudeAbs = Math.abs(longitude);
		return 0 <= longitudeAbs && longitudeAbs <= 180;  
	}
}
