/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.setup;

/**
 * General setup runtime exception.
 * 
 * @version 1.0
 */
public class SetupException extends RuntimeException {

	private static final long serialVersionUID = 547923961008965723L;

	/**
	 * Construct with a message.
	 * 
	 * @param message
	 *        the message
	 */
	public SetupException(String message) {
		super(message);
	}

	/**
	 * Construct with a message and exception.
	 * 
	 * @param message
	 *        message
	 * @param t
	 *        the original exception
	 */
	public SetupException(String message, Throwable cause) {
		super(message, cause);
	}

}
