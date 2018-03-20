/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.domain;

/**
 * Standardized API for datum associated with a price to implement.
 * 
 * @author matt
 * @version 1.1
 */
public interface PricedDatum {

	/**
	 * A {@link net.solarnetwork.domain.GeneralNodeDatumSamples} status sample
	 * key for {@link #getPriceLocationId()} values.
	 */
	public static final String PRICE_LOCATION_KEY = "priceLocationId";

	/**
	 * A {@link net.solarnetwork.domain.GeneralNodeDatumSamples} status sample
	 * key for {@link #getPriceSourceId()} values.
	 * 
	 * @since 1.1
	 */
	public static final String PRICE_SOURCE_KEY = "priceSourceId";

	/**
	 * Get the location ID associated with this datum.
	 * 
	 * @return the price location ID
	 */
	public Long getPriceLocationId();

	/**
	 * Get the location source ID associated with this datum.
	 * 
	 * @return the price source ID
	 * @since 1.1
	 */
	public String getPriceSourceId();

}
