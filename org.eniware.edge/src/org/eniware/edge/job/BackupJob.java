/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.job;

import org.eniware.edge.backup.BackupManager;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.PersistJobDataAfterExecution;

import org.eniware.util.OptionalService;

/**
 * Scheduled backup job using {@link BackupManager}.
 * 
 * @version 2.0
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class BackupJob extends AbstractJob {

	private OptionalService<BackupManager> backupManagerTracker;

	@Override
	protected void executeInternal(JobExecutionContext jobContext) throws Exception {
		BackupManager manager = backupManagerTracker.service();
		if ( manager == null ) {
			log.debug("No backup manager available, cannot perform backup");
			return;
		}

		manager.createBackup();
	}
}
