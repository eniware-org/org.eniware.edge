/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */
package org.eniware.edge;

import java.security.Principal;

/**
 * API for knowing information about the node's identity.
 * 
 * @author matt
 * @version 1.1
 */
public interface IdentityService {

	/**
	 * Get the ID of the current node.
	 * 
	 * @return node ID, or <em>null</em> if the ID is not known
	 */
	Long getNodeId();

	/**
	 * Get a {@link Principal} for the current node.
	 * 
	 * @return The node Principal, or <em>null</em> if none available.
	 */
	Principal getNodePrincipal();

	/**
	 * Get the host name for the SolarNet central service.
	 * 
	 * @return a host name
	 */
	String getSolarNetHostName();

	/**
	 * Get the host port for the SolarNet central service.
	 * 
	 * @return a host name
	 */
	Integer getSolarNetHostPort();

	/**
	 * Get the URL prefix for the SolarIn service.
	 * 
	 * @return a URL prefix
	 */
	String getSolarNetSolarInUrlPrefix();

	/**
	 * Return an absolute URL to the SolarIn service.
	 * 
	 * <p>
	 * This is a convenience method for generating a URL for the correct
	 * SolarNet host, SolarNet port, and SolarIn URL prefix as a single absolute
	 * URL string.
	 * </p>
	 * 
	 * @return SolarIn base URL
	 */
	String getSolarInBaseUrl();

}
