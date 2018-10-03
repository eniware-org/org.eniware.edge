/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.backup;

/**
 * An individual backup.
 * 
 * @version 1.2
 */
public interface Backup extends BackupIdentity {

	/**
	 * Boolean flag indicating if this backup is complete.
	 * 
	 * @return <em>true</em> if the backup is finished, <em>false</em> otherwise
	 */
	boolean isComplete();

	/**
	 * Get the size, in bytes, of this backup.
	 * 
	 * @return the size in bytes, or <em>null</em> if not known
	 */
	Long getSize();
}
