/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.backup;

import java.util.Date;

/**
 * Simple implementation of {@link BackupServiceInfo}.
 * 
 * @version 1.0
 */
public class SimpleBackupServiceInfo implements BackupServiceInfo {

	private final Date mostRecentBackupDate;
	private final BackupStatus status;

	/**
	 * Construct with values.
	 * 
	 * @param mostRecentBackupDate
	 *        the backup date
	 * @param status
	 *        the status
	 */
	public SimpleBackupServiceInfo(Date mostRecentBackupDate, BackupStatus status) {
		super();
		this.mostRecentBackupDate = mostRecentBackupDate;
		this.status = status;
	}

	@Override
	public Date getMostRecentBackupDate() {
		return mostRecentBackupDate;
	}

	@Override
	public BackupStatus getStatus() {
		return status;
	}

}
