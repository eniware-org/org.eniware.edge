/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.settings.support;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eniware.edge.settings.GroupSettingSpecifier;
import org.eniware.edge.settings.MappableSpecifier;
import org.eniware.edge.settings.SettingSpecifier;

/**
 * Basic implementation of {@link GroupSettingSpecifier}.
 * 
 * @author matt
 * @version 1.1
 */
public class BasicGroupSettingSpecifier extends BaseSettingSpecifier implements GroupSettingSpecifier {

	private final String key;
	private final String footerText;
	private final List<SettingSpecifier> groupSettings;
	private final boolean dynamic;

	/**
	 * Construct without a key. The {@code dynamic} property will be set to
	 * <em>false</em>.
	 * 
	 * @param settings
	 *        The group settings.
	 */
	public BasicGroupSettingSpecifier(List<SettingSpecifier> settings) {
		this(null, settings, false, null);
	}

	/**
	 * Construct with the group settings. The {@code dynamic} property will be
	 * set to <em>false</em>.
	 * 
	 * @param groupKey
	 *        The key for the entire group.
	 * @param settings
	 *        The group settings.
	 */
	public BasicGroupSettingSpecifier(String groupKey, List<SettingSpecifier> settings) {
		this(groupKey, settings, false, null);
	}

	/**
	 * Construct with settings and dynamic flag.
	 * 
	 * @param groupKey
	 *        The key for the entire group.
	 * @param settings
	 *        The group settings.
	 * @param dynamic
	 *        The dynamic flag.
	 */
	public BasicGroupSettingSpecifier(String groupKey, List<SettingSpecifier> settings, boolean dynamic) {
		this(groupKey, settings, dynamic, null);
	}

	/**
	 * Construct with values.
	 * 
	 * @param groupKey
	 *        The key for the entire group.
	 * @param settings
	 *        The group settings.
	 * @param dynamic
	 *        The dynamic flag.
	 * @param footerText
	 *        The footer text.
	 */
	public BasicGroupSettingSpecifier(String groupKey, List<SettingSpecifier> settings, boolean dynamic,
			String footerText) {
		super();
		this.key = groupKey;
		this.groupSettings = Collections.unmodifiableList(settings);
		this.dynamic = dynamic;
		this.footerText = footerText;
	}

	@Override
	public String getFooterText() {
		return this.footerText;
	}

	@Override
	public List<SettingSpecifier> getGroupSettings() {
		return this.groupSettings;
	}

	@Override
	public boolean isDynamic() {
		return dynamic;
	}

	@Override
	public String getKey() {
		return key;
	}

	@Override
	public SettingSpecifier mappedWithPlaceholer(final String template) {
		List<SettingSpecifier> gSettings = getGroupSettings();
		List<SettingSpecifier> mappedGroupSettings = null;
		if ( gSettings != null ) {
			mappedGroupSettings = new ArrayList<SettingSpecifier>(gSettings.size());
			for ( SettingSpecifier s : gSettings ) {
				if ( s instanceof MappableSpecifier ) {
					MappableSpecifier ms = (MappableSpecifier) s;
					mappedGroupSettings.add(ms.mappedWithPlaceholer(template));
				} else {
					mappedGroupSettings.add(s);
				}
			}
		}
		final String key = getKey();
		final String mappedKey = (key == null ? null : String.format(template, key));
		BasicGroupSettingSpecifier spec = new BasicGroupSettingSpecifier(mappedKey, mappedGroupSettings,
				isDynamic(), getFooterText());
		spec.setTitle(getTitle());
		return spec;
	}

	@Override
	public SettingSpecifier mappedTo(final String prefix) {
		return mappedWithPlaceholer(prefix + "%s");
	}

	@Override
	public SettingSpecifier mappedWithMapper(final Mapper mapper) {
		List<SettingSpecifier> gSettings = getGroupSettings();
		List<SettingSpecifier> mappedGroupSettings = null;
		if ( gSettings != null ) {
			mappedGroupSettings = new ArrayList<SettingSpecifier>(gSettings.size());
			for ( SettingSpecifier s : gSettings ) {
				if ( s instanceof MappableSpecifier ) {
					MappableSpecifier ms = (MappableSpecifier) s;
					mappedGroupSettings.add(ms.mappedWithMapper(mapper));
				} else {
					mappedGroupSettings.add(s);
				}
			}
		}
		final String key = getKey();
		final String mappedKey = (key == null ? null : mapper.mapKey(key));
		BasicGroupSettingSpecifier spec = new BasicGroupSettingSpecifier(mappedKey, mappedGroupSettings,
				isDynamic(), getFooterText());
		spec.setTitle(getTitle());
		return spec;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(getClass().getSimpleName());
		builder.append("{key=").append(key);
		builder.append(",dynamic=").append(dynamic);
		builder.append(",count=").append(groupSettings == null ? 0 : groupSettings.size());
		builder.append("}");
		return builder.toString();
	}

}
