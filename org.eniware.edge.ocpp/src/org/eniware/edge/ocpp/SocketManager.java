/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */
package org.eniware.edge.ocpp;

import org.eniware.edge.Identifiable;

/**
 * API for something that manages the on/off state of OCPP sockets.
 * 
 * @version 1.0
 */
public interface SocketManager extends Identifiable {

	/**
	 * Set the socket enabled state for a given socket ID.
	 * 
	 * @param socketId
	 *        The ID of the socket to set the state of.
	 * @param enabled
	 *        The desired state to set.
	 * @return <em>true</em> if the state was set
	 */
	boolean adjustSocketEnabledState(String socketId, boolean enabled);

}
