/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */
package org.eniware.edge.domain;

/**
 * Base class for {@link EnergyDatum} implementations.
 * 
 * @version 1.1
 * @deprecated use {@link GeneralEdgeEnergyDatum}
 */
@Deprecated
public abstract class BaseEnergyDatum extends BaseDatum implements EnergyDatum {

	private Integer watts = null;
	private Long wattHourReading = null;

	@Override
	public Integer getWatts() {
		return watts;
	}

	public void setWatts(Integer watts) {
		this.watts = watts;
	}

	@Override
	public Long getWattHourReading() {
		return wattHourReading;
	}

	public void setWattHourReading(Long wattHourReading) {
		this.wattHourReading = wattHourReading;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "{sourceId=" + getSourceId() + ",watts=" + getWatts()
				+ ",wattHourReading=" + getWattHourReading() + '}';
	}
}
