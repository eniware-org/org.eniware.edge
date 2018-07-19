/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge;

import org.eniware.domain.GeneralDatumMetadata;

/**
 * API for manipulating {@link GeneralDatumMetadata} associated with a Edge.
 * 
 * @version 1.0
 */
public interface DatumMetadataService {

	/**
	 * Add metadata to a specific source. If metadata already exists for the
	 * given source, the values will be merged such that tags are only added and
	 * only new info values will be added.
	 * 
	 * @param sourceId
	 *        the source ID to add to
	 * @param meta
	 *        the metadata to add
	 */
	void addSourceMetadata(String sourceId, GeneralDatumMetadata meta);

	/**
	 * Find datum metadata for a given source.
	 * 
	 * @param sourceId
	 *        the sourceId to get the metadta for
	 * @return the metadata, or <em>null</em> if none available
	 */
	GeneralDatumMetadata getSourceMetadata(String sourceId);

}
