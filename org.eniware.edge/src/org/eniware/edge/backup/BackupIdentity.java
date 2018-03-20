/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.backup;

import java.util.Date;

/**
 * Identity information about a {@link Backup}.
 * 
 * @author matt
 * @version 1.0
 * @since 1.55
 */
public interface BackupIdentity {

	/**
	 * Get a unique key for the backup.
	 * 
	 * @return the backup key
	 */
	String getKey();

	/**
	 * Get the node ID associated with the backup.
	 * 
	 * @return the node ID
	 */
	Long getNodeId();

	/**
	 * Get the date the backup was created.
	 * 
	 * @return the backup date
	 */
	Date getDate();

	/**
	 * Get an optional qualifier.
	 * 
	 * <p>
	 * A qualifier can be used to provide a backup with a more descriptive name
	 * or tag.
	 * </p>
	 * 
	 * @return the qualifier, or {@literal null} if not known
	 */
	String getQualifier();

}
