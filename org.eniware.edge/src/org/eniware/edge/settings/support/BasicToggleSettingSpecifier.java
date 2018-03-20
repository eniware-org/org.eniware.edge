/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.settings.support;

import org.eniware.edge.settings.MappableSpecifier;
import org.eniware.edge.settings.SettingSpecifier;
import org.eniware.edge.settings.ToggleSettingSpecifier;

/**
 * Basic implementation of {@link ToggleSettingSpecifier}.
 * 
 * @author matt
 * @version 1.2
 */
public class BasicToggleSettingSpecifier extends BaseKeyedSettingSpecifier<Object> implements
		ToggleSettingSpecifier {

	private Object trueValue = Boolean.TRUE;
	private Object falseValue = Boolean.FALSE;

	/**
	 * Constructor.
	 * 
	 * @param key
	 *        the key
	 * @param defaultValue
	 *        the default value
	 */
	public BasicToggleSettingSpecifier(String key, Object defaultValue) {
		super(key, defaultValue);
	}

	/**
	 * Constructor.
	 * 
	 * @param key
	 *        the key
	 * @param defaultValue
	 *        the default value
	 * @param trans
	 *        the transient flag value
	 */
	public BasicToggleSettingSpecifier(String key, Object defaultValue, boolean trans) {
		super(key, defaultValue, trans);
	}

	@Override
	public Object getTrueValue() {
		return this.trueValue;
	}

	@Override
	public Object getFalseValue() {
		return this.falseValue;
	}

	@Override
	public SettingSpecifier mappedWithPlaceholer(String template) {
		BasicToggleSettingSpecifier spec = new BasicToggleSettingSpecifier(String.format(template,
				getKey()), getDefaultValue());
		spec.setTitle(getTitle());
		spec.setTrueValue(trueValue);
		spec.setFalseValue(falseValue);
		return spec;
	}

	@SuppressWarnings("deprecation")
	@Override
	public SettingSpecifier mappedWithMapper(Mapper mapper) {
		return mappedWithMapper((MappableSpecifier.Mapper) mapper);
	}

	@Override
	public SettingSpecifier mappedWithMapper(MappableSpecifier.Mapper mapper) {
		BasicToggleSettingSpecifier spec = new BasicToggleSettingSpecifier(mapper.mapKey(getKey()),
				getDefaultValue());
		spec.setTitle(getTitle());
		spec.setTrueValue(trueValue);
		spec.setFalseValue(falseValue);
		return spec;
	}

	public void setTrueValue(Object trueValue) {
		this.trueValue = trueValue;
	}

	public void setFalseValue(Object falseValue) {
		this.falseValue = falseValue;
	}

}
