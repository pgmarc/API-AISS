package aiss.model;

import org.codehaus.jackson.annotate.JsonCreator;

import aiss.util.EnumValidator;

public enum PlaceCategory {
	SUPERMARKET,
	PHARMACY,
	ATM,
	BUSINESS,
	BAR, 
	RESTAURANT,
	CHURCH,
	CINEMA,
	PARK,
	NIGHT_CLUB,
	MUSEUM,
	UNIVERSITY,
	GYM,
	CONGRESS,
	HOSPITAL,
	POLICE,
	TOURIST_ATTRACTION,
	ACCOMMODATION,
	STADIUM,
	GAS_STATION,
	TRAIN_STATION,
	BUS_STATION,
	AIRPORT,
	PARKING,
	TAXI_STAND,
	SUBWAY_STATION,
	UNDEFINED;
	
	@JsonCreator
	public static PlaceCategory fromString(String key) {
		
		String newKey = key.toUpperCase();
		return key == null || !EnumValidator.isValidEnum(PlaceCategory.class, newKey)?
				null : PlaceCategory.valueOf(key.toUpperCase());
	} 
}
