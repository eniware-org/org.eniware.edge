/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.ocpp;

/**
 * DAO API for {@link Socket} entities.
 * 
 * @version 1.0
 */
public interface SocketDao {

	/**
	 * Store (create or update) a socket. The {@code socketId} value is the
	 * primary key.
	 * 
	 * @param socket
	 *        The socket to store.
	 */
	void storeSocket(Socket socket);

	/**
	 * Get an {@link Socket} for a given socket ID.
	 * 
	 * @param socketId
	 *        The socketId ID of the socket to get.
	 * @return The associated socket, or <em>null</em> if not available.
	 */
	Socket getSocket(String socketId);

	/**
	 * Get the {@code enabled} state of a socket. If no entity exists for the
	 * given {@code socketId} this method returns <em>true</em>.
	 * 
	 * @param socketId
	 *        The socketId ID of the socket to get the enabled state of.
	 * @return The enabled state.
	 */
	boolean isEnabled(String socketId);

}
