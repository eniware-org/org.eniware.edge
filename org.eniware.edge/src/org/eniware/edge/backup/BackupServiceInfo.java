/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */
package org.eniware.edge.backup;

import java.util.Date;

/**
 * Information about the backup service.
 * 
 * @version 1.0
 */
public interface BackupServiceInfo {

	/**
	 * Get the overall backup service status.
	 * 
	 * @return the current status
	 */
	BackupStatus getStatus();

	/**
	 * Get the date of the most recent successful backup.
	 * 
	 * @return the backup date, or <em>null</em> if none available
	 */
	Date getMostRecentBackupDate();

}
