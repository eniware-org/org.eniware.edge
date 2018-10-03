/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.setup;

/**
 * Exception used to identify invalid verification codes, generally thrown when
 * an exception is encountered trying to decode a verification code.
 * 
 * @version 1.0
 */
public class InvalidVerificationCodeException extends Exception {

	private static final long serialVersionUID = -3412491490707016756L;

	/**
	 * Construct with a message.
	 * 
	 * @param message
	 *        the message
	 */
	public InvalidVerificationCodeException(String message) {
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
	public InvalidVerificationCodeException(String message, Throwable t) {
		super(message, t);
	}

}
