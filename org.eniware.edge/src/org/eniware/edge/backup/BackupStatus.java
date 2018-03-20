/* ==================================================================
 * BackupStatus.java - Mar 27, 2013 7:06:12 AM
 * 
 * Copyright 2007-2013 SolarNetwork.net Dev Team
 * 
 * This program is free software; you can redistribute it and/or 
 * modify it under the terms of the GNU General Public License as 
 * published by the Free Software Foundation; either version 2 of 
 * the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU 
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License 
 * along with this program; if not, write to the Free Software 
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 
 * 02111-1307 USA
 * ==================================================================
 */

package org.eniware.edge.backup;

/**
 * Status of the backup service.
 * 
 * @author matt
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