/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.backup;

/**
 * Metadata for a {@link BackupResourceProvider}.
 * 
 * @author matt
 * @version 1.0
 * @since 1.46
 */
public interface BackupResourceProviderInfo {

	/**
	 * Get a unique key for the provider.
	 * 
	 * @return The unique provider key.
	 */
	String getProviderKey();

	/**
	 * Get a display-friendly name of the {@link BackupResourceProvider}.
	 * 
	 * @return The name.
	 */
	String getName();

	/**
	 * Get a display-friendly description of the {@link BackupResourceProvider}.
	 * 
	 * @return A display-friendly description.
	 */
	String getDescription();

}
