/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.setup.web;

import java.util.HashMap;
import java.util.Map;
import org.springframework.util.StringUtils;
import org.eniware.edge.backup.BackupManager;

/**
 * Options to pass to backup operations.
 * 
 * @version 1.0
 */
public class BackupOptions {

	private String key;
	private String[] providers;

	/**
	 * Get a property map suitable for passing into various
	 * {@link BackupManager} methods.
	 * 
	 * @return A property map, or {@code null}.
	 */
	public Map<String, String> asBackupManagerProperties() {
		if ( providers == null || providers.length < 1 ) {
			return null;
		}
		Map<String, String> props = new HashMap<String, String>(1);
		props.put(BackupManager.RESOURCE_PROVIDER_FILTER,
				StringUtils.arrayToCommaDelimitedString(providers));
		return props;
	}

	/**
	 * Get the {@link org.eniware.edge.backup.Backup} key.
	 * 
	 * @return The backup key.
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Set the {@link org.eniware.edge.backup.Backup} key.
	 * 
	 * @param key
	 *        The backup key.
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * Get the list of
	 * {@link org.eniware.edge.backup.BackupResourceProvider} keys to limit
	 * the backup to.
	 * 
	 * @return The list of provider keys to limit the backup to, or {@code null}
	 *         for all providers.
	 */
	public String[] getProviders() {
		return providers;
	}

	/**
	 * Set the list of
	 * {@link org.eniware.edge.backup.BackupResourceProvider} keys to limit
	 * the backup to.
	 * 
	 * @param providers
	 *        The list of provider keys to limit the backup to, or {@code null}
	 *        for all providers.
	 */
	public void setProviders(String[] providers) {
		this.providers = providers;
	}

}
