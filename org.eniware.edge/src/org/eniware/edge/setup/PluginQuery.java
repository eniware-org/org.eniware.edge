/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.setup;

/**
 * API for querying and filtering available plugins.
 * 
 * @author matt
 * @version 1.0
 */
public interface PluginQuery {

	/**
	 * If <em>true</em> then only return the latest (highest) version available
	 * for any given plugin.
	 * 
	 * @return boolean
	 */
	boolean isLatestVersionOnly();

	/**
	 * Get a simple substring-matching query. This is the query as provided by a
	 * user. The application can apply this query in whatever method is most
	 * appropriate to return appropriate results.
	 * 
	 * @return a simple query string
	 */
	String getSimpleQuery();

}
