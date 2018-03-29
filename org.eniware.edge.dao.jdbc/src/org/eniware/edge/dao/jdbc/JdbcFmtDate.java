/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.dao.jdbc;

import java.util.Calendar;
import org.joda.time.ReadableInstant;
import org.joda.time.ReadablePartial;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.ift.DateCellProcessor;
import org.supercsv.cellprocessor.ift.StringCellProcessor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.util.CsvContext;

/**
 * Format dates using a Joda {@link DateTimeFormatter}.
 * 
 * @version 1.0
 */
public class JdbcFmtDate extends CellProcessorAdaptor implements DateCellProcessor {

	private static final String DATE_PATTERN = "yyyy-MM-dd";
	private static final String TIME_PATTERN = "HH:mm:ss.SSS";
	private static final String TIMESTAMP_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

	private final DateTimeFormatter dateFormatter;

	public JdbcFmtDate(DateTimeFormatter dateFormatter) {
		super();
		this.dateFormatter = dateFormatter;
	}

	public JdbcFmtDate(DateTimeFormatter dateFormatter, StringCellProcessor next) {
		super(next);
		this.dateFormatter = dateFormatter;
	}

	public static final class Timestamp extends JdbcFmtDate {

		public Timestamp() {
			super(DateTimeFormat.forPattern(TIMESTAMP_PATTERN).withZoneUTC());
		}

		public Timestamp(StringCellProcessor next) {
			super(DateTimeFormat.forPattern(TIMESTAMP_PATTERN).withZoneUTC(), next);
		}
	}

	public static final class Date extends JdbcFmtDate {

		public Date() {
			super(DateTimeFormat.forPattern(DATE_PATTERN));
		}

		public Date(StringCellProcessor next) {
			super(DateTimeFormat.forPattern(DATE_PATTERN), next);
		}
	}

	public static final class Time extends JdbcFmtDate {

		public Time() {
			super(DateTimeFormat.forPattern(TIME_PATTERN));
		}

		public Time(StringCellProcessor next) {
			super(DateTimeFormat.forPattern(TIME_PATTERN), next);
		}
	}

	@Override
	public Object execute(final Object value, final CsvContext context) {
		validateInputNotNull(value, context);

		String result;

		if ( value instanceof java.util.Date ) {
			result = dateFormatter.print(((java.util.Date) value).getTime());
		} else if ( value instanceof Calendar ) {
			result = dateFormatter.print(((Calendar) value).getTimeInMillis());
		} else if ( value instanceof ReadableInstant ) {
			result = dateFormatter.print((ReadableInstant) value);
		} else if ( value instanceof ReadablePartial ) {
			result = dateFormatter.print((ReadablePartial) value);
		} else {
			throw new SuperCsvCellProcessorException(java.util.Date.class, value, context, this);
		}

		return next.execute(result, context);
	}

}
