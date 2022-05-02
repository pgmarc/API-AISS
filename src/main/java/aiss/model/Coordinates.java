package aiss.model;

import java.util.Objects;

public class Coordinates {
	
	private final Double latitude;
	private final Double longitude;
	
	private Coordinates(Double latitude, Double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public static Coordinates of(Double latitude, Double longitude) {
		return new Coordinates(latitude, longitude);
	}

	public Double getLatitude() {
		return latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	@Override
	public String toString() {
		return "Coordinates [latitude=" + latitude + ", longitude=" + longitude + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(latitude, longitude);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinates other = (Coordinates) obj;
		return Objects.equals(latitude, other.latitude) && Objects.equals(longitude, other.longitude);
	}
	
	

}
