/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.settings.support;

import org.eniware.edge.domain.Location;
import org.eniware.edge.settings.LocationLookupSettingSpecifier;
import org.eniware.edge.settings.MappableSpecifier;
import org.eniware.edge.settings.SettingSpecifier;

/**
 * Basic implementation of {@link LocationLookupSettingSpecifier}.
 * 
 * @version 1.2
 */
public class BasicLocationLookupSettingSpecifier extends BaseKeyedSettingSpecifier<Long> implements
		LocationLookupSettingSpecifier {

	private final Location location;
	private final String locationType;

	/**
	 * Construct with a key and default value.
	 * 
	 * @param key
	 *        the key
	 * @param locationType
	 *        the location type
	 * @param location
	 *        the location
	 */
	public BasicLocationLookupSettingSpecifier(String key, String locationType, Location location) {
		super(key, (location == null ? null : location.getLocationId()));
		this.locationType = locationType;
		this.location = location;
	}

	@Override
	public SettingSpecifier mappedWithPlaceholer(String template) {
		BasicLocationLookupSettingSpecifier spec = new BasicLocationLookupSettingSpecifier(
				String.format(template, getKey()), locationType, location);
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
		BasicLocationLookupSettingSpecifier spec = new BasicLocationLookupSettingSpecifier(
				mapper.mapKey(getKey()), locationType, location);
		spec.setTitle(getTitle());
		return spec;
	}

	@Override
	public Location getLocation() {
		return location;
	}

	@Override
	public Long getLocationId() {
		return (location == null ? null : location.getLocationId());
	}

	@Override
	public String getLocationName() {
		return (location == null ? null : location.getLocationName());
	}

	@Override
	public String getSourceId() {
		return (location == null ? null : location.getSourceId());
	}

	@Override
	public String getSourceName() {
		return (location == null ? null : location.getSourceName());
	}

	@Override
	public String getLocationTypeKey() {
		String t = (locationType == null ? "basic" : locationType);
		if ( t.endsWith("Location") ) {
			t = t.substring(0, t.length() - 8);
		}
		t = t.toLowerCase();
		return t;
	}

}
