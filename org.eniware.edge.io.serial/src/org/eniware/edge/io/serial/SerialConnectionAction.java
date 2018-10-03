/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.io.serial;

import java.io.IOException;

/**
 * Callback API for performing an action with a {@link SerialConnection}.
 * 
 * <p>
 * If no result object is needed, simply use {@link Object} as the parameter
 * type and return <em>null</em> from
 * {@link #doWithConnection(SerialConnection)}.
 * </p>
 * 
 * @param <T>
 *  
 * @version 1.0
 */
public interface SerialConnectionAction<T> {

	/**
	 * Perform an action with a {@link SerialConnection}. If no result object is
	 * needed, simply return <em>null</em>.
	 * 
	 * @param conn
	 *        the connection
	 * @return the result
	 * @throws IOException
	 */
	T doWithConnection(SerialConnection conn) throws IOException;

}
