/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.backup;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

/**
 * Simple implemenation of {@link BackupResourceIterable} that uses a
 * collection.
 * 
 * @author matt
 * @version 1.0
 */
public class CollectionBackupResourceIterable implements BackupResourceIterable {

	private final Collection<BackupResource> collection;

	public CollectionBackupResourceIterable(Collection<BackupResource> collection) {
		super();
		this.collection = collection;
	}

	@Override
	public Iterator<BackupResource> iterator() {
		return collection.iterator();
	}

	@Override
	public void close() throws IOException {
		// nothing to do here
	}

}
