/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.reactor;

/**
 * Extension of {@link InstructionHandler} that provides feedback on the
 * instruction status.
 * 
 * @version 1.0
 * @since 1.50
 */
public interface FeedbackInstructionHandler extends InstructionHandler {

	/**
	 * Process an instruction, providing status feedback.
	 * 
	 * @param instruction
	 *        the instruction to process
	 * @return new status the instruction, or <em>null</em> if the instruction
	 *         was not handled
	 */
	InstructionStatus processInstructionWithFeedback(Instruction instruction);

}
