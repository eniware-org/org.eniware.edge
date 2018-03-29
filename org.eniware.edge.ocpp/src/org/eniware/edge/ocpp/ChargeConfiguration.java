/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.ocpp;

/**
 * Configuration properties supported by SolarNode.
 * 
 * @version 1.0
 * @since 0.6
 */
public interface ChargeConfiguration {

	/**
	 * Get the heart beat interval, in seconds. A value of {@code 0} indicates
	 * no heart beat should be used.
	 * 
	 * @return the heart beat interval
	 */
	int getHeartBeatInterval();

	/**
	 * Get the interval at which to sample meter values during a charge session,
	 * in seconds. A value of {@code 0} indicates no samples should be taken.
	 * 
	 * @return the meter value sample interval
	 */
	int getMeterValueSampleInterval();

	/**
	 * Test if this configuration differs in any way from another instance.
	 * 
	 * @param config
	 *        The other configuration to compare to.
	 * @return {@code true} if any properties differ between the two
	 *         configurations
	 */
	boolean differsFrom(ChargeConfiguration config);

}
