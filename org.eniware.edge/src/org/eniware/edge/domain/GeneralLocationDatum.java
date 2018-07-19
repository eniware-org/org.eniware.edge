/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.domain;

import org.eniware.domain.GeneralDatumSamples;
import org.eniware.domain.GeneralLocationDatumSamples;

/**
 * General location datum.
 * 
 * @version 1.1
 */
public class GeneralLocationDatum extends GeneralEdgeDatum {

	private Long locationId;

	/**
	 * Default constructor.
	 */
	public GeneralLocationDatum() {
		super();
		setSourceId(null);
	}

	@Override
	protected GeneralDatumSamples newSamplesInstance() {
		return new GeneralLocationDatumSamples();
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

}
