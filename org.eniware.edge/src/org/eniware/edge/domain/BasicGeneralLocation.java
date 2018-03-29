/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */
package org.eniware.edge.domain;

import net.solarnetwork.domain.GeneralDatumMetadata;
import net.solarnetwork.domain.GeneralLocationSourceMetadata;

/**
 * Basic implementation of {@link GeneralLocation}.
 * 
 * @version 1.0
 */
public class BasicGeneralLocation extends BasicLocation implements GeneralLocation {

	private GeneralLocationSourceMetadata sourceMetadata;

	@Override
	public GeneralDatumMetadata getMetadata() {
		return (sourceMetadata == null ? null : sourceMetadata.getMeta());
	}

	@Override
	public String getLocationName() {
		if ( sourceMetadata != null ) {
			return sourceMetadata.getMeta().getInfoString("name");
		}
		return super.getLocationName();
	}

	@Override
	public String getSourceName() {
		if ( sourceMetadata != null ) {
			return getSourceId();
		}
		return super.getSourceName();
	}

	public GeneralLocationSourceMetadata getSourceMetadata() {
		return sourceMetadata;
	}

	public void setSourceMetadata(GeneralLocationSourceMetadata sourceMetadata) {
		this.sourceMetadata = sourceMetadata;
	}

}
