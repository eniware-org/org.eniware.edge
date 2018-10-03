/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */
package org.eniware.edge;

/**
 * Runtime lock timeout exception.
 * 
 * @version $Revision$
 */
public class LockTimeoutException extends RuntimeException {

	private static final long serialVersionUID = 1158999746180436986L;

	/**
	 * Default constructor.
	 */
	public LockTimeoutException() {
		super();
	}

	/**
	 * Construct with a message.
	 * 
	 * @param message the message
	 */
	public LockTimeoutException(String message) {
		super(message);
	}

}
