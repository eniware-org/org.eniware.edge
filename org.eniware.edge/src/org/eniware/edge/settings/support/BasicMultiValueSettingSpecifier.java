/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.settings.support;

import org.eniware.edge.settings.MappableSpecifier;
import org.eniware.edge.settings.MultiValueSettingSpecifier;
import org.eniware.edge.settings.SettingSpecifier;

/**
 * Basic implementation of {@link MultiValueSettingSpecifier}.
 * 
 * @version 1.2
 */
public class BasicMultiValueSettingSpecifier extends BasicTextFieldSettingSpecifier implements
		MultiValueSettingSpecifier {

	/**
	 * Constructor.
	 * 
	 * @param key
	 *        the key
	 * @param defaultValue
	 *        the default value
	 */
	public BasicMultiValueSettingSpecifier(String key, String defaultValue) {
		super(key, defaultValue);
	}

	@Override
	public SettingSpecifier mappedWithPlaceholer(String template) {
		BasicMultiValueSettingSpecifier spec = new BasicMultiValueSettingSpecifier(String.format(
				template, getKey()), getDefaultValue());
		spec.setTitle(getTitle());
		spec.setValueTitles(getValueTitles());
		return spec;
	}

	@SuppressWarnings("deprecation")
	@Override
	public SettingSpecifier mappedWithMapper(
			org.eniware.edge.settings.KeyedSettingSpecifier.Mapper mapper) {
		return mappedWithMapper((MappableSpecifier.Mapper) mapper);
	}

	@Override
	public SettingSpecifier mappedWithMapper(MappableSpecifier.Mapper mapper) {
		BasicMultiValueSettingSpecifier spec = new BasicMultiValueSettingSpecifier(
				mapper.mapKey(getKey()), getDefaultValue());
		spec.setTitle(getTitle());
		spec.setValueTitles(getValueTitles());
		return spec;
	}
}
