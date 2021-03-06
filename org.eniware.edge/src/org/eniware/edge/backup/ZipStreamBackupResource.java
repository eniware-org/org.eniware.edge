/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.backup;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import org.apache.commons.io.input.TeeInputStream;

/**
 * A zip input stream backup resource.
 * 
 * @version 1.2
 * @since 1.46
 */
public class ZipStreamBackupResource implements BackupResource {

	private final InputStream stream;
	private final ZipEntry entry;
	private final String providerKey;
	private final String path;

	private File tempFile;

	/**
	 * Construct with values.
	 * 
	 * @param archiveFile
	 *        the archive file
	 * @param entry
	 *        the entry previously obtained from the zip archive
	 * @param providerKey
	 *        the provider key
	 * @param path
	 *        the path to use
	 */
	public ZipStreamBackupResource(InputStream stream, ZipEntry entry, String providerKey, String path) {
		super();
		this.stream = stream;
		this.entry = entry;
		this.providerKey = providerKey;
		this.path = path;
	}

	@Override
	public String getProviderKey() {
		return providerKey;
	}

	@Override
	public String getBackupPath() {
		return path;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		// to support calling getInputStream() more than once, tee the input to a temp file
		// the first time, and subsequent times 
		if ( tempFile != null ) {
			return new BufferedInputStream(new FileInputStream(tempFile));
		}
		tempFile = File.createTempFile(entry.getName(), ".tmp");
		final BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(tempFile));
		return new TeeInputStream(new FilterInputStream(stream) {

			@Override
			public void close() throws IOException {
				out.flush();
				out.close();
			}
		}, out, false);
	}

	@Override
	public long getModificationDate() {
		return entry.getTime();
	}

	@Override
	protected void finalize() throws Throwable {
		if ( tempFile != null ) {
			tempFile.delete();
		}
		super.finalize();
	}

	@Override
	public String getSha256Digest() {
		return null;
	}

}
