/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */
package org.eniware.edge.backup;

/**
 * Metadata about a {@link BackupResource}.
 * 
 * @version 1.0
 * @since 1.46
 */
public interface BackupResourceInfo {

	/**
	 * Get the key of the {@link BackupResourceProvider} that provided this
	 * resource.
	 * 
	 * @return The provider key.
	 */
	String getProviderKey();

	/**
	 * Get a display-friendly name of the resource.
	 * 
	 * @return The name.
	 */
	String getName();

	/**
	 * Get a display-friendly description of the resource.
	 * 
	 * @return A display-friendly description.
	 */
	String getDescription();

}
