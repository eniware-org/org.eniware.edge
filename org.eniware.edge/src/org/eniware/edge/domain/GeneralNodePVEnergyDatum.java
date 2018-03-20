/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.domain;

import net.solarnetwork.util.SerializeIgnore;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * GeneralNodeDatum that also implements {@link PVEnergyDatum}.
 * 
 * @author matt
 * @version 1.0
 */
public class GeneralNodePVEnergyDatum extends GeneralNodeEnergyDatum implements PVEnergyDatum {

	@Override
	@JsonIgnore
	@SerializeIgnore
	public Integer getDCPower() {
		return getInstantaneousSampleInteger(DC_POWER_KEY);
	}

	public void setDCPower(Integer value) {
		putInstantaneousSampleValue(DC_POWER_KEY, value);
	}

	@Override
	@JsonIgnore
	@SerializeIgnore
	public Float getDCVoltage() {
		return getInstantaneousSampleFloat(DC_VOLTAGE_KEY);
	}

	public void setDCVoltage(Float value) {
		putInstantaneousSampleValue(DC_VOLTAGE_KEY, value);
	}

	@Override
	@JsonIgnore
	@SerializeIgnore
	public Float getVoltage() {
		return getInstantaneousSampleFloat(VOLTAGE_KEY);
	}

	public void setVoltage(Float value) {
		putInstantaneousSampleValue(VOLTAGE_KEY, value);
	}

}
