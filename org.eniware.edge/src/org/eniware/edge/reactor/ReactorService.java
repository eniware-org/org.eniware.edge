/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.reactor;

import java.util.List;
import java.util.Map;

/**
 * API for reacting to EniwareNet service instruction requests.
 * 
 * @version $Revision$
 */
public interface ReactorService {

	/**
	 * Process an instruction.
	 * 
	 * @param instruction the instruction to process
	 * @return the status for the instruction
	 */
	InstructionStatus processInstruction(Instruction instruction);
	
	/**
	 * Attempt to parse and process an Instruction.
	 * 
	 * @param instructorId the ID of the instructor
	 * @param data the InputStream to parse for Instruction instances
	 * @param dataType the data type
	 * @param properties optional parsing properties and metadata
	 * @return the status for any parsed Instruction instances, or an empty list if none parsed
	 */
	List<InstructionStatus> processInstruction(String instructorId, Object data, 
			String dataType, Map<String, ?> properties);
}
