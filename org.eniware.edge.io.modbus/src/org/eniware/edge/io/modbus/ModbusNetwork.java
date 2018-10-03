/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.io.modbus;

import java.io.IOException;
import org.eniware.edge.Identifiable;

/**
 * High level Modbus API.
 * 
 * <p>
 * This API aims to simplify accessing Modbus capable devices without having any
 * direct dependency on Jamod (or any other Modbus implementation).
 * </p>
 * 
 * @version 1.0
 * @since 2.0
 */
public interface ModbusNetwork extends Identifiable {

	/**
	 * Perform some action that requires a {@link ModbusConnection}, returning
	 * the result. The
	 * {@link ModbusConnectionAction#doWithConnection(ModbusConnection)} method
	 * will be called and the result returned by this method.
	 * 
	 * The {@link ModbusConnection} passed will already be opened, and it will
	 * be closed automatically after the action is complete.
	 * 
	 * @param action
	 *        the callback whose result to return
	 * @param unitId
	 *        the Modbus unit ID to address
	 * @return the result of calling
	 *         {@link ModbusConnectionAction#doWithConnection(ModbusConnection)}
	 * @throws IOException
	 *         if any IO error occurs
	 */
	<T> T performAction(ModbusConnectionAction<T> action, int unitId) throws IOException;

	/**
	 * Create a connection to a specific Modbus device. The returned connection
	 * will not be opened and must be closed when finished being used.
	 * 
	 * @param unitId
	 *        the Modbus unit ID to connect with
	 * @return a new connection
	 */
	ModbusConnection createConnection(int unitId);

}
