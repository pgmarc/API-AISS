package aiss.util;

import static org.junit.Assert.*;

import org.junit.Test;

import aiss.model.Coordinates;
import aiss.util.validation.PlaceCoordinatesValidation;

public class PlaceValidationTest {

	@Test
	public void ShouldGetValidCoordinates() {
		Coordinates coordinates =  Coordinates.of(-40.0, 40.0);
		boolean expected = true;
		boolean result = PlaceCoordinatesValidation.validCoordinates(coordinates);
		assertEquals(expected, result);
	}
	
	@Test
	public void ShouldGetInvalidalidCoordinatesWrongLatitude() {
		Coordinates coordinates =  Coordinates.of(-500.0, 40.0);
		boolean expected = false;
		boolean result = PlaceCoordinatesValidation.validCoordinates(coordinates);
		assertEquals(expected, result);
	}

	@Test
	public void ShouldGetInvalidalidCoordinatesWrongLongitude() {
		Coordinates coordinates =  Coordinates.of(40.0, -360.0);
		boolean expected = false;
		boolean result = PlaceCoordinatesValidation.validCoordinates(coordinates);
		assertEquals(expected, result);
	}
	
	@Test
	public void ShouldGetInvalidalidCoordinatesBothValues() {
		Coordinates coordinates =  Coordinates.of(-200.0, 200.0);
		boolean expected = false;
		boolean result = PlaceCoordinatesValidation.validCoordinates(coordinates);
		assertEquals(expected, result);
	}
}
