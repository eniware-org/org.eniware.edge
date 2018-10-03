/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.domain;

import org.eniware.util.SerializeIgnore;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * GeneralEdgeDatum that also implements {@link ACEnergyDatum}.
 * 
 * @version 1.1
 */
public class GeneralEdgeACEnergyDatum extends GeneralEdgeEnergyDatum implements ACEnergyDatum {

	@Override
	@JsonIgnore
	@SerializeIgnore
	public ACPhase getPhase() {
		String p = getStatusSampleString(PHASE_KEY);
		return (p == null ? null : ACPhase.valueOf(p));
	}

	public void setPhase(ACPhase phase) {
		putStatusSampleValue(PHASE_KEY, phase == null ? null : phase.toString());
	}

	@Override
	@JsonIgnore
	@SerializeIgnore
	public Integer getRealPower() {
		return getInstantaneousSampleInteger(REAL_POWER_KEY);
	}

	public void setRealPower(Integer realPower) {
		putInstantaneousSampleValue(REAL_POWER_KEY, realPower);
	}

	@Override
	@JsonIgnore
	@SerializeIgnore
	public Integer getApparentPower() {
		return getInstantaneousSampleInteger(APPARENT_POWER_KEY);
	}

	public void setApparentPower(Integer apparentPower) {
		putInstantaneousSampleValue(APPARENT_POWER_KEY, apparentPower);
	}

	@Override
	@JsonIgnore
	@SerializeIgnore
	public Integer getReactivePower() {
		return getInstantaneousSampleInteger(REACTIVE_POWER_KEY);
	}

	public void setReactivePower(Integer reactivePower) {
		putInstantaneousSampleValue(REACTIVE_POWER_KEY, reactivePower);
	}

	@Override
	@JsonIgnore
	@SerializeIgnore
	public Float getEffectivePowerFactor() {
		return getInstantaneousSampleFloat(EFFECTIVE_POWER_FACTOR_KEY);
	}

	public void setEffectivePowerFactor(Float effectivePowerFactor) {
		putInstantaneousSampleValue(EFFECTIVE_POWER_FACTOR_KEY, effectivePowerFactor);
	}

	@Override
	@JsonIgnore
	@SerializeIgnore
	public Float getFrequency() {
		return getInstantaneousSampleFloat(FREQUENCY_KEY);
	}

	public void setFrequency(Float frequency) {
		putInstantaneousSampleValue(FREQUENCY_KEY, frequency);
	}

	@Override
	@JsonIgnore
	@SerializeIgnore
	public Float getVoltage() {
		return getInstantaneousSampleFloat(VOLTAGE_KEY);
	}

	public void setVoltage(Float voltage) {
		putInstantaneousSampleValue(VOLTAGE_KEY, voltage);
	}

	@Override
	@JsonIgnore
	@SerializeIgnore
	public Float getCurrent() {
		return getInstantaneousSampleFloat(CURRENT_KEY);
	}

	public void setCurrent(Float current) {
		putInstantaneousSampleValue(CURRENT_KEY, current);
	}

	@Override
	@JsonIgnore
	@SerializeIgnore
	public Float getPhaseVoltage() {
		return getInstantaneousSampleFloat(PHASE_VOLTAGE_KEY);
	}

	public void setPhaseVoltage(Float phaseVoltage) {
		putInstantaneousSampleValue(PHASE_VOLTAGE_KEY, phaseVoltage);
	}

	@Override
	@JsonIgnore
	@SerializeIgnore
	public Float getPowerFactor() {
		return getInstantaneousSampleFloat(POWER_FACTOR_KEY);
	}

	public void setPowerFactor(Float powerFactor) {
		putInstantaneousSampleValue(POWER_FACTOR_KEY, powerFactor);
	}

	/**
	 * Get an export energy value.
	 * 
	 * @return An energy value, in watts, or <em>null</em> if none available.
	 * @since 1.1
	 */
	@JsonIgnore
	@SerializeIgnore
	public Long getReverseWattHourReading() {
		return getAccumulatingSampleLong(WATT_HOUR_READING_KEY + REVERSE_ACCUMULATING_SUFFIX_KEY);
	}

	/**
	 * Set an export energy value.
	 * 
	 * @param wattHourReading
	 *        An energy value, in watts, or <em>null</em> if none available.
	 * @since 1.1
	 */
	public void setReverseWattHourReading(Long wattHourReading) {
		putAccumulatingSampleValue(WATT_HOUR_READING_KEY + REVERSE_ACCUMULATING_SUFFIX_KEY,
				wattHourReading);
	}

}
