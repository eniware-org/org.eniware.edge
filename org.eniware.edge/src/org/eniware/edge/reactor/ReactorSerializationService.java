/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.reactor;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * API for parsing instruction data encoded in some way back into an Instruction instance.
 * 
 * @version $Revision$
 */
public interface ReactorSerializationService {
	
	/**
	 * Parse some data object and return new Instruction instances.
	 * 
	 * @param instructorId the instructor ID
	 * @param data the data to parse
	 * @param type the type of data {@code in} is encoded as (text/xml, text/json, etc.)
	 * @param properties optional properties and metadata for decoding
	 * @return the parsed Instruction instances, or an empty list if unable to parse anything
	 * @throws IllegalArgumentException if the {@code in} object is not supported
	 */
	List<Instruction> decodeInstructions(String instructorId, Object in, String type, Map<String, ?> properties);
	
	/**
	 * Encode a collection of Instruction objects as a data object.
	 * 
	 * @param instructions the instructions to encode
	 * @param type the type of data desired (text/xml, text/json, etc.)
	 * @param properties optional properties and metadata for encoding
	 * @return the encoded instruction
	 * @throws IllegalArgumentException if the {@code in} object is not supported
	 */
	Object encodeInstructions(Collection<Instruction> instructions, String type, Map<String, ?> properties);
	
}
