/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.io.serial;

import java.io.IOException;
import org.eniware.edge.LockTimeoutException;

/**
 * @version 1.0
 */
public interface SerialConnection {

	/**
	 * Open the connection, if it is not already open. The connection must be
	 * opened before calling any of the other methods in this API.
	 * 
	 * @throws IOException
	 *         if the connection cannot be opened
	 */
	void open() throws IOException, LockTimeoutException;

	/**
	 * Close the connection, if it is open.
	 */
	void close();

	/**
	 * Read a message that is marked by start and end "magic" bytes. The
	 * returned bytes will include both the start and end marker bytes.
	 * 
	 * @param startMarker
	 *        the starting byte sequence
	 * @param endMarker
	 *        the ending byte sequence
	 * @return the message bytes, <b>including</b> {@code startMarker} and
	 *         {@code endMarker}
	 * @throws IOException
	 *         if the connection fails
	 */
	byte[] readMarkedMessage(byte[] startMarker, byte[] endMarker) throws IOException;

	/**
	 * Read a message that is marked by some starting "magic" bytes and has a
	 * fixed length;
	 * 
	 * @param startMarker
	 *        the starting byte sequence
	 * @param length
	 *        the length of the message to read, <b>including</b> the length of
	 *        {@code startMarker}
	 * @return the message bytes, <b>including</b> {@code startMarker}
	 * @throws IOException
	 *         if the connection fails
	 */
	byte[] readMarkedMessage(byte[] startMarker, int length) throws IOException;

	/**
	 * Write a message.
	 * 
	 * @param message
	 *        the message to write
	 * @throws IOException
	 *         if the connection fails
	 */
	void writeMessage(byte[] message) throws IOException;

	/**
	 * Drain the input buffer until it is empty.
	 * 
	 * @throws IOException
	 *         if the connection fails
	 * @return the drained bytes (never <em>null</em>)
	 */
	byte[] drainInputBuffer() throws IOException;

}
