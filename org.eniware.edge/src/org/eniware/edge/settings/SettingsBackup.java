/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.settings;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A backup domain object.
 * 
 * @author matt
 * @version $Revision$
 */
public class SettingsBackup {

	private final String backupKey;
	private final Date backupDate;
	
	/**
	 * Construct with values.
	 * 
	 * @param backupKey the backup key
	 * @param backupDate the backup date
	 */
	public SettingsBackup(String backupKey, Date backupDate) {
		super();
		this.backupKey = backupKey;
		this.backupDate = backupDate;
	}
	
	/**
	 * Get a standardized representation of the backup date.
	 * 
	 * @return date string
	 */
	public String getStandardDateString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy HH:mm");
		return sdf.format(backupDate);
	}
	
	public String getBackupKey() {
		return backupKey;
	}
	public Date getBackupDate() {
		return backupDate;
	}
}
