/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.backup;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * An iterator of {@link BackupResource} objects that adds a prefix to each
 * resource path.
 * 
 * @version 1.1
 * @since 1.46
 */
public class PrefixedBackupResourceIterator implements Iterator<BackupResource> {

	private final Iterator<BackupResource> delegate;
	private final String prefix;

	/**
	 * Construct with a delegate iterator.
	 * 
	 * @param delegate
	 *        The delegate iterator.
	 * @param prefix
	 *        The prefix to add to each {@link BackupResource#getBackupPath()}.
	 */
	public PrefixedBackupResourceIterator(Iterator<BackupResource> delegate, String prefix) {
		super();
		this.delegate = delegate;
		this.prefix = prefix;
	}

	@Override
	public boolean hasNext() {
		return delegate.hasNext();
	}

	@Override
	public BackupResource next() {
		final BackupResource r = delegate.next();
		return new BackupResource() {

			@Override
			public String getBackupPath() {
				return prefix + '/' + r.getBackupPath();
			}

			@Override
			public String getProviderKey() {
				return prefix;
			}

			@Override
			public InputStream getInputStream() throws IOException {
				return r.getInputStream();
			}

			@Override
			public long getModificationDate() {
				return r.getModificationDate();
			}

			@Override
			public String getSha256Digest() {
				return r.getSha256Digest();
			}

		};
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
}
