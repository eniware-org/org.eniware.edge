/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.reactor;

import java.util.Collection;

/**
 * API for acknowledging instructions to EniwareNet.
 * 
 * @version $Revision$
 */
public interface InstructionAcknowledgementService {
	
	/**
	 * Acknowledge a collection of instructions.
	 * 
	 * @param instructions the instructions to acknowledge
	 */
	void acknowledgeInstructions(Collection<Instruction> instructions);
	
}
