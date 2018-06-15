/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */
package org.eniware.edge.reactor.io.json;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import org.eniware.edge.reactor.Instruction;
import org.eniware.edge.reactor.ReactorSerializationService;
import org.eniware.edge.reactor.support.BasicInstruction;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JSON-based IO support for ReactorService.
 * 
 * @version 1.1
 */
public class JsonReactorSerializationService implements ReactorSerializationService {

	private ObjectMapper objectMapper = defaultObjectMapper();

	private static ObjectMapper defaultObjectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS'Z'");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		mapper.setDateFormat(sdf);
		return mapper;
	}

	@Override
	public List<Instruction> decodeInstructions(String instructorId, Object in, String type,
			Map<String, ?> properties) {
		if ( !"application/json".equalsIgnoreCase(type) ) {
			throw new IllegalArgumentException("The [" + type + "] is not supported.");
		}

		if ( in instanceof JsonNode ) {
			try {
				return decodeInstructions(instructorId, (JsonNode) in);
			} catch ( IOException e ) {
				throw new RuntimeException(e);
			}
		} else {
			throw new IllegalArgumentException("The data object [" + in + "] is not supported.");
		}
	}

	private List<Instruction> decodeInstructions(String instructorId, JsonNode root) throws IOException {
		List<Instruction> results = new ArrayList<Instruction>();
		if ( root.isArray() ) {
			for ( JsonNode child : root ) {
				Instruction instr = decodeInstruction(instructorId, child);
				if ( instr != null ) {
					results.add(instr);
				}
			}
		}
		return results;
	}

	private String getStringFieldValue(JsonNode node, String fieldName, String placeholder) {
		JsonNode child = node.get(fieldName);
		return (child == null ? placeholder : child.asText());
	}

	/**
	 * Decode a single instruction. Example JSON:
	 * 
	 * <pre>
	 * {
	 * 	"topic" : "Mock/Topic",
	 * 	"id" : "1",
	 * 	"instructionDate" : "2014-01-01 12:00:00.000Z",
	 * 	"parameters" : [
	 * 		{ "name" : "foo", "value" : "bar" }
	 * 	]
	 * }
	 * </pre>
	 * 
	 * @param instructorId
	 *        the instructor ID
	 * @param node
	 *        the JSON node
	 * @return the Instruction, or <em>null</em> if unable to parse
	 */
	private Instruction decodeInstruction(String instructorId, JsonNode node) {
		final String topic = getStringFieldValue(node, "topic", null);
		final String instructionId = getStringFieldValue(node, "id", null);
		final String instructionDate = getStringFieldValue(node, "instructionDate", null);
		final Date date = (instructionDate == null ? null : objectMapper.convertValue(instructionDate,
				Date.class));

		BasicInstruction result = new BasicInstruction(topic, date, instructionId, instructorId, null);

		JsonNode params = node.get("parameters");
		if ( params != null && params.isArray() ) {
			for ( JsonNode p : params ) {
				String paramName = getStringFieldValue(p, "name", null);
				String paramValue = getStringFieldValue(p, "value", null);
				if ( paramName != null ) {
					result.addParameter(paramName, paramValue);
				}
			}
		}

		return result;
	}

	@Override
	public Object encodeInstructions(Collection<Instruction> instructions, String type,
			Map<String, ?> properties) {
		throw new UnsupportedOperationException();
	}

	public ObjectMapper getObjectMapper() {
		return objectMapper;
	}

	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

}
