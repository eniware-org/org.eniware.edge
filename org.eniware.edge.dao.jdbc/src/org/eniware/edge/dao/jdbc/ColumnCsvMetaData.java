/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.dao.jdbc;

import org.supercsv.cellprocessor.ift.CellProcessor;

/**
 * Metadata about a JDBC table column for use in CSV processing.
 * 
 * @version 1.0
 * @since 1.17
 */
public class ColumnCsvMetaData {

	private final String columnName;
	private final CellProcessor cellProcessor;
	private final int sqlType;
	private final boolean primaryKey;

	/**
	 * Construct as a non-primary key column.
	 * 
	 * @param columnName
	 *        The column name.
	 * @param cellProcessor
	 *        The cell processor to use when parsing CSV data for this column.
	 * @param sqlType
	 *        The JDBC {@link java.sql.Types} value to use for this column.
	 */
	public ColumnCsvMetaData(String columnName, CellProcessor cellProcessor, int sqlType) {
		this(columnName, cellProcessor, sqlType, false);
	}

	/**
	 * Constructor.
	 * 
	 * @param columnName
	 *        The column name.
	 * @param cellProcessor
	 *        The cell processor to use when parsing CSV data for this column.
	 * @param sqlType
	 *        The JDBC {@link java.sql.Types} value to use for this column.
	 * @param primaryKey
	 *        {@code true} if this is a primary key column.
	 */
	public ColumnCsvMetaData(String columnName, CellProcessor cellProcessor, int sqlType,
			boolean primaryKey) {
		super();
		this.columnName = columnName;
		this.cellProcessor = cellProcessor;
		this.sqlType = sqlType;
		this.primaryKey = primaryKey;
	}

	/**
	 * Get the JDBC column name.
	 * 
	 * @return The column name.
	 */
	public String getColumnName() {
		return columnName;
	}

	/**
	 * Get a {@code CellProcessor} to use with the associated CSV conversion.
	 * 
	 * @return A cell processor.
	 */
	public CellProcessor getCellProcessor() {
		return cellProcessor;
	}

	/**
	 * Get the JDBC column type, from the {@link java.sql.Types} class.
	 * 
	 * @return The JDBC column type.
	 */
	public int getSqlType() {
		return sqlType;
	}

	/**
	 * Flag if this column is part of the table's primary key.
	 * 
	 * @return {@code true} if part of the table's primary key.
	 */
	public boolean isPrimaryKey() {
		return primaryKey;
	}

	/**
	 * Get a copy of this object with the primary key flag set to {@code true}.
	 * 
	 * @return A new column metadata instance.
	 */
	public ColumnCsvMetaData asPrimaryKeyColumn() {
		return new ColumnCsvMetaData(columnName, cellProcessor, sqlType, true);
	}

}
