/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.domain;

import net.solarnetwork.util.SerializeIgnore;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * GeneralNodeDatum that also implements {@link EnergyDatum}.
 * 
 * @version 1.0
 */
public class GeneralNodeEnergyDatum extends GeneralNodeDatum implements EnergyDatum {

	/**
	 * Return <em>true</em> if this datum is tagged with
	 * {@link EnergyDatum#TAG_CONSUMPTION}.
	 * 
	 * @return boolean
	 */
	public boolean isConsumption() {
		return hasTag(TAG_CONSUMPTION);
	}

	/**
	 * Tag this datum with {@link EnergyDatum#TAG_CONSUMPTION}. This will also
	 * remove {@link EnergyDatum#TAG_GENERATION}.
	 */
	public void tagAsConsumption() {
		addTag(TAG_CONSUMPTION);
		removeTag(TAG_GENERATION);
	}

	/**
	 * Return <em>true</em> if this datum is tagged with
	 * {@link EnergyDatum#TAG_GENERATION}.
	 * 
	 * @return boolean
	 */
	public boolean isGeneration() {
		return hasTag(TAG_GENERATION);
	}

	/**
	 * Tag this datum with {@link EnergyDatum#TAG_GENERATION}. This will also
	 * remove {@link EnergyDatum#TAG_CONSUMPTION}.
	 */
	public void tagAsGeneration() {
		removeTag(TAG_CONSUMPTION);
		addTag(TAG_GENERATION);
	}

	@Override
	@JsonIgnore
	@SerializeIgnore
	public Long getWattHourReading() {
		return getAccumulatingSampleLong(WATT_HOUR_READING_KEY);
	}

	public void setWattHourReading(Long wattHourReading) {
		putAccumulatingSampleValue(WATT_HOUR_READING_KEY, wattHourReading);
	}

	@Override
	@JsonIgnore
	@SerializeIgnore
	public Integer getWatts() {
		return getInstantaneousSampleInteger(WATTS_KEY);
	}

	public void setWatts(Integer watts) {
		putInstantaneousSampleValue(WATTS_KEY, watts);
	}

}
