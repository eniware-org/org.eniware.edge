/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.io.serial.rxtx;

import java.util.concurrent.Callable;

/**
 * Extension of {@link Callable} to provide a way to notify between blocking IO
 * operations.
 * 
 * @version 1.0
 * @param <T>
 */
public interface AbortableCallable<T> extends Callable<T> {

	/**
	 * Give up the action, if still running. Can be called even after the task
	 * completes.
	 */
	void abort();

}
