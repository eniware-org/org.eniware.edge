/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.reactor;

import java.io.IOException;
import java.util.List;

import org.eniware.edge.job.AbstractJob;
import org.eniware.edge.setup.SetupException;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.PersistJobDataAfterExecution;

/**
 * Job to look for instructions to update the acknowledgment status for.
 * 
 * @version 2.1
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class InstructionAcknowledgeJob extends AbstractJob {

	private InstructionDao instructionDao;
	private InstructionAcknowledgementService instructionAcknowledgementService;

	@Override
	protected void executeInternal(JobExecutionContext jobContext) throws Exception {
		try {
			List<Instruction> instructions = instructionDao.findInstructionsForAcknowledgement();
			if ( instructions.size() > 0 ) {
				instructionAcknowledgementService.acknowledgeInstructions(instructions);
				for ( Instruction instruction : instructions ) {
					instructionDao.storeInstructionStatus(instruction.getId(),
							instruction.getStatus().newCopyWithAcknowledgedState(
									instruction.getStatus().getInstructionState()));
				}
			}
		} catch ( RuntimeException e ) {
			Throwable root = e;
			while ( root.getCause() != null ) {
				root = root.getCause();
			}
			if ( root instanceof IOException ) {
				if ( log.isWarnEnabled() ) {
					log.warn("Network problem posting instruction acknowledgement ({}): {}",
							root.getClass().getSimpleName(), root.getMessage());
				}
			} else if ( root instanceof SetupException ) {
				log.warn("Unable to post instruction acknowledgement: {}", root.getMessage());
			} else {
				if ( log.isErrorEnabled() ) {
					log.error("Exception posting instruction acknowledgement", root);
				}
			}
		}
	}

	public InstructionDao getInstructionDao() {
		return instructionDao;
	}

	public void setInstructionDao(InstructionDao instructionDao) {
		this.instructionDao = instructionDao;
	}

	public InstructionAcknowledgementService getInstructionAcknowledgementService() {
		return instructionAcknowledgementService;
	}

	public void setInstructionAcknowledgementService(
			InstructionAcknowledgementService instructionAcknowledgementService) {
		this.instructionAcknowledgementService = instructionAcknowledgementService;
	}

}
