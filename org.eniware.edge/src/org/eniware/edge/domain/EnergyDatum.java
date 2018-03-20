/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.domain;

/**
 * Standardized API for energy related datum to implement. By "energy" we simply
 * mean this datum represents information tracked during the production or
 * consumption of energy. For example current sensors can provide approximate
 * instantaneous watt readings, watt-hour meters can provide accumulated Wh
 * readings, and usually solar inverters can provide instantaneous generated
 * power and accumulated energy production readings.
 * 
 * @author matt
 * @version 1.1
 */
public interface EnergyDatum extends Datum {

	/**
	 * The {@link net.solarnetwork.domain.GeneralNodeDatumSamples} accumulating
	 * sample key for {@link #getWattHourReading()} values.
	 */
	public static final String WATT_HOUR_READING_KEY = "wattHours";

	/**
	 * The {@link net.solarnetwork.domain.GeneralNodeDatumSamples} instantaneous
	 * sample key for {@link #getWatts()} values.
	 */
	public static final String WATTS_KEY = "watts";

	/** A tag for "consumption" of energy. */
	public static final String TAG_CONSUMPTION = "consumption";

	/** A tag for "generation" of energy. */
	public static final String TAG_GENERATION = "power";

	/**
	 * Get a watt-hour reading. Generally this is an accumulating value and
	 * represents the overall energy used or produced since some reference date.
	 * Often the reference date if fixed, but it could also shift, for example
	 * shift to the last time a reading was taken.
	 * 
	 * @return the watt hour reading, or <em>null</em> if not available
	 */
	public Long getWattHourReading();

	/**
	 * Get the instantaneous watts.
	 * 
	 * @return watts, or <em>null</em> if not available
	 */
	public Integer getWatts();

}
