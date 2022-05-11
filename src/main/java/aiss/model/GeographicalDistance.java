package aiss.model;

public class GeographicalDistance {
	
	private static final Double EARTH_RADIUS = 6371008.8;
	
	public static Double geographicalDistance(Coordinates source, Coordinates target) {
		
		Double sourceLatitude = decimalDegreesToRadians(source.getLatitude());
		Double targetLatitude = decimalDegreesToRadians(target.getLatitude());
		Double latitudeIncrement = decimalDegreesToRadians(source.getLatitude() - target.getLatitude());
		Double longitudeIncrement = decimalDegreesToRadians(source.getLongitude() - target.getLongitude());
		
		Double halfChordLength = harversine(latitudeIncrement) + 
				Math.cos(sourceLatitude) * Math.cos(targetLatitude) *
				harversine(longitudeIncrement);
		
		Double angularDistance = 2 * Math.atan2(Math.sqrt(halfChordLength), Math.sqrt(1 - halfChordLength));
		return EARTH_RADIUS * angularDistance;
	}

	private static Double decimalDegreesToRadians(Double decimalDegree) {
		return Math.toRadians(decimalDegree);
	}
	
	private static Double harversine(Double angle) {
		return Math.pow(Math.sin(angle / 2), 2);
	}
}
