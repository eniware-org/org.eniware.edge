/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.io.serial.rxtx;

/**
 * {@link UnabortableCallable} that returns no result.
 * 
 * @version 1.0
 */
public abstract class NoResultUnabortableCallable extends UnabortableCallable<Object> {

	@Override
	public Object call() throws Exception {
		doCall();
		return null;
	}

	/**
	 * Perform the task. This method is invoked by {@link #call()}.
	 * 
	 * @throws Exception
	 *         if any error occurrs
	 */
	protected abstract void doCall() throws Exception;

}
