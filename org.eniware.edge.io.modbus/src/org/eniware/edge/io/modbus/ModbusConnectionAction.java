/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.io.modbus;

import java.io.IOException;

/**
 * Callback API for performing an action with a {@link ModbusConnection}.
 * 
 * <p>
 * If no result object is needed, simply use {@link Object} as the parameter
 * type and return <em>null</em> from
 * {@link #doWithConnection(ModbusConnection)}.
 * </p>
 * 
 * @param <T>
 *  
 * @version 1.0
 * @since 2.0
 */
public interface ModbusConnectionAction<T> {

	/**
	 * Perform an action with a {@link ModbusConnection}. If no result object is
	 * needed, simply return <em>null</em>.
	 * 
	 * @param conn
	 *        the connection
	 * @return the result
	 * @throws IOException
	 */
	T doWithConnection(ModbusConnection conn) throws IOException;

}
