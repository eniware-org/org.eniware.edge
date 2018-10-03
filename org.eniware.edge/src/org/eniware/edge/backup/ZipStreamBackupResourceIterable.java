/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.backup;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.eniware.util.StringUtils;

/**
 * Iterator over a zip stream of backup resources.
 * 
 * @version 1.0
 * @since 1.46
 */
public final class ZipStreamBackupResourceIterable implements BackupResourceIterable {

	private final ZipInputStream zin;
	private final Set<String> providerKeySet;
	private final String backupKey;

	private final Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * Construct with a zip stream and properties.
	 * 
	 * The supported properties are those from {@link BackupManager}.
	 * 
	 * @param zin
	 *        The zip input stream to read.
	 * @param props
	 *        The properties.
	 */
	public ZipStreamBackupResourceIterable(ZipInputStream zin, Map<String, String> props) {
		this.zin = zin;
		this.providerKeySet = (props == null ? null
				: StringUtils.commaDelimitedStringToSet(
						props.get(DefaultBackupManager.RESOURCE_PROVIDER_FILTER)));
		this.backupKey = (props == null ? null : props.get(DefaultBackupManager.BACKUP_KEY));
	}

	@Override
	public void close() throws IOException {
		zin.close();
	}

	@Override
	public Iterator<BackupResource> iterator() {
		return new Iterator<BackupResource>() {

			private ZipEntry currEntry = null;
			private String currProviderKey = null;

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}

			@Override
			public boolean hasNext() {
				if ( currEntry == null ) {
					currProviderKey = null;
					ZipEntry entry;
					try {
						while ( true ) {
							entry = zin.getNextEntry();
							if ( entry != null ) {
								final String path = entry.getName();
								log.debug("Inspecting backup resource {}", path);
								final int providerIndex = path.indexOf('/');
								if ( providerIndex != -1 ) {
									final String providerKey = path.substring(0, providerIndex);
									if ( providerKeySet != null && !providerKeySet.isEmpty()
											&& !providerKeySet.contains(providerKey) ) {
										log.debug("Skipping backup {} resource {} (provider filtered)",
												backupKey, path);
										continue;
									}
									currProviderKey = providerKey;
									currEntry = entry;
									break;
								}
							} else {
								break;
							}
						}
					} catch ( IOException e ) {
						log.debug("IOException importing backup archive: {}", e.getMessage());
					}
				}
				return (currEntry != null);
			}

			@Override
			public BackupResource next() {
				if ( !hasNext() ) {
					return null;
				}
				final ZipEntry entry = currEntry;
				BackupResource result = null;
				if ( entry != null ) {
					result = new ZipStreamBackupResource(zin, entry, currProviderKey, entry.getName());
					currEntry = null;
					currProviderKey = null;
				}
				return result;
			}
		};
	}
}
