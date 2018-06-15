/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.backup;

/**
 * Basic implementation of {@link BackupResourceProviderInfo}.
 * 
 * @version 1.0
 * @since 1.46
 */
public class SimpleBackupResourceProviderInfo implements BackupResourceProviderInfo {

	private final String providerKey;
	private final String name;
	private final String description;

	/**
	 * Construct with values.
	 * 
	 * @param providerKey
	 *        The provider key.
	 * @param name
	 *        The name.
	 * @param description
	 *        The description.
	 */
	public SimpleBackupResourceProviderInfo(String providerKey, String name, String description) {
		super();
		this.providerKey = providerKey;
		this.name = name;
		this.description = description;
	}

	@Override
	public String getProviderKey() {
		return providerKey;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return description;
	}

}
