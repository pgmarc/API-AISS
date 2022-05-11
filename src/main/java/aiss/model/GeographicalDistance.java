package aiss.model;

public class GeographicalDistance {
	
	private static final Double EARTH_RADIUS = 6371008.8;
	
	
	public static Double geographicalDistance(Coordinates source, Coordinates target) {
		
		Double sourceLatitude = decimalDegreesToRadians(source.getLatitude());
		Double targetLatitude = decimalDegreesToRadians(target.getLatitude());
		Double latitudeIncrement = decimalDegreesToRadians(source.getLatitude() - target.getLatitude());
		Double longitudeIncrement = decimalDegreesToRadians(source.getLongitude() - target.getLongitude());
		
		Double halfChordLength = Math.pow(Math.sin(latitudeIncrement / 2), 2) + 
				Math.cos(sourceLatitude) * Math.cos(targetLatitude) *
				Math.pow(Math.sin(longitudeIncrement / 2), 2);
		
		Double angularDistance = 2 * Math.atan2(Math.sqrt(halfChordLength), Math.sqrt(1 - halfChordLength));
		return EARTH_RADIUS * angularDistance;
	}

	private static Double decimalDegreesToRadians(Double decimalDegree) {
		return Math.toRadians(decimalDegree);
	}
	
//	private static Double harversine(Double angle) {
//		return Math.pow(Math.sin(angle / 2), 2);
//	}
	
	public static void main(String[] args) {
		Double latitudeSeville = 37.36784771881383;
		Double longitudSeville =  -5.973401949040638;
		Double latitudePuertaSol = 40.41693317360963;
		Double longitudePuertaSol = -3.7035548586487885;
		Coordinates seville = Coordinates.of(latitudeSeville, longitudSeville);
		Coordinates huelva = Coordinates.of(latitudePuertaSol, longitudePuertaSol);
		
		Double distanceV1 = geographicalDistance(seville, huelva);

		System.out.println("Distance V1: " + distanceV1);
		System.out.println(Math.toRadians(-90));
	}

}
