/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.settings;

import org.eniware.edge.domain.Location;

/**
 * A setting for a location ID.
 * 
 * @version 1.1
 */
public interface LocationLookupSettingSpecifier extends KeyedSettingSpecifier<Long>, Location {

	/**
	 * Get the location this setting is for.
	 * 
	 * @return a Location, or <em>null</em> if none available
	 */
	Location getLocation();

	/**
	 * Get the location type or tag, e.g. "weather", "price", etc.
	 * 
	 * @return the location type
	 */
	String getLocationTypeKey();

}
