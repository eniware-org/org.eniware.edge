/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.domain;

/**
 * Standardized API for photovoltaic system related energy datum to implement.
 * 
 * @version 1.0
 */
public interface PVEnergyDatum extends EnergyDatum {

	/**
	 * The {@link org.eniware.domain.GeneralNodeDatumSamples} instantaneous
	 * sample key for {@link #getDCPower()} values.
	 */
	static final String DC_POWER_KEY = "dcPower";

	/**
	 * The {@link org.eniware.domain.GeneralNodeDatumSamples} instantaneous
	 * sample key for {@link #getDCVoltage()} values.
	 */
	static final String DC_VOLTAGE_KEY = "dcVoltage";

	/**
	 * The {@link org.eniware.domain.GeneralNodeDatumSamples} instantaneous
	 * sample key for {@link #getVoltage()} values.
	 */
	static final String VOLTAGE_KEY = "voltage";

	/**
	 * Get the instantaneous DC power output, in watts.
	 * 
	 * @return watts, or <em>null</em> if not available
	 */
	Integer getDCPower();

	/**
	 * Get the instantaneous DC voltage output, in volts.
	 * 
	 * @return DC voltage, or <em>null</em> if not available
	 */
	Float getDCVoltage();

	/**
	 * Get the instantaneous AC voltage output, in volts.
	 * 
	 * @return AC voltage, or <em>null</em> if not available
	 */
	Float getVoltage();

}
