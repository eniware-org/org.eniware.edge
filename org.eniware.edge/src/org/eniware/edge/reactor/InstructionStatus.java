/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.reactor;

import java.util.Date;
import java.util.Map;

/**
 * Status information for a single Instruction.
 * 
 * @version 1.1
 */
public interface InstructionStatus {

	enum InstructionState {

		/**
		 * The instruction has been received, but has not been looked at yet. It
		 * will be looked at as soon as possible.
		 */
		Received,

		/**
		 * The instruction has been received and is being executed currently.
		 */
		Executing,

		/**
		 * The instruction was received but has been declined and will not be
		 * executed.
		 */
		Declined,

		/**
		 * The instruction was received and has been executed.
		 */
		Completed;

	}

	/**
	 * A standard result parameter key for a message (typically an error
	 * message).
	 * 
	 * @since 1.1
	 */
	String MESSAGE_RESULT_PARAM = "message";

	/**
	 * A standard result parameter key for an error code.
	 * 
	 * @since 1.1
	 */
	String ERROR_CODE_RESULT_PARAM = "code";

	/**
	 * Get the ID of the instruction this state is associated with.
	 * 
	 * @return the primary key
	 */
	Long getInstructionId();

	/**
	 * Get the current instruction state.
	 * 
	 * @return the current instruction state
	 */
	InstructionState getInstructionState();

	/**
	 * Get the acknowledged instruction state.
	 * 
	 * <p>
	 * This is the state that has been posted back to EniwareNet.
	 * </p>
	 * 
	 * @return the acknowledged instruction state, or <em>null</em> if never
	 *         acknowledged
	 */
	InstructionState getAcknowledgedInstructionState();

	/**
	 * Get the date/time the instruction state was queried.
	 * 
	 * @return the status date
	 */
	Date getStatusDate();

	/**
	 * Get result parameters.
	 * 
	 * @return the result parameters, or {@literal null} if none available
	 * @since 1.1
	 */
	Map<String, ?> getResultParameters();

	/**
	 * Create a new InstructionStatus copy with a new state.
	 * 
	 * @param newState
	 *        the new state
	 * @return the new instance
	 */
	InstructionStatus newCopyWithState(InstructionState newState);

	/**
	 * Create a new InstructionStatus copy with a new state and result
	 * parameters.
	 * 
	 * @param newState
	 *        the new state
	 * @param resultParameters
	 *        the result parameters
	 * @return the new instance
	 * @since 1.1
	 */
	InstructionStatus newCopyWithState(InstructionState newState, Map<String, ?> resultParameters);

	/**
	 * Create a new InstructionStatus copy with a new acknowledged state.
	 * 
	 * @param newState
	 *        the new state
	 * @return the new instance
	 */
	InstructionStatus newCopyWithAcknowledgedState(InstructionState newState);

	/**
	 * Create a new InstructionStatus copy with a new acknowledged state.
	 * 
	 * @param newState
	 *        the new state
	 * @param resultParameters
	 *        the result parameters
	 * @return the new instance
	 * @since 1.1
	 */
	InstructionStatus newCopyWithAcknowledgedState(InstructionState newState,
			Map<String, ?> resultParameters);

}
