/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.backup;

/**
 * Status of the backup service.
 * 
 * @version 1.0
 */
public enum BackupStatus {

	/** The backup has not been configured. */
	Unconfigured,

	/** The backup has been configured and scheduled. */
	Configured,

	/** A backup is being made right now. */
	RunningBackup,

	/** Restoring from a backup right now. */
	RestoringBackup,

	/** An error has occurred while making or restoring from a backup. */
	Error;

}
