/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.backup;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * {@link BackupResource} for a file in a zip archive.
 * 
 * @author matt
 * @version 1.2
 */
public class ZipEntryBackupResource implements BackupResource {

	private final ZipFile archiveFile;
	private final ZipEntry entry;
	private final String providerKey;

	/**
	 * Constructor.
	 * 
	 * The {@code providerKey} will be set to {@code null}.
	 * 
	 * @param archiveFile
	 *        the archive file
	 * @param entry
	 *        the entry previously obtained from the zip archive
	 */
	public ZipEntryBackupResource(ZipFile archiveFile, ZipEntry entry) {
		this(archiveFile, entry, null);
	}

	/**
	 * Construct with values.
	 * 
	 * @param archiveFile
	 *        the archive file
	 * @param entry
	 *        the entry previously obtained from the zip archive
	 * @param providerKey
	 *        the provider key
	 * @since 1.1
	 */
	public ZipEntryBackupResource(ZipFile archiveFile, ZipEntry entry, String providerKey) {
		super();
		this.archiveFile = archiveFile;
		this.entry = entry;
		this.providerKey = providerKey;
	}

	@Override
	public String getProviderKey() {
		return providerKey;
	}

	@Override
	public String getBackupPath() {
		return entry.getName();
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return archiveFile.getInputStream(entry);
	}

	@Override
	public long getModificationDate() {
		return entry.getTime();
	}

	@Override
	public String getSha256Digest() {
		return null;
	}

}
