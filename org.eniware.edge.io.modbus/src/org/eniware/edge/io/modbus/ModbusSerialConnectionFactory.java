/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.io.modbus;

import net.solarnetwork.node.LockTimeoutException;
import net.wimpi.modbus.net.SerialConnection;

/**
 * Factory for Modbus SerialConnection objects.
 * 
 * @version 1.0
 */
public interface ModbusSerialConnectionFactory {

	/**
	 * Get a unique identifier for this factory.
	 * 
	 * <p>
	 * This should be meaningful to the factory implementation. For example a
	 * serial port based implementation could use the port identifier as the
	 * UID.
	 * </p>
	 * 
	 * @return unique identifier
	 */
	String getUID();

	/**
	 * Get a configured and open SerialConnection.
	 * 
	 * <p>
	 * The returned connection will be opened. If the connection cannot be
	 * opened, a {@link RuntimeException} will be thrown. The caller should
	 * always call {@link SerialConnection#close()} to free up resources when
	 * finished.
	 * </p>
	 * 
	 * @return the connection
	 * @throws LockTimeoutException
	 *         if cannot obtain the connection because another thread has
	 *         already obtained it
	 */
	SerialConnection getSerialConnection();

	/**
	 * Perform some work with a Modbus {@link SerialConnection}.
	 * 
	 * <p>
	 * This is a convenient way to open the connection, perform some work, and
	 * have the connection automatically closed for you.
	 * </p>
	 * 
	 * <p>
	 * This method attempts to obtain a {@link SerialConnection} via
	 * {@link #getSerialConnection()}. If the connection is obtained, it will
	 * call {@link ModbusConnectionCallback#doInConnection(SerialConnection)},
	 * and then close the connection when finished.
	 * </p>
	 * 
	 * <p>
	 * <b>Note</b> that if either the connection factory is unavailable, or it
	 * fails to return a connection, the callback method will never be called.
	 * </p>
	 * 
	 * @param action
	 *        the connection callback
	 * @return the result of the callback, or <em>null</em> if the callback is
	 *         never invoked
	 */
	<T> T execute(ModbusConnectionCallback<T> action);

}
