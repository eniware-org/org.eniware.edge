/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.dao.jdbc;

import java.io.File;

/**
 * An API to expose system administration functions to the database.
 * 
 * The implementation of these methods are most often database vendor specific,
 * so this API provides a way for the EniwareEdge to access the information in a
 * generic way.
 * 
 * @version 1.0
 * @since 1.19
 */
public interface DatabaseSystemService {

	/**
	 * Get a set of "root" directories the database stores files on.
	 * 
	 * This method aims to support discovering how much space is available for
	 * the database system on the file system(s) it uses.
	 * 
	 * @return a list of directories
	 */
	File[] getFileSystemRoots();

	/**
	 * Get the size, in bytes, a specific database table consumes on disk.
	 * 
	 * @param schemaName
	 *        the schema of the table
	 * @param tableName
	 *        the table name
	 * @return the size, in bytes, the table consumes on disk
	 */
	long tableFileSystemSize(String schemaName, String tableName);

	/**
	 * Perform maintenance on a table, with the goal of freeing up resources
	 * that can be returned to the system.
	 * 
	 * @param schemaName
	 *        the schema of the table
	 * @param tableName
	 *        the table name
	 */
	void vacuumTable(String schemaName, String tableName);

}
