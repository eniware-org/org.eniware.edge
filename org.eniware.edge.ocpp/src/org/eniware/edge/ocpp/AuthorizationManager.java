/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.ocpp;

import net.solarnetwork.node.Identifiable;
import ocpp.v15.cs.AuthorizationStatus;

/**
 * API to handle OCPP authorization functionality, which may include local
 * caching and/or synchronization with the OCPP central system.
 * 
 * @author matt
 * @version 1.1
 */
public interface AuthorizationManager extends Identifiable {

	/**
	 * Request authorization of a specific ID tag value.
	 * 
	 * @param idTag
	 *        The ID tag value to authorize.
	 * @return <em>true</em> if the tag is authorized, <em>false</em> otherwise.
	 * @throws OCPPException
	 *         if the authorization status cannot be determined, e.g. unable to
	 *         communicate with server
	 */
	AuthorizationStatus authorize(String idTag);

}
