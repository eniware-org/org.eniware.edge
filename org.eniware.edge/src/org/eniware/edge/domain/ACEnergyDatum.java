/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */
package org.eniware.edge.domain;

/**
 * Standardized API for alternating current related energy datum to implement.
 * This API represents a single phase, either a direct phase measurement or an
 * average or total measurement.
 * 
 * @version 1.1
 */
public interface ACEnergyDatum extends EnergyDatum {

	/**
	 * The {@link org.eniware.domain.GeneralEdgeDatumSamples} status sample
	 * key for {@link #getPhase()} values.
	 */
	public static final String PHASE_KEY = "phase";

	/**
	 * The {@link org.eniware.domain.GeneralEdgeDatumSamples} instantaneous
	 * sample key for {@link #getRealPower()} values.
	 */
	public static final String REAL_POWER_KEY = "realPower";

	/**
	 * The {@link org.eniware.domain.GeneralEdgeDatumSamples} instantaneous
	 * sample key for {@link #getApparentPower()} values.
	 */
	public static final String APPARENT_POWER_KEY = "apparentPower";

	/**
	 * The {@link org.eniware.domain.GeneralEdgeDatumSamples} instantaneous
	 * sample key for {@link #getReactivePower()} values.
	 */
	public static final String REACTIVE_POWER_KEY = "reactivePower";

	/**
	 * The {@link org.eniware.domain.GeneralEdgeDatumSamples} instantaneous
	 * sample key for {@link #getPowerFactor()} values.
	 */
	public static final String POWER_FACTOR_KEY = "powerFactor";

	/**
	 * The {@link org.eniware.domain.GeneralEdgeDatumSamples} instantaneous
	 * sample key for {@link #getEffectivePowerFactor()} values.
	 */
	public static final String EFFECTIVE_POWER_FACTOR_KEY = "effectivePowerFactor";

	/**
	 * The{@link org.eniware.domain.GeneralEdgeDatumSamples} instantaneous
	 * sample key for {@link #getFrequency()} values.
	 */
	public static final String FREQUENCY_KEY = "frequency";

	/**
	 * The {@link org.eniware.domain.GeneralEdgeDatumSamples} instantaneous
	 * sample key for {@link #getVoltage()} values.
	 */
	public static final String VOLTAGE_KEY = "voltage";

	/**
	 * The {@link org.eniware.domain.GeneralEdgeDatumSamples} instantaneous
	 * sample key for {@link #getCurrent()} values.
	 */
	public static final String CURRENT_KEY = "current";

	/**
	 * The {@link org.eniware.domain.GeneralEdgeDatumSamples} instantaneous
	 * sample key for {@link #getPhaseVoltage()} values.
	 */
	public static final String PHASE_VOLTAGE_KEY = "phaseVoltage";

	/**
	 * Get the phase measured by this datum.
	 * 
	 * @return the phase, should never be <em>null</em>
	 */
	ACPhase getPhase();

	/**
	 * Get the instantaneous real power, in watts (W). This should return the
	 * same value as {@link EnergyDatum#getWatts()} but has this method to be
	 * explicit.
	 * 
	 * @return the real power in watts, or <em>null</em> if not available
	 */
	Integer getRealPower();

	/**
	 * Get the instantaneous apparent power, in volt-amperes (VA).
	 * 
	 * @return the apparent power in volt-amperes, or <em>null</em> if not
	 *         available
	 */
	Integer getApparentPower();

	/**
	 * Get the instantaneous reactive power, in reactive volt-amperes (var).
	 * 
	 * @return the reactive power in reactive volt-amperes, or <em>null</em> if
	 *         not available
	 */
	Integer getReactivePower();

	/**
	 * Get the effective instantaneous power factor, as a value between
	 * {@code -1} and {@code 1}. If the phase angle is positive (current leads
	 * voltage) this method returns a positive value. If the phase angle is
	 * negative (current lags voltage) this method returns a negative value.
	 * 
	 * @return the effective power factor
	 */
	Float getEffectivePowerFactor();

	/**
	 * Get the instantaneous frequency, in hertz (Hz).
	 * 
	 * @return the frequency, or <em>null</em> if not known
	 */
	Float getFrequency();

	/**
	 * Get the instantaneous neutral voltage.
	 * 
	 * @return the volts, or <em>null</em> if not known
	 */
	Float getVoltage();

	/**
	 * Get the instantaneous current, in amps.
	 * 
	 * @return the amps, or <em>null</em> if not known
	 */
	Float getCurrent();

	/**
	 * Get the instantaneous phase line voltage.
	 * 
	 * @return the volts, or <em>null</em> if not known
	 */
	Float getPhaseVoltage();

	/**
	 * Get the instantaneous power factor.
	 * 
	 * @return the power factor, or <em>null</em> if not known
	 */
	Float getPowerFactor();
}
