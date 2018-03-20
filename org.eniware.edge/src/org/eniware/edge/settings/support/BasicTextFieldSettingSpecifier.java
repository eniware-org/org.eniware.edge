/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.settings.support;

import org.eniware.edge.settings.MappableSpecifier;
import org.eniware.edge.settings.SettingSpecifier;
import org.eniware.edge.settings.TextFieldSettingSpecifier;

/**
 * Basic implementation of {@link TextFieldSettingSpecifier}.
 * 
 * @author matt
 * @version 1.3
 */
public class BasicTextFieldSettingSpecifier extends BasicTitleSettingSpecifier
		implements TextFieldSettingSpecifier {

	private boolean secureTextEntry = false;

	/**
	 * Constructor.
	 * 
	 * @param key
	 *        the key
	 * @param defaultValue
	 *        the default value
	 */
	public BasicTextFieldSettingSpecifier(String key, String defaultValue) {
		super(key, defaultValue);
	}

	/**
	 * Constructor.
	 * 
	 * @param key
	 *        the key
	 * @param defaultValue
	 *        the default value
	 * @param secureTextEntry
	 *        <em>true</em> if the text should be hidden when editing.
	 */
	public BasicTextFieldSettingSpecifier(String key, String defaultValue, boolean secureTextEntry) {
		super(key, defaultValue);
		this.secureTextEntry = secureTextEntry;
	}

	@Override
	public SettingSpecifier mappedWithPlaceholer(String template) {
		BasicTextFieldSettingSpecifier spec = new BasicTextFieldSettingSpecifier(
				String.format(template, getKey()), getDefaultValue());
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
		BasicTextFieldSettingSpecifier spec = new BasicTextFieldSettingSpecifier(mapper.mapKey(getKey()),
				getDefaultValue());
		spec.setTitle(getTitle());
		spec.setValueTitles(getValueTitles());
		return spec;
	}

	@Override
	public boolean isSecureTextEntry() {
		return secureTextEntry;
	}

}
