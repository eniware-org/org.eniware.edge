/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge;

import java.util.Collection;
import java.util.Set;

import org.eniware.edge.domain.Location;

import net.solarnetwork.domain.GeneralLocationSourceMetadata;

/**
 * API for querying for locations.
 * 
 * @author matt
 * @version 1.1
 */
public interface LocationService {

	/** An unknown source, which is always available. */
	static final String UNKNOWN_SOURCE = "Unknown";

	/** An unknown location, which is always available for the UNKNOWN source. */
	static final String UNKNOWN_LOCATION = "Unknown";

	/**
	 * Look up a Location based on a source name and location name.
	 * 
	 * @param locationType
	 *        the type of location to look up
	 * @param sourceName
	 *        the source of the location data
	 * @param locationName
	 *        the location within the source (e.g. HAY2201)
	 * @return the matching location, or <em>null</em> if not found
	 * @deprecated see {@link #findLocations(String, Set)}
	 */
	@Deprecated
	<T extends Location> Collection<? extends Location> findLocations(Class<T> locationType,
			String sourceName, String locationName);

	/**
	 * Get a specific Location based on an ID.
	 * 
	 * @param locationType
	 *        the type of location to look up
	 * @param locationId
	 *        the ID of the location to find
	 * @return the location, or <em>null</em> if not found
	 * @deprecated see {@link #getLocation(Long, String)}
	 */
	@Deprecated
	<T extends Location> T getLocation(Class<T> locationType, Long locationId);

	/**
	 * Query for general location metadata.
	 * 
	 * @param query
	 *        the query text
	 * @param sourceId
	 *        an optional source ID to limit the results to
	 * @param tags
	 *        the optional tags
	 * @return the matching location metadata, never <em>null</em>
	 * @since 1.1
	 */
	Collection<GeneralLocationSourceMetadata> findLocationMetadata(String query, String sourceId,
			Set<String> tags);

	/**
	 * Get a specific general location metadata.
	 * 
	 * @param locationId
	 *        the location ID
	 * @param sourceId
	 *        the source ID
	 * @return the location metadata, or <em>null</em> if not found
	 * @since 1.1
	 */
	GeneralLocationSourceMetadata getLocationMetadata(Long locationId, String sourceId);
}
