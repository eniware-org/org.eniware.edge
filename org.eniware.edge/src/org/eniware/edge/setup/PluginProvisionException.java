/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.setup;

/**
 * Exception thrown when a provisioning error occurs, such as a missing
 * dependency that prevents a plugin from being installed.
 * 
 * @author matt
 * @version 1.0
 */
public class PluginProvisionException extends RuntimeException {

	private static final long serialVersionUID = -2847282484880397704L;

	public PluginProvisionException(String message, Throwable cause) {
		super(message, cause);
	}

	public PluginProvisionException(String message) {
		super(message);
	}

	public PluginProvisionException(Throwable cause) {
		super(cause);
	}

}
