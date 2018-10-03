/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */
package org.eniware.edge.domain;

/**
 * API for a location object.
 * 
 * <p>
 * A <em>location</em> is a standardized reference to some place or some source
 * of information, for example a weather location, a price location, etc.
 * </p>
 * 
 * @version 1.1
 */
public interface Location {

	/** A price location type. */
	static final String PRICE_TYPE = "price";

	/** A day location type. */
	static final String DAY_TYPE = "day";

	/** A weather location type. */
	static final String WEATHER_TYPE = "weather";

	/**
	 * Get a unique ID of this location.
	 * 
	 * @return the location ID
	 */
	Long getLocationId();

	/**
	 * Get a name for this location.
	 * 
	 * @return the location name
	 */
	String getLocationName();

	/**
	 * Get a unique ID of the source of this location.
	 * 
	 * @return the source ID
	 */
	String getSourceId();

	/**
	 * Get the name of the source of this location.
	 * 
	 * @return the source name
	 */
	String getSourceName();

}
