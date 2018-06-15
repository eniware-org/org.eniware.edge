/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.domain;

import org.eniware.domain.GeneralDatumMetadata;

/**
 * Extension of {@link Location} to add general metadata support.
 * 
 * @version 1.0
 */
public interface GeneralLocation extends Location {

	/**
	 * Get metadata about the location.
	 * 
	 * @return metadata
	 */
	GeneralDatumMetadata getMetadata();

}
