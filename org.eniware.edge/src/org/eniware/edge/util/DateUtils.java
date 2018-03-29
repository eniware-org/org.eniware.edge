/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.util;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Utility functions for dealing with dates and times.
 * 
 * @version 1.0
 */
public final class DateUtils {

	private static final DateTimeFormatter LOCAL_TIME_FORMATTER = DateTimeFormat.forPattern("HH:mm");
	private static final DateTimeFormatter LOCAL_DATE_FORMATTER = DateTimeFormat
			.forPattern("yyyy-MM-dd");

	private DateUtils() {
		// can't construct me
	}

	/**
	 * Parse a standard local time value, in {@code HH:mm} form.
	 * 
	 * @param value
	 *        the time value
	 * @return the LocalTime object
	 */
	public static LocalTime parseLocalTime(String value) {
		return LOCAL_TIME_FORMATTER.parseLocalTime(value);
	}

	/**
	 * Format a standard local time value, in {@code HH:mm} form.
	 * 
	 * @param value
	 *        the LocalTime to format
	 * @return the formatted value
	 */
	public static String format(LocalTime value) {
		return LOCAL_TIME_FORMATTER.print(value);
	}

	/**
	 * Parse a standard local date value, in {@code yyyy-MM-dd} form.
	 * 
	 * @param value
	 *        the date value
	 * @return the LocalDate object
	 */
	public static LocalTime parseLocalDate(String value) {
		return LOCAL_DATE_FORMATTER.parseLocalTime(value);
	}

	/**
	 * Format a standard local date value, in {@code yyyy-MM-dd} form.
	 * 
	 * @param value
	 *        the LocalDate to format
	 * @return the formatted value
	 */
	public static String format(LocalDate value) {
		return LOCAL_DATE_FORMATTER.print(value);
	}

}
