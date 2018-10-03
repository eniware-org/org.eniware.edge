/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge;

/**
 * API for a standardized way of identifying services, to support configuring
 * links to specific instances of a service at runtime. Many managed services in
 * EniwareEdge allow any number of them to be deployed.
 * 
 * @version 1.0
 */
public interface Identifiable {

	/**
	 * Get a unique identifier for this service. This should be meaningful to
	 * the service implementation.
	 * 
	 * @return unique identifier (should never be <em>null</em>)
	 */
	String getUID();

	/**
	 * Get a grouping identifier for this service. This should be meaningful to
	 * the service implementation.
	 * 
	 * @return a group identifier, or <em>null</em> if not part of any group
	 */
	String getGroupUID();

}
