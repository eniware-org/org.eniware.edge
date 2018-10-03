/* ===================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ===================================================================
 */

package org.eniware.edge.dao.jdbc;

import org.springframework.context.MessageSource;

/**
 * API for JDBC-based DAO implemtnations.
 * 
 * @version $Revision$ $Date$
 */
public interface JdbcDao {

	/**
	 * Get the database schema name this DAO is working with.
	 * 
	 * @return database schema name
	 */
	String getSchemaName();

	/**
	 * Get the primary database table name this DAO is working with.
	 * 
	 * @return primary database table name
	 */
	String getTableName();

	/**
	 * Get the database table names this DAO is working with.
	 * 
	 * <p>
	 * If a DAO manages more than one table, this should return all the table
	 * names.
	 * </p>
	 * 
	 * @return database table name
	 */
	String[] getTableNames();

	/**
	 * Get a MessageSource to resolve messages with related to this DAO.
	 * 
	 * @return message source
	 */
	MessageSource getMessageSource();

}
