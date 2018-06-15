/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.settings.support;

import org.eniware.edge.settings.MappableSpecifier;
import org.eniware.edge.settings.SettingSpecifier;
import org.eniware.edge.settings.SliderSettingSpecifier;

/**
 * Basic implementation of {@link SliderSettingSpecifier}.
 * s
 * @version 1.2
 */
public class BasicSliderSettingSpecifier extends BaseKeyedSettingSpecifier<Double> implements
		SliderSettingSpecifier {

	private Double minimumValue = Double.valueOf(0.0);
	private Double maximumValue = Double.valueOf(1.0);
	private Double step = Double.valueOf(1);

	/**
	 * Construct with values.
	 * 
	 * @param key
	 *        the key
	 * @param defaultValue
	 *        the default value
	 * @param minValue
	 *        the minimum value
	 * @param maxValue
	 *        the maximum value
	 * @param step
	 *        the step value
	 */
	public BasicSliderSettingSpecifier(String key, Double defaultValue, Double minValue,
			Double maxValue, Double step) {
		super(key, defaultValue);
		setMaximumValue(maxValue);
		setMinimumValue(minValue);
		setStep(step);
	}

	@Override
	public Double getMinimumValue() {
		return this.minimumValue;
	}

	@Override
	public Double getMaximumValue() {
		return this.maximumValue;
	}

	@Override
	public Double getStep() {
		return step;
	}

	@Override
	public SettingSpecifier mappedWithPlaceholer(String template) {
		BasicSliderSettingSpecifier spec = new BasicSliderSettingSpecifier(String.format(template,
				getKey()), getDefaultValue(), minimumValue, maximumValue, step);
		spec.setTitle(getTitle());
		return spec;
	}

	@SuppressWarnings("deprecation")
	@Override
	public SettingSpecifier mappedWithMapper(Mapper mapper) {
		return mappedWithMapper((MappableSpecifier.Mapper) mapper);
	}

	@Override
	public SettingSpecifier mappedWithMapper(MappableSpecifier.Mapper mapper) {
		BasicSliderSettingSpecifier spec = new BasicSliderSettingSpecifier(mapper.mapKey(getKey()),
				getDefaultValue(), minimumValue, maximumValue, step);
		spec.setTitle(getTitle());
		return spec;
	}

	public void setMinimumValue(Double minimumValue) {
		this.minimumValue = minimumValue;
	}

	public void setMaximumValue(Double maximumValue) {
		this.maximumValue = maximumValue;
	}

	public void setStep(Double step) {
		this.step = step;
	}

}
