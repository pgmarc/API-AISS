package aiss.util.validation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateValidation {
	
	private static final String dateTimeRgx = "^\\d\\d\\d\\d-(0?[1-9]|1[0-2])-(0?[1-9]|[12][0-9]|3[01]) "
			+ "(00|[0-9]|1[0-9]|2[0-3]):([0-9]|[0-5][0-9])$";
	private static Pattern dateTimePattern = Pattern.compile(dateTimeRgx);
	
	public static LocalDateTime parseDateTime(String dateTime) {
		Matcher matcher = dateTimePattern.matcher(dateTime);
		matcher.find();
		Integer year = Integer.valueOf(dateTime.substring(0, 4));
		Integer month = Integer.valueOf(matcher.group(1));
		Integer day = Integer.valueOf(matcher.group(2));
		Integer hour = Integer.valueOf(matcher.group(3));
		Integer minutes = Integer.valueOf(matcher.group(4));

		LocalDate eventDate = LocalDate.of(year, month, day);
		LocalTime eventHour = LocalTime.of(hour, minutes);
		LocalDateTime dateAndHour = LocalDateTime.of(eventDate, eventHour);

		return dateAndHour;
	}
	
	public static boolean validDateTime(String dateTime) {
		Matcher matcher = dateTimePattern.matcher(dateTime);
		return matcher.find();
	}
	
	public static boolean validBothDate(String startDate, String endDate) {
		return validDateTime(startDate) && validDateTime(endDate);
	}
	
	public static boolean isBeforeCurrentDate(String dateString) {
		LocalDateTime currentDate = LocalDateTime.now();
		LocalDateTime date = parseDateTime(dateString);
		return date.isBefore(currentDate);
	}
}
