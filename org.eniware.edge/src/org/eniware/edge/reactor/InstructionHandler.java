/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.reactor;

import org.eniware.edge.reactor.InstructionStatus.InstructionState;

/**
 * API to be implemented by a service that can handle instructions.
 * 
 * @version 1.2
 */
public interface InstructionHandler {

	/**
	 * The instruction topic for setting control parameters. By convention the
	 * instruction should have a parameter whose key is the ID of the control to
	 * change and whose value is some control-specific data.
	 */
	String TOPIC_SET_CONTROL_PARAMETER = "SetControlParameter";

	/**
	 * The instruction topic for balancing power generation to power demand. By
	 * convention the instruction should have a parameter whose key is the ID of
	 * the control that should respond to the balancing request and whose value
	 * is an integer percentage (0 - 100) of the maximum desired power
	 * generation capacity.
	 * 
	 * @since 1.1
	 */
	String TOPIC_DEMAND_BALANCE = "DemandBalanceGeneration";

	/**
	 * The instruction topic for a request to reduce power demand. By convention
	 * the instruction should have a parameter whose key is the ID of the
	 * control that should respond to the shed request and whose value is an
	 * integer representing the amount of power, in watts, requested to be shed.
	 * If the requested power is <code>0</code> then any restriction on power
	 * should be removed, so that no limit is placed.
	 * 
	 * @since 1.2
	 */
	String TOPIC_SHED_LOAD = "ShedLoad";

	/**
	 * Test if a topic is handled by this handler.
	 * 
	 * @param topic
	 *        the topic
	 * @return <em>true</em> only if this handler can execute the job for the
	 *         given topic
	 */
	boolean handlesTopic(String topic);

	/**
	 * Process an instruction.
	 * 
	 * @param instruction
	 *        the instruction to process
	 * @return the state for the instruction, or <em>null</em> if the
	 *         instruction was not handled
	 */
	InstructionState processInstruction(Instruction instruction);

}
