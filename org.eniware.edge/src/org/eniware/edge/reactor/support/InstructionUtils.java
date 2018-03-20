/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.reactor.support;

import java.util.Date;

import org.eniware.edge.reactor.Instruction;
import org.eniware.edge.reactor.InstructionHandler;
import org.eniware.edge.reactor.InstructionStatus.InstructionState;

/**
 * Utilities for dealing with common Instruction patterns.
 * 
 * @author matt
 * @version 1.1
 */
public final class InstructionUtils {

	private InstructionUtils() {
		// can't create me
	}

	/**
	 * Given a collection of {@link InstructionHandler} objects, find one that
	 * can handle a specific {@link Instruction}.
	 * 
	 * <p>
	 * This method will iterate over the provided {@code handlers} and ask each
	 * one if they handle the topic of the provided {@code instruction}. If the
	 * handler does, it is asked to process the instruction. The first non-null
	 * result returned by a handler will be returned.
	 * </p>
	 * 
	 * @param handlers
	 *        the collection of handlers to search over
	 * @param instruction
	 *        the instruction to ask the handlers to perform
	 * @return the first non-null result of
	 *         {@link InstructionHandler#processInstruction(Instruction)}, or
	 *         <em>null</em> if no handler returns a value
	 */
	public static InstructionState handleInstruction(Iterable<InstructionHandler> handlers,
			Instruction instruction) {
		InstructionState result = null;
		for ( InstructionHandler handler : handlers ) {
			if ( handler.handlesTopic(instruction.getTopic()) ) {
				result = handler.processInstruction(instruction);
			}
			if ( result != null ) {
				break;
			}
		}
		return result;
	}

	/**
	 * Given a collection of {@link InstructionHandler} objects, find the first
	 * one that can handle the
	 * {@link InstructionHandler#TOPIC_SET_CONTROL_PARAMETER} topic for the
	 * given {@code controlId} and {@code controlValue}. The
	 * {@link Instruction#getRemoteInstructionId()} and
	 * {@link Instruction#getInstructorId()} values are set to
	 * {@link Instruction#LOCAL_INSTRUCTION_ID}.
	 * 
	 * @param handlers
	 *        the collection of handlers to search over
	 * @param controlId
	 *        the ID of the control to set the control value to
	 * @param controlValue
	 *        the value to set the control to
	 * @return the first non-null result of
	 *         {@link InstructionHandler#processInstruction(Instruction)}, or
	 *         <em>null</em> if no handler returns a value
	 */
	public static InstructionState setControlParameter(final Iterable<InstructionHandler> handlers,
			final String controlId, final String controlValue) {
		BasicInstruction instr = new BasicInstruction(InstructionHandler.TOPIC_SET_CONTROL_PARAMETER,
				new Date(), Instruction.LOCAL_INSTRUCTION_ID, Instruction.LOCAL_INSTRUCTION_ID, null);
		instr.addParameter(controlId, String.valueOf(controlValue));
		return InstructionUtils.handleInstruction(handlers, instr);
	}

}
