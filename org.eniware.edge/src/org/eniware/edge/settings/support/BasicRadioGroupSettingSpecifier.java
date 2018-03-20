/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.settings.support;

import org.eniware.edge.settings.MappableSpecifier;
import org.eniware.edge.settings.RadioGroupSettingSpecifier;
import org.eniware.edge.settings.SettingSpecifier;

/**
 * Basic implementation of {@link RadioGroupSettingSpecifier}.
 * 
 * @author matt
 * @version 1.1
 */
public class BasicRadioGroupSettingSpecifier extends BasicTextFieldSettingSpecifier implements
		RadioGroupSettingSpecifier {

	private String footerText;

	/**
	 * Constructor.
	 * 
	 * @param key
	 *        the key
	 * @param defaultValue
	 *        the default value
	 */
	public BasicRadioGroupSettingSpecifier(String key, String defaultValue) {
		super(key, defaultValue);
	}

	@Override
	public String getFooterText() {
		return this.footerText;
	}

	@Override
	public SettingSpecifier mappedWithPlaceholer(String template) {
		BasicRadioGroupSettingSpecifier spec = new BasicRadioGroupSettingSpecifier(String.format(
				template, getKey()), getDefaultValue());
		spec.setTitle(getTitle());
		spec.setValueTitles(getValueTitles());
		return spec;
	}

	@SuppressWarnings("deprecation")
	@Override
	public SettingSpecifier mappedWithMapper(Mapper mapper) {
		return mappedWithMapper((MappableSpecifier.Mapper) mapper);
	}

	@Override
	public SettingSpecifier mappedWithMapper(MappableSpecifier.Mapper mapper) {
		BasicRadioGroupSettingSpecifier spec = new BasicRadioGroupSettingSpecifier(
				mapper.mapKey(getKey()), getDefaultValue());
		spec.setTitle(getTitle());
		spec.setValueTitles(getValueTitles());
		return spec;
	}

	public void setFooterText(String footerText) {
		this.footerText = footerText;
	}

}
