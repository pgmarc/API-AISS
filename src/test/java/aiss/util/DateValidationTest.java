package aiss.util;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;

import aiss.util.validation.DateValidation;

public class DateValidationTest {

	@Test
	public void ShouldGetValidDate() {
		String date = "2022-07-07 15:30";
		LocalDateTime expected = LocalDateTime.of(2022, 7, 7, 15, 30);
		LocalDateTime result = DateValidation.parseLocalDateTime(date);
		assertEquals(expected, result);
	}
	
	@Test
	public void ShouldGetInValidDate() {
		String date = "2022-07-07 24:30";
		boolean result = DateValidation.validDateTime(date);
		assertFalse(result);
	}
	
	@Test
	public void ShouldGetValidDateNoLeadingZerosMonthAndDay() {
		String date = "2022-7-7 15:30";
		boolean result = DateValidation.validDateTime(date);
		assertTrue(result);
	}
	
	@Test
	public void ShouldGetInvalidDateDateOverflow() {
		String date = "2022-7-74 15:30";
		boolean result = DateValidation.validDateTime(date);
		assertFalse(result);
	}
	
	@Test
	public void ShouldGetInvalidDateMonthOverflow() {
		String date = "2022-13-15 15:30";
		boolean result = DateValidation.validDateTime(date);
		assertFalse(result);
	}
}
