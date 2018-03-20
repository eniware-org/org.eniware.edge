/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.backup;

import java.io.Closeable;
import java.util.Iterator;

/**
 * An iterator over backup resources.
 * 
 * <p>
 * This iterator must be closed when finished using it, to correctly free up any
 * internal resources used by the instance. Once closed, {@link #iterator()}
 * should not be used again, and any {@link BackupResoruce} instanced returned
 * by any previously obtained {@link Iterator} instances are not considered
 * valid.
 * </p>
 * 
 * @author matt
 * @version 1.0
 */
public interface BackupResourceIterable extends Iterable<BackupResource>, Closeable {

}
