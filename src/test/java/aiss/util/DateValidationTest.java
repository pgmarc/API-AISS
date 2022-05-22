package aiss.util;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;

public class DateValidationTest {

	@Test
	public void ShouldGetValidDate() {
		String date = "2022-7-7 15:30:00";
		LocalDateTime expected = LocalDateTime.of(2022, 7, 7, 15, 30);
		LocalDateTime resutl = DateValidation.LocalDate(date);
		assertEquals(expected, resutl);
	}

}
