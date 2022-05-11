package aiss.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class GeographicalDistanceTest {

	@Test
	public void harversineFormulaTest() { 
		Coordinates torreDelOro = Coordinates.of(37.38240, -5.99632);
		Coordinates puertaDelSol = Coordinates.of(40.41694, -3.70361);
		Double distanceExpected = 391300.0;
		Double calculatedDistance = GeographicalDistance.geographicalDistance(torreDelOro, puertaDelSol);
		assertEquals(distanceExpected, calculatedDistance, 500);
	}
}
