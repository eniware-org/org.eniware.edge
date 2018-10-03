/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.io.serial;

import java.io.IOException;
import org.eniware.edge.Identifiable;

/**
 * High level serial network API.
 * 
 * <p>
 * This API aims to simplify accessing serial capable devices without having any
 * direct dependency on RXTX (or any other serial implementation).
 * </p>
 * 
 * @version 1.0
 */
public interface SerialNetwork extends Identifiable {

	/**
	 * Perform some action that requires a {@link SerialConnection}, returning
	 * the result. The
	 * {@link SerialConnectionAction#doWithConnection(SerialConnection)} method
	 * will be called and the result returned by this method.
	 * 
	 * The {@link SerialConnection} passed will already be opened, and it will
	 * be closed automatically after the action is complete.
	 * 
	 * @param action
	 *        the callback whose result to return
	 * @return the result of calling
	 *         {@link SerialConnectionAction#doWithConnection(SerialConnection)}
	 * @throws IOException
	 *         if any IO error occurs
	 */
	<T> T performAction(SerialConnectionAction<T> action) throws IOException;

	/**
	 * Create a connection to a specific Serial device. The returned connection
	 * will not be opened and must be closed when finished being used.
	 * 
	 * @return a new connection
	 */
	SerialConnection createConnection();

}
