package in.geekmj.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * 
 * @author geekmj
 * DateParamModel is a class with a constructor with one String Argument
 * We can use it as custom type for mapping request parameters, cookies or headers values 
 */
public class ConstructorDateParamModel {

	private String dateAsString;

	/* Expecting ISO-8601 format date string */
	public ConstructorDateParamModel(String dateAsString) {
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
