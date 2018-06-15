/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge;

/**
 * API for object that can sample (collect) raw data from some device,
 * such as a serial port, and return it.
 * 
 * <p>This API is designed for collecting small amounts of data at a time,
 * geared towards reading statistics from generation or consumption data
 * sources.</p>
 * 
 * @version $Revision$ $Date$
 */
public interface DataCollector {
	
	/**
	 * Start collecting data.
	 * 
	 * <p>This method will start reading data from the connected
	 * data stream. Depending on the implementation, it may block until
	 * the desired data has been collected, or it may return immediately
	 * and collect data in a background thread.</p>
	 */
	public void collectData();

	/**
	 * Get the number of "good" bytes read so far.
	 * 
	 * @return byte count
	 */
	public int bytesRead();
	
	/**
	 * Get the bytes read.
	 * 
	 * @return the bytes of data collected thus far
	 */
	public byte[] getCollectedData();
	
	/**
	 * Get the bytes read as a String.
	 * 
	 * @return the bytes of data collected thus far, as a String
	 */
	public String getCollectedDataAsString();
	
	/**
	 * Stop collecting data, freeing up any appropriate resources
	 * (for example closing the underlying data stream).
	 */
	public void stopCollecting();
}
