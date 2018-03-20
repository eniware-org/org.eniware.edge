/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.domain;

import java.math.BigDecimal;

/**
 * Extension of {@link GeneralLocationDatum} that implements {@link PriceDatum}.
 * 
 * @author matt
 * @version 1.0
 */
public class GeneralPriceDatum extends GeneralLocationDatum implements PriceDatum {

	@Override
	public BigDecimal getPrice() {
		return getInstantaneousSampleBigDecimal(PRICE_KEY);
	}

	public void setPrice(BigDecimal value) {
		putInstantaneousSampleValue(PRICE_KEY, value);
	}

}
