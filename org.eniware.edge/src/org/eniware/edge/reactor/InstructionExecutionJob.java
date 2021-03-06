/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.reactor;

import java.util.ArrayList;
import java.util.List;

import org.eniware.edge.job.AbstractJob;
import org.eniware.edge.reactor.InstructionStatus.InstructionState;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.PersistJobDataAfterExecution;

/**
 * Job to look for received instructions and pass to handlers for execution.
 * 
 * <p>
 * This job uses the {@link InstructionDao} to query for all {@link Instruction}
 * entities with a state of {@link InstructionState#Received}. For each
 * {@link Instruction} found, it will attempt to find an
 * {@link InstructionHandler} that can execute the instruction.
 * </p>
 * 
 * <p>
 * This job will pass {@link Instruction#getTopic()} to every configured
 * {@link InstructionHandler#handlesTopic(String)} and if that returns
 * <em>true</em> the handler will be given the opportunity to handle the
 * instruction via {@link InstructionHandler#processInstruction(Instruction)}.
 * If the handler returns <em>null</em> or {@link InstructionState#Received}
 * (because it does not support the given Instruction) this job will move on to
 * the next configured handler and try it.
 * </p>
 * 
 * <p>
 * The first handler to return an {@link InstructionState} other than
 * {@link InstructionState#Received} will cause this job to persist the updated
 * Instruction state and not pass the instruction to any other handlers.
 * </p>
 * 
 * <p>
 * For handlers that implement {@link FeedbackInstructionHandler}, the
 * {@link FeedbackInstructionHandler#processInstructionWithFeedback(Instruction)}
 * method will be called instead, and if the returned status differs from the
 * instruction's previous status that new status will be persisted.
 * </p>
 *
 * @version 2.2
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class InstructionExecutionJob extends AbstractJob {

	/** Default value for the {@code executionReceivedHourLimit} property. */
	public static final int DEFAULT_EXECUTION_RECEIVED_HOUR_LIMIT = 24;

	private InstructionDao instructionDao;
	private List<InstructionHandler> handlers;
	private List<FeedbackInstructionHandler> feedbackHandlers;
	private int executionReceivedHourLimit = DEFAULT_EXECUTION_RECEIVED_HOUR_LIMIT;

	@Override
	protected void executeInternal(JobExecutionContext jobContext) throws Exception {
		List<Instruction> instructions = instructionDao
				.findInstructionsForState(InstructionState.Received);
		log.debug("Found {} instructions in Received state", instructions.size());
		final long now = System.currentTimeMillis();
		final long timeLimitMs = executionReceivedHourLimit * 60 * 60 * 1000;
		List<InstructionHandler> allHandlers = new ArrayList<InstructionHandler>(handlers);
		if ( feedbackHandlers != null ) {
			allHandlers.addAll(feedbackHandlers);
		}
		if ( allHandlers.isEmpty() ) {
			log.trace("No InstructionHandler instances available");
			return;
		}
		for ( Instruction instruction : instructions ) {
			boolean handled = false;
			log.trace("Passing instruction {} {} to {} handlers",
					new Object[] { instruction.getId(), instruction.getTopic(), allHandlers.size() });
			InstructionStatus currStatus = instruction.getStatus();
			for ( InstructionHandler handler : allHandlers ) {
				log.trace("Handler {} handles topic {}: {}", new Object[] { handler,
						instruction.getTopic(), handler.handlesTopic(instruction.getTopic()) });
				if ( handler.handlesTopic(instruction.getTopic()) ) {
					if ( handler instanceof FeedbackInstructionHandler ) {
						InstructionStatus status = ((FeedbackInstructionHandler) handler)
								.processInstructionWithFeedback(instruction);
						if ( status != null && (currStatus == null || !currStatus.equals(status)) ) {
							log.info("Instruction {} {} status changed to {}", instruction.getId(),
									instruction.getTopic(), status.getInstructionState());
							instructionDao.storeInstructionStatus(instruction.getId(), status);
							handled = true;
						}
					} else {
						InstructionState state = handler.processInstruction(instruction);
						if ( state != null && !InstructionState.Received.equals(state) ) {
							log.info("Instruction {} {} state changed to {}", instruction.getId(),
									instruction.getTopic(), state);
							instructionDao.storeInstructionStatus(instruction.getId(),
									instruction.getStatus().newCopyWithState(state));
							handled = true;
						}
					}
				}
			}
			if ( !handled && instruction.getInstructionDate() != null ) {
				long diffMs = now - instruction.getInstructionDate().getTime();
				if ( diffMs > timeLimitMs ) {
					log.info("Instruction {} {} not handled within {} hours; declining",
							instruction.getId(), instruction.getTopic(), executionReceivedHourLimit);
					instructionDao.storeInstructionStatus(instruction.getId(),
							instruction.getStatus().newCopyWithState(InstructionState.Declined));
				}
			}
		}
	}

	public InstructionDao getInstructionDao() {
		return instructionDao;
	}

	/**
	 * Set the {@link InstructionDao} to manage {@link Instruction} entities
	 * with.
	 * 
	 * @param instructionDao
	 *        the DAO to use
	 */
	public void setInstructionDao(InstructionDao instructionDao) {
		this.instructionDao = instructionDao;
	}

	public List<InstructionHandler> getHandlers() {
		return handlers;
	}

	/**
	 * Set a list of {@link InstructionHandler} instances to process
	 * instructions with.
	 * 
	 * <p>
	 * Note that {@link FeedbackInstructionHandler} instances <em>are</em>
	 * allowed to be configured here, and will be treated as such.
	 * </p>
	 * 
	 * @param handlers
	 *        the handlers
	 */
	public void setHandlers(List<InstructionHandler> handlers) {
		this.handlers = handlers;
	}

	public List<FeedbackInstructionHandler> getFeedbackHandlers() {
		return feedbackHandlers;
	}

	/**
	 * Set a list of {@link FeedbackInstructionHandler} instances to process
	 * instructions with.
	 * 
	 * @param feedbackHandlers
	 *        the feedbackHandlers to set
	 */
	public void setFeedbackHandlers(List<FeedbackInstructionHandler> feedbackHandlers) {
		this.feedbackHandlers = feedbackHandlers;
	}

	public int getExecutionReceivedHourLimit() {
		return executionReceivedHourLimit;
	}

	/**
	 * Set the minimum amount of time to wait before forcing instructions into
	 * the {@link InstructionState#Declined} state. This prevents instructions
	 * not handled by any handler from sticking around on the Edge indefinitely.
	 * Defaults to {@link #DEFAULT_EXECUTION_RECEIVED_HOUR_LIMIT}.
	 * 
	 * @param executionReceivedHourLimit
	 *        the hour limit
	 */
	public void setExecutionReceivedHourLimit(int executionReceivedHourLimit) {
		this.executionReceivedHourLimit = executionReceivedHourLimit;
	}

}
