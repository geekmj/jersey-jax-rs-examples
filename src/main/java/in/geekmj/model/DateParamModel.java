package in.geekmj.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * 
 * @author geekmj
 * 
 * It is a class with one String attribute dateAsString. Method getDate
 * convert dateAsString to LocalDateTime. We will use it as a custom
 * Type for Request Parameter, Header, Path, Cookie and Matrix values.
 */
public class DateParamModel {

	private String dateAsString;

	public String getDateAsString() {
		return dateAsString;
	}

	public void setDateAsString(String dateAsString) {
		this.dateAsString = dateAsString;
	}

	public LocalDateTime getDate() {
		LocalDateTime dateTime = null;
		try {
			dateTime = LocalDateTime.parse(dateAsString, DateTimeFormatter.ISO_DATE_TIME);
		} catch (DateTimeParseException ex) {
			System.err.println("Conversion of dateAsString: " + dateAsString + " using ISO date time failed.");
		}
		try {
			dateTime = LocalDateTime.parse(dateAsString, DateTimeFormatter.ISO_DATE);
		} catch (DateTimeParseException ex) {
			System.err.println("Conversion of dateAsString: " + dateAsString + " using ISO date failed.");
		}

		return dateTime;
	}

}
