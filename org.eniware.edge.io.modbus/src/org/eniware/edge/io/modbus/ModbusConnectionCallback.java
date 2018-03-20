/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.io.modbus;

import java.io.IOException;
import net.wimpi.modbus.net.SerialConnection;

/**
 * Callback API for performing an action with a Modbus {@link SerialConnection}.
 * 
 * <p>
 * If no result object is needed, simply use {@link Object} as the parameter
 * type and return <em>null</em> from {@link #doInConnection(SerialConnection)}.
 * </p>
 * 
 * @param <T>
 *        the action return type
 */
public interface ModbusConnectionCallback<T> {

	/**
	 * Perform an action with a Modbus {@link SerialConnection}.
	 * 
	 * <p>
	 * If no result object is needed, simply return <em>null</em>.
	 * </p>
	 * 
	 * @param conn
	 *        the connection
	 * @return the result
	 * @throws IOException
	 */
	T doInConnection(SerialConnection conn) throws IOException;
}
