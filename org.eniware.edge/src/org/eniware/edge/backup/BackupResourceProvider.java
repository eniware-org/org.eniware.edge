/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */
package org.eniware.edge.backup;

import java.util.Locale;

/**
 * A provider of {@link BackupResource} instances.
 * 
 * <p>
 * Any system component can register {@link BackupResourceProvider} instances to
 * include additional resources in backups.
 * </p>
 * 
 * @version 1.1
 */
public interface BackupResourceProvider {

	/**
	 * Get a key, unique among all other {@link BackupResourceProvider}
	 * instances.
	 * 
	 * <p>
	 * The key should contain only alpha-numeric and/or the period characters. A
	 * good candidate is the full class name of the provider.
	 * </p>
	 * 
	 * @return the provider key
	 */
	String getKey();

	/**
	 * Get the resources that should be backed up.
	 * 
	 * @return the resources, never <em>null</em>
	 */
	Iterable<BackupResource> getBackupResources();

	/**
	 * Restore a {@link BackupResoruce}.
	 * 
	 * @param resource
	 *        the resource to restore
	 * @return <em>true</em> if successful, <em>false</em> otherwise
	 */
	boolean restoreBackupResource(BackupResource resource);

	/**
	 * Get info about the provider.
	 * 
	 * @param locale
	 *        The desired locale of the information, or {@code null} for the
	 *        system locale.
	 * @return The info.
	 * @since 1.1
	 */
	BackupResourceProviderInfo providerInfo(Locale locale);

	/**
	 * Get info about a particular resource.
	 * 
	 * @param resource
	 *        The resource to get the information for.
	 * @param locale
	 *        The desired locale of the information, or {@code null} for the
	 *        system locale.
	 * @return The info, or {@code null} if none available.
	 * @since 1.1
	 */
	BackupResourceInfo resourceInfo(BackupResource resource, Locale locale);
}
