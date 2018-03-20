/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.reactor;

import java.util.List;

import org.eniware.edge.reactor.InstructionStatus.InstructionState;

/**
 * DAO API for Instructor entities.
 * 
 * @author matt
 * @version 1.0
 */
public interface InstructionDao {

	/**
	 * Store an Instruction instance and return its primary key.
	 * 
	 * @param instruction
	 * @return the local primary key
	 */
	Long storeInstruction(Instruction instruction);

	/**
	 * Get an Instruction instance by ID.
	 * 
	 * @param instructionId
	 *        the instruction ID
	 * @return the Instruction, or <em>null</em> if not found
	 */
	Instruction getInstruction(Long instructionId);

	/**
	 * Get an Instruction instance by the remote ID.
	 * 
	 * @param remoteInstructionId
	 *        the remote instruction ID
	 * @param instructorId
	 *        the instructor ID
	 * @return the Instruction, or <em>null</em> if not found
	 */
	Instruction getInstruction(String remoteInstructionId, String instructorId);

	/**
	 * Update an instruction status.
	 * 
	 * @param instruction
	 *        ID the ID of the instruction to update the status for
	 * @param status
	 *        the status
	 */
	void storeInstructionStatus(Long instructionId, InstructionStatus status);

	/**
	 * Find all instructions in a given state.
	 * 
	 * @param state
	 *        the instruction state
	 * @return the found instructions, or empty list if none available
	 */
	List<Instruction> findInstructionsForState(InstructionState state);

	/**
	 * Find all instructions needing acknowledgement.
	 * 
	 * @return the found instructions, or empty list if none available
	 */
	List<Instruction> findInstructionsForAcknowledgement();

	/**
	 * Delete Instruction entities that are not in the Received or Executing
	 * state and are older than a specified number of hours.
	 * 
	 * <p>
	 * This is designed to free up space from local database storage for devices
	 * with limited storage capacity.
	 * </p>
	 * 
	 * @param hours
	 *        the minimum number of hours old the data must be to delete
	 * @return the number of Instruction entities deleted
	 */
	int deleteHandledInstructionsOlderThan(int hours);

}
