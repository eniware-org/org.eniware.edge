/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.reactor;

import org.eniware.edge.job.AbstractJob;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.PersistJobDataAfterExecution;

/**
 * Job to clean out old instructions so they don't build up.
 * 
 * @author matt
 * @version 2.0
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class InstructionCleanerJob extends AbstractJob {

	/** The default value for the {@code hours} property. */
	public static final int DEFAULT_HOURS = 72;

	private int hours = DEFAULT_HOURS;
	private InstructionDao instructionDao = null;

	@Override
	protected void executeInternal(JobExecutionContext jobContext) throws Exception {
		int deleted = instructionDao.deleteHandledInstructionsOlderThan(hours);
		if ( deleted > 0 ) {
			log.info("Deleted {} handled instructions older than {} hours", deleted, hours);
		}
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public InstructionDao getInstructionDao() {
		return instructionDao;
	}

	public void setInstructionDao(InstructionDao instructionDao) {
		this.instructionDao = instructionDao;
	}

}
