/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.reactor.support;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eniware.edge.reactor.Instruction;
import org.eniware.edge.reactor.InstructionStatus;

/**
 * Basic implementation of {@link Instruction}.
 *
 * @version $Revision$
 */
public class BasicInstruction implements Instruction, Serializable {

	private static final long serialVersionUID = 5522509637377814131L;

	private final Long id;
	private final String topic;
	private final Date instructionDate;
	private final String remoteInstructionId;
	private final String instructorId;
	private final InstructionStatus status;
	private final Map<String, List<String>> parameters;

	public BasicInstruction(String topic, Date instructionDate,
			String remoteInstructionId, String instructorId, InstructionStatus status) {
		this(null, topic, instructionDate, remoteInstructionId, instructorId, status);
	}
	
	public BasicInstruction(Long id, String topic, Date instructionDate,
			String remoteInstructionId, String instructorId, InstructionStatus status) {
		this.id = id;
		this.topic = topic;
		this.instructionDate = instructionDate;
		this.remoteInstructionId = remoteInstructionId;
		this.instructorId = instructorId;
		this.status = status;
		this.parameters = new LinkedHashMap<String, List<String>>();
	}
	
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public String getTopic() {
		return topic;
	}

	@Override
	public Date getInstructionDate() {
		return instructionDate;
	}

	@Override
	public String getRemoteInstructionId() {
		return remoteInstructionId;
	}

	@Override
	public String getInstructorId() {
		return instructorId;
	}
	
	@Override
	public Iterable<String> getParameterNames() {
		return Collections.unmodifiableSet(parameters.keySet());
	}

	@Override
	public boolean isParameterAvailable(String parameterName) {
		List<String> values = parameters.get(parameterName);
		return values != null;
	}

	@Override
	public String getParameterValue(String parameterName) {
		List<String> values = parameters.get(parameterName);
		return values == null ? null : values.get(0);
	}

	@Override
	public String[] getAllParameterValues(String parameterName) {
		List<String> values = parameters.get(parameterName);
		if ( values != null ) {
			return values.toArray(new String[values.size()]);
		}
		return null;
	}

	@Override
	public InstructionStatus getStatus() {
		return status;
	}
	
	/**
	 * Add a new parameter value.
	 * 
	 * @param name the parameter name
	 * @param value the parameter value
	 */
	public void addParameter(String name, String value) {
		assert name != null && value != null;
		List<String> values = parameters.get(name);
		if ( values == null ) {
			values = new ArrayList<String>(3);
			parameters.put(name, values);
		}
		values.add(value);
	}

}
