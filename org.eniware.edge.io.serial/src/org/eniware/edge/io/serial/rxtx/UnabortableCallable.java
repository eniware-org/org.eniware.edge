/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.io.serial.rxtx;

/**
 * Abstract implementation of {@link AbortableCallable} that ignores the call to
 * {@link #abort()}.
 * 
 * @version 1.0
 */
public abstract class UnabortableCallable<T> implements AbortableCallable<T> {

	@Override
	public void abort() {
		// no-op
	}

}
