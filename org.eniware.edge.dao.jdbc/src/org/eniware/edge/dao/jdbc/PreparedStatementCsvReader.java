/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.dao.jdbc;

import java.io.IOException;
import java.io.Reader;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.AbstractCsvReader;
import org.supercsv.io.ITokenizer;
import org.supercsv.prefs.CsvPreference;

/**
 * Implementation of {@link JdbcPreparedStatementCsvReader}.
 * 
 * @version 1.0
 * @since 1.17
 */
public class PreparedStatementCsvReader extends AbstractCsvReader
		implements JdbcPreparedStatementCsvReader {

	public PreparedStatementCsvReader(ITokenizer tokenizer, CsvPreference preferences)
			throws SQLException {
		super(tokenizer, preferences);
	}

	public PreparedStatementCsvReader(Reader reader, CsvPreference preferences) throws SQLException {
		super(reader, preferences);
	}

	@Override
	public boolean read(PreparedStatement stmt, Map<String, Integer> csvColumns,
			CellProcessor[] cellProcessors, Map<String, ColumnCsvMetaData> columnMetaData)
			throws SQLException, IOException {
		final List<Object> processed = new ArrayList<Object>(csvColumns.size());
		if ( readRow() ) {
			List<?> columnValues;
			if ( cellProcessors != null ) {
				executeProcessors(processed, cellProcessors);
				columnValues = processed;
			} else {
				columnValues = getColumns();
			}
			int i = 1;
			final Calendar utcCalendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
			for ( Map.Entry<String, ColumnCsvMetaData> me : columnMetaData.entrySet() ) {
				Integer csvColumnIndex = csvColumns.get(me.getKey());
				Object columnValue = (csvColumnIndex == null ? null : columnValues.get(csvColumnIndex));
				final int sqlType = me.getValue().getSqlType();
				if ( columnValue == null ) {
					stmt.setNull(i, sqlType);
				} else if ( columnValue instanceof java.sql.Date ) {
					stmt.setDate(i, (java.sql.Date) columnValue, utcCalendar);
				} else if ( columnValue instanceof java.sql.Time ) {
					stmt.setTime(i, (java.sql.Time) columnValue, utcCalendar);
				} else if ( columnValue instanceof java.sql.Timestamp ) {
					stmt.setTimestamp(i, (java.sql.Timestamp) columnValue, utcCalendar);
				} else {
					stmt.setObject(i, columnValue, sqlType);
				}
				i++;
			}
			return true;
		}
		return false;
	}

}
