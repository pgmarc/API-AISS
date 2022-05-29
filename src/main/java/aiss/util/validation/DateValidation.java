package aiss.util.validation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateValidation {
	
	private static final String dateRgx = "^\\d\\d\\d\\d-(0?[1-9]|1[0-2])-(0?[1-9]|[12][0-9]|3[01])$";
	private static final String dateTimeRgx = "^\\d\\d\\d\\d-(0?[1-9]|1[0-2])-(0?[1-9]|[12][0-9]|3[01]) "
			+ "(00|[0-9]|1[0-9]|2[0-3]):([0-9]|[0-5][0-9])$";
	private static Pattern datePattern = Pattern.compile(dateRgx);
	private static Pattern dateTimePattern = Pattern.compile(dateTimeRgx);
	
	public static LocalDate parseLocalDate(String dateTime) {
		Matcher matcher = datePattern.matcher(dateTime);
		matcher.find();
		Integer year = Integer.valueOf(dateTime.substring(0, 4));
		Integer month = Integer.valueOf(matcher.group(1));
		Integer day = Integer.valueOf(matcher.group(2));

		LocalDate eventDate = LocalDate.of(year, month, day);

		return eventDate;
	}
	
	
	public static LocalDateTime parseLocalDateTime(String dateTime) {
		Matcher matcher = dateTimePattern.matcher(dateTime);
		matcher.find();
		Integer hour = Integer.valueOf(matcher.group(3));
		Integer minutes = Integer.valueOf(matcher.group(4));

		LocalDate eventDate = parseLocalDate(dateTime);
		LocalTime eventHour = LocalTime.of(hour, minutes);
		LocalDateTime dateAndHour = LocalDateTime.of(eventDate, eventHour);

		return dateAndHour;
	}
	
	public static boolean validDate(String date) {
		Matcher matcher = datePattern.matcher(date);
		return matcher.find();
	}
	
	public static boolean validDateTime(String dateTime) {
		Matcher matcher = dateTimePattern.matcher(dateTime);
		return matcher.find();
	}
	
	public static boolean isBeforeCurrentDate(LocalDateTime date) {
		return date.isBefore(LocalDateTime.now());
	}
	
	public static String currentDateFormated() {
		return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	
	public static String currentDatePlusMonthFormated() {
		return LocalDate.now().plusDays(30).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
}
