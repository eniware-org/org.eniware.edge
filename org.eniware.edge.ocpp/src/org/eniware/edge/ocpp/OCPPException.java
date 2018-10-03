/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.ocpp;

import ocpp.v15.cs.AuthorizationStatus;

/**
 * Generic exception for OCPP specific conditions.
 * 
 * @version 1.0
 */
public class OCPPException extends RuntimeException {

	private static final long serialVersionUID = 6185440591151893140L;

	private final AuthorizationStatus status;

	/**
	 * Construct with a message.
	 * 
	 * @param message
	 *        The message.
	 */
	public OCPPException(String message) {
		this(message, null, null);
	}

	/**
	 * Construct with cause.
	 * 
	 * @param cause
	 *        The original cause.
	 */
	public OCPPException(Throwable cause) {
		this(null, cause, null);
	}

	/**
	 * Construct with message and cause.
	 * 
	 * @param message
	 *        The message.
	 * @param cause
	 *        The original cause.
	 */
	public OCPPException(String message, Throwable cause) {
		this(message, cause, null);
	}

	/**
	 * Construct with values.
	 * 
	 * @param message
	 *        The message.
	 * @param cause
	 *        The original cause.
	 * @param status
	 *        A status.
	 */
	public OCPPException(String message, Throwable cause, AuthorizationStatus status) {
		super(message, cause);
		this.status = status;
	}

	/**
	 * Get a status associated with the exception condition.
	 * 
	 * @return The status.
	 */
	public AuthorizationStatus getStatus() {
		return status;
	}

}
