/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge;

/**
 * API for a service that manages data collector instances.
 * 
 * @version 1.1
 */
public interface DataCollectorFactory<T> extends Identifiable {

	/**
	 * Get a unique identifier for this factory. This should be meaningful to
	 * the factory implementation. For example a serial port based
	 * implementation could use the port identifier as the UID.
	 * 
	 * @return unique identifier
	 */
	@Override
	String getUID();

	/**
	 * Get a {@link DataCollector} instance.
	 * 
	 * @param params
	 *        the parameters to configure the collector with
	 * @return the instance
	 */
	DataCollector getDataCollectorInstance(T params);

	/**
	 * Get a {@link ConversationalDataCollector} instance.
	 * 
	 * @param params
	 *        the parameters to configure the collector with
	 * @return the instance
	 */
	ConversationalDataCollector getConversationalDataCollectorInstance(T params);

}
