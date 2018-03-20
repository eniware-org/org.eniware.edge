/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.util;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utilities for dealing with raw data.
 * 
 * @author matt
 * @version 1.0
 */
public final class DataUtils {

	private static final Logger LOG = LoggerFactory.getLogger(DataUtils.class);

	private DataUtils() {
		// do not construct me
	}

	/**
	 * Convert a signed byte into an unsigned short value.
	 * 
	 * <p>
	 * The returned short will have a value between 0 and 255.
	 * </p>
	 * 
	 * @param data
	 *        the byte
	 * @return the unsigned value
	 */
	public static short unsigned(byte data) {
		return (short) (data & 0xFF);
	}

	/**
	 * Convert signed bytes into unsigned short values.
	 * 
	 * <p>
	 * The returned shorts will have values between 0 and 255.
	 * </p>
	 * 
	 * @param data
	 *        the bytes
	 * @return the unsigned values
	 */
	public static short[] getUnsignedValues(byte[] data) {
		// convert bytes into "unsigned" integer values, i.e. 0..255
		short[] unsigned = new short[data.length];
		for ( int i = 0; i < data.length; i++ ) {
			unsigned[i] = (short) (data[i] & 0xFF);
		}
		if ( LOG.isTraceEnabled() ) {
			LOG.trace("Unsigned data: " + Arrays.toString(unsigned));
		}
		return unsigned;
	}

}
