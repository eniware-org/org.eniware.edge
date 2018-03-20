/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */
package org.eniware.edge.settings.support;

import java.util.Collections;
import java.util.Map;

import org.eniware.edge.settings.MappableSpecifier;
import org.eniware.edge.settings.SettingSpecifier;
import org.eniware.edge.settings.TitleSettingSpecifier;

/**
 * Basic implemtation of {@link TitleSettingSpecifier}.
 * 
 * @author matt
 * @version 1.2
 */
public class BasicTitleSettingSpecifier extends BaseKeyedSettingSpecifier<String> implements
		TitleSettingSpecifier {

	private Map<String, String> valueTitles;

	/**
	 * Constructor.
	 * 
	 * @param key
	 *        the key
	 * @param defaultValue
	 *        the default value
	 */
	public BasicTitleSettingSpecifier(String key, String defaultValue) {
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
	public BasicTitleSettingSpecifier(String key, String defaultValue, boolean trans) {
		super(key, defaultValue, trans);
	}

	@Override
	public Map<String, String> getValueTitles() {
		return this.valueTitles;
	}

	@Override
	public SettingSpecifier mappedWithPlaceholer(String template) {
		BasicTitleSettingSpecifier spec = new BasicTitleSettingSpecifier(String.format(template,
				getKey()), getDefaultValue());
		spec.setTitle(getTitle());
		spec.setValueTitles(valueTitles);
		return spec;
	}

	@SuppressWarnings("deprecation")
	@Override
	public SettingSpecifier mappedWithMapper(Mapper mapper) {
		return mappedWithMapper((MappableSpecifier.Mapper) mapper);
	}

	@Override
	public SettingSpecifier mappedWithMapper(MappableSpecifier.Mapper mapper) {
		BasicTitleSettingSpecifier spec = new BasicTitleSettingSpecifier(mapper.mapKey(getKey()),
				getDefaultValue());
		spec.setTitle(getTitle());
		spec.setValueTitles(valueTitles);
		return spec;
	}

	public void setValueTitles(Map<String, String> valueTitles) {
		this.valueTitles = (valueTitles == null ? null : Collections.unmodifiableMap(valueTitles));
	}

}
