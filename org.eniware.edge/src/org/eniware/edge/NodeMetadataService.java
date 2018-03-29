/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge;

import net.solarnetwork.domain.GeneralDatumMetadata;

/**
 * API for managing node metadata.
 * 
 * @version 1.0
 * @since 1.50
 */
public interface NodeMetadataService {

	/**
	 * Add node metadata. If metadata already exists for the given source, the
	 * values will be merged such that tags are only added and only new info
	 * values will be added.
	 * 
	 * @param sourceId
	 *        the source ID to add to
	 * @param meta
	 *        the metadata to add
	 */
	void addNodeMetadata(GeneralDatumMetadata meta);

	/**
	 * Get all metadata for the active node.
	 * 
	 * @param sourceId
	 *        the sourceId to get the metadta for
	 * @return the metadata, or <em>null</em> if none available
	 */
	GeneralDatumMetadata getNodeMetadata();

}
