/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.domain;

import java.math.BigDecimal;

/**
 * API for price data.
 * 
 * @version 1.1
 */
public interface PriceDatum extends Datum {

	/**
	 * A {@link org.eniware.domain.GeneralDatumSamples} instantaneous
	 * sample key for {@link PriceDatum#getPrice()} values.
	 */
	static final String PRICE_KEY = "price";

	/**
	 * Get the price value.
	 * 
	 * @return the price
	 */
	BigDecimal getPrice();

}
