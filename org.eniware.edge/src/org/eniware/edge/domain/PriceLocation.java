/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.domain;


/**
 * Information about a specific price location.
 * 
 * @author matt
 * @version 1.1
 */
public class PriceLocation extends BasicLocation {

	private String currency;
	private String unit;

	@Override
	public String toString() {
		return "PriceDatum{locationId=" + getLocationId() + ",currency=" + this.currency + ",unit="
				+ this.unit + '}';
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

}
