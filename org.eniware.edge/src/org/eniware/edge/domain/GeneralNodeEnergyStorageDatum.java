/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.domain;

import net.solarnetwork.util.SerializeIgnore;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * GeneralNodeDatum that also implements {@link EnergyStorageDatum}.
 * 
 * @version 1.0
 */
public class GeneralNodeEnergyStorageDatum extends GeneralNodeDatum implements EnergyStorageDatum {

	@Override
	@JsonIgnore
	@SerializeIgnore
	public Float getAvailableEnergyPercentage() {
		return getInstantaneousSampleFloat(PERCENTAGE_KEY);
	}

	public void setAvailableEnergyPercentage(Float value) {
		putInstantaneousSampleValue(PERCENTAGE_KEY, value);
	}

	@Override
	@JsonIgnore
	@SerializeIgnore
	public Long getAvailableEnergy() {
		return getInstantaneousSampleLong(AVAILABLE_WATT_HOURS_KEY);
	}

	public void setAvailableEnergy(Long value) {
		putInstantaneousSampleValue(AVAILABLE_WATT_HOURS_KEY, value);
	}

}
