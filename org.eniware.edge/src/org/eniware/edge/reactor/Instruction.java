/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.reactor;

import java.util.Date;

/**
 * API for a single, immutable instruction with associated parameters.
 * 
 * <p>
 * An instruction is like a single name (the {@code topic}) with an arbitrary
 * number of named key/value parameters. All parameter names and values are
 * Strings. Each parameter can have any number of associated values.
 * </p>
 * 
 * <p>
 * An Instruction is considered equal to another Instruction only if the
 * {@code instructionId} and {@code instructorId} values are equal.
 * </p>
 * 
 * @author matt
 * @version $Revision$
 */
public interface Instruction {

	/** An ID to use for locally-initiated instructions. */
	String LOCAL_INSTRUCTION_ID = "LOCAL";

	/**
	 * Get a locally-assigned unique ID.
	 * 
	 * @return local unique ID
	 */
	Long getId();

	/**
	 * Get the topic of the instruction -- a unique identifier for the
	 * instruction type.
	 * 
	 * @return the topic name
	 */
	String getTopic();

	/**
	 * Get the date/time the instruction was requested.
	 * 
	 * @return the date
	 */
	Date getInstructionDate();

	/**
	 * Get the SolarNet-assigned unique ID for this instruction.
	 * 
	 * @return unique ID
	 */
	String getRemoteInstructionId();

	/**
	 * Get the unique ID for the sender of the instruction, for example the DN
	 * of the sender's certificate.
	 * 
	 * @return the instructor ID
	 */
	String getInstructorId();

	/**
	 * Get an Iterator of all unique instruction parameter names.
	 * 
	 * @return iterator
	 */
	Iterable<String> getParameterNames();

	/**
	 * Test if a specific parameter has an associated value available.
	 * 
	 * @param parameterName
	 *        the parameter name to test for
	 * @return <em>true</em> if the parameter name has at least one value
	 *         available
	 */
	boolean isParameterAvailable(String parameterName);

	/**
	 * Get a single parameter value for a specific parameter name.
	 * 
	 * @param parameterName
	 *        the parameter name to get the value for
	 * @return the first available parameter name, or <em>null</em> if not
	 *         available
	 */
	String getParameterValue(String parameterName);

	/**
	 * Get all parameter values for a specific parameter name;
	 * 
	 * @param parameterName
	 *        the parameter name to get the values for
	 * @return all available parameter values, or <em>null</em> if not available
	 */
	String[] getAllParameterValues(String parameterName);

	/**
	 * Get the instruction status.
	 * 
	 * @return the status
	 */
	InstructionStatus getStatus();
}
