/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */
package org.eniware.edge.backup;

import java.util.Collection;
import java.util.Date;

/**
 * Basic implementation of {@link BackupInfo}.
 * 
 * @version 1.1
 * @since 1.46
 */
public class SimpleBackupInfo extends SimpleBackupIdentity implements BackupInfo {

	private final Collection<BackupResourceProviderInfo> providerInfos;
	private final Collection<BackupResourceInfo> resourceInfos;

	/**
	 * Constructor.
	 * 
	 * @param key
	 *        The backup key.
	 * @param date
	 *        The backup date.
	 * @param providerInfos
	 *        The providers.
	 * @param resourceInfos
	 *        The resources.
	 */
	public SimpleBackupInfo(String key, Date date, Collection<BackupResourceProviderInfo> providerInfos,
			Collection<BackupResourceInfo> resourceInfos) {
		this(key, date, null, null, providerInfos, resourceInfos);
	}

	/**
	 * Constructor.
	 * 
	 * @param key
	 *        The backup key.
	 * @param date
	 *        The backup date.
	 * @param EdgeId
	 *        The Edge ID.
	 * @param qualifier
	 *        The qualifier.
	 * @param providerInfos
	 *        The providers.
	 * @param resourceInfos
	 *        The resources.
	 */
	public SimpleBackupInfo(String key, Date date, Long EdgeId, String qualifier,
			Collection<BackupResourceProviderInfo> providerInfos,
			Collection<BackupResourceInfo> resourceInfos) {
		super(key, date, null, null);
		this.providerInfos = providerInfos;
		this.resourceInfos = resourceInfos;
	}

	@Override
	public Collection<BackupResourceProviderInfo> getProviderInfos() {
		return providerInfos;
	}

	@Override
	public Collection<BackupResourceInfo> getResourceInfos() {
		return resourceInfos;
	}

}
