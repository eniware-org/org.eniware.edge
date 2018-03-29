/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.backup;

import java.io.IOException;
import java.io.InputStream;
import org.springframework.core.io.Resource;

/**
 * {@link BackupResource} implementation using a Spring {@link Resource}.
 * 
 * @version 1.2
 */
public class ResourceBackupResource implements BackupResource {

	private final Resource resource;
	private final String backupPath;
	private final String providerKey;
	private final String sha256Digest;

	/**
	 * Constructor.
	 * 
	 * The {@code providerKey} will be set to
	 * {@code net.solarnetwork.node.backup.FileBackupResourceProvider}.
	 * 
	 * @param resource
	 *        The resource.
	 * @param backupPath
	 *        The backup path.
	 */
	public ResourceBackupResource(Resource resource, String backupPath) {
		this(resource, backupPath, FileBackupResourceProvider.class.getName(), null);
	}

	/**
	 * Construct with a specific provider key.
	 * 
	 * @param resource
	 *        The resource.
	 * @param backupPath
	 *        The backup path.
	 * @param providerKey
	 *        The provider key.
	 * @since 1.1
	 */
	public ResourceBackupResource(Resource resource, String backupPath, String providerKey) {
		this(resource, backupPath, providerKey, null);
	}

	/**
	 * Construct with a specific provider key.
	 * 
	 * @param resource
	 *        The resource.
	 * @param backupPath
	 *        The backup path.
	 * @param providerKey
	 *        The provider key.
	 * @param sha256Digest
	 *        The SHA-256 digest, hex encoded
	 * @since 1.2
	 */
	public ResourceBackupResource(Resource resource, String backupPath, String providerKey,
			String sha256Digest) {
		super();
		this.resource = resource;
		this.backupPath = backupPath;
		this.providerKey = providerKey;
		this.sha256Digest = sha256Digest;
	}

	@Override
	public String getProviderKey() {
		return providerKey;
	}

	@Override
	public String getBackupPath() {
		return backupPath;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return resource.getInputStream();
	}

	@Override
	public long getModificationDate() {
		try {
			return resource.getFile().lastModified();
		} catch ( IOException e ) {
			// ignore
		}
		return -1;
	}

	@Override
	public String getSha256Digest() {
		return sha256Digest;
	}

}
