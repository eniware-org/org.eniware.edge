/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.dao.jdbc;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.ICsvWriter;

/**
 * Write JDBC {@link ResultSet} instances as CSV data.
 * 
 * CSV column headers are generated from column names provided by the
 * {@code ResultSet} itself, and their order matches the order of columns in the
 * {@code ResultSet}.
 * 
 * @version 1.0
 * @since 1.17
 */
public interface JdbcResultSetCsvWriter extends ICsvWriter {

	/**
	 * Export a {@link ResultSet} as CSV data.
	 * 
	 * @param resultSet
	 *        The {@code ResultSet} to write.
	 * @throws SQLException
	 *         If any SQL error occurs.
	 * @throws IOException
	 *         If any IO error occurs.
	 */
	void write(ResultSet resultSet) throws SQLException, IOException;

	/**
	 * Export a {@link ResultSet} as CSV data, using cell processors.
	 * 
	 * @param resultSet
	 *        The {@code ResultSet} to write.
	 * @param cellProcessors
	 *        An array of cell processors to handle each exported column. The
	 *        length of the array should match the number and order of columns
	 *        in the {@code ResultSet}. {@code null} values are permitted and
	 *        indicate no processing should be performed on that column.
	 * @throws SQLException
	 *         If any SQL error occurs.
	 * @throws IOException
	 *         If any IO error occurs.
	 */
	void write(ResultSet resultSet, CellProcessor[] cellProcessors) throws SQLException, IOException;

}
