/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.backup;

import java.util.Collection;

/**
 * Metadata about a {@link Backup}.
 * 
 * @version 1.1
 * @since 1.46
 */
public interface BackupInfo extends BackupIdentity {

	/**
	 * Get a list of all providers included in the backup.
	 * 
	 * @return The list of providers, or an empty list.
	 */
	Collection<BackupResourceProviderInfo> getProviderInfos();

	/**
	 * Get a list of all resources included in the backup.
	 * 
	 * The resources should be ordered such that all resources for a given
	 * provider are together.
	 * 
	 * @return The list of resources, or an empty list.
	 */
	Collection<BackupResourceInfo> getResourceInfos();

}
