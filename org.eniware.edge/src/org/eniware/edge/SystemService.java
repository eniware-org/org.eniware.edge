/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */
package org.eniware.edge;

/**
 * API for node system services, such as restarting, rebooting, or making system
 * configuration changes.
 * 
 * @author matt
 * @version 1.0
 * @since 1.47
 */
public interface SystemService {

	/**
	 * Exit the node application, stopping the active process.
	 * 
	 * @param syncState
	 *        A flag to indicate (when {@code true}) that any transient data
	 *        should be persisted to permanent storage.
	 */
	void exit(boolean syncState);

	/**
	 * Reboot the device the application is running on.
	 */
	void reboot();

}
