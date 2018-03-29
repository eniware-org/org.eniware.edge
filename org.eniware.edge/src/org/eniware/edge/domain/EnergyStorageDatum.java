/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.domain;

/**
 * Standardized API for energy storage system related datum to implement.
 * 
 * @version 1.0
 */
public interface EnergyStorageDatum extends Datum {

	/**
	 * The {@link net.solarnetwork.domain.GeneralNodeDatumSamples} instantaneous
	 * sample key for {@link #getAvailableEnergyPercentage()} values.
	 */
	String PERCENTAGE_KEY = "percent";

	/**
	 * The {@link net.solarnetwork.domain.GeneralNodeDatumSamples} instantaneous
	 * sample key for {@link #getAvailableEnergy()} values.
	 */
	String AVAILABLE_WATT_HOURS_KEY = "availWattHours";

	/**
	 * Get the percentage of energy capacity available in the storage. This
	 * value, multiplied by {@link #getAvailableEnergy()}, represents the total
	 * energy capacity of the storage.
	 * 
	 * @return The available energy as a percentage of the total capacity of the
	 *         storage.
	 */
	Float getAvailableEnergyPercentage();

	/**
	 * Get the available energy of the storage system, in Wh.
	 * 
	 * @return The available energy of the storage.
	 */
	Long getAvailableEnergy();

}
