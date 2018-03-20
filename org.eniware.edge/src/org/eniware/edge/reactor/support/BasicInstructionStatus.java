/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.reactor.support;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import org.eniware.edge.reactor.InstructionStatus;

/**
 * Basic implementation of {@link InstructionStatus}.
 * 
 * @author matt
 * @version 1.2
 */
public class BasicInstructionStatus implements InstructionStatus, Serializable {

	private static final long serialVersionUID = 4584466858414350595L;

	private final Long instructionId;
	private final InstructionState instructionState;
	private final InstructionState acknowledgedInstructionState;
	private final Date statusDate;
	private final Map<String, ?> resultParameters;

	/**
	 * Constructor.
	 * 
	 * @param instructionId
	 *        the instruction ID
	 * @param instructionState
	 *        the instruction state
	 * @param statusDate
	 *        the status date
	 */
	public BasicInstructionStatus(Long instructionId, InstructionState instructionState,
			Date statusDate) {
		this(instructionId, instructionState, statusDate, null, null);
	}

	/**
	 * Constructor.
	 * 
	 * @param instructionId
	 *        the instruction ID
	 * @param instructionState
	 *        the instruction state
	 * @param statusDate
	 *        the status date
	 * @param ackInstructionState
	 *        the acknowledged state
	 */
	public BasicInstructionStatus(Long instructionId, InstructionState instructionState, Date statusDate,
			InstructionState ackInstructionState) {
		this(instructionId, instructionState, statusDate, ackInstructionState, null);
	}

	/**
	 * Constructor.
	 * 
	 * @param instructionId
	 *        the instruction ID
	 * @param instructionState
	 *        the instruction state
	 * @param statusDate
	 *        the status date
	 * @param ackInstructionState
	 *        the acknowledged state
	 * @param resultParameters
	 *        the result parameters
	 * @since 1.2
	 */
	public BasicInstructionStatus(Long instructionId, InstructionState instructionState, Date statusDate,
			InstructionState ackInstructionState, Map<String, ?> resultParameters) {
		this.instructionId = instructionId;
		this.instructionState = instructionState;
		this.statusDate = (statusDate == null ? new Date() : statusDate);
		this.acknowledgedInstructionState = ackInstructionState;
		this.resultParameters = resultParameters;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BasicInstructionStatus{");
		if ( instructionId != null ) {
			builder.append("instructionId=");
			builder.append(instructionId);
			builder.append(", ");
		}
		if ( instructionState != null ) {
			builder.append("instructionState=");
			builder.append(instructionState);
			builder.append(", ");
		}
		if ( acknowledgedInstructionState != null ) {
			builder.append("acknowledgedInstructionState=");
			builder.append(acknowledgedInstructionState);
			builder.append(", ");
		}
		if ( statusDate != null ) {
			builder.append("statusDate=");
			builder.append(statusDate);
		}
		builder.append("}");
		return builder.toString();
	}

	@Override
	public InstructionStatus newCopyWithState(InstructionState newState) {
		return new BasicInstructionStatus(this.instructionId, newState, this.statusDate,
				this.acknowledgedInstructionState, this.resultParameters);
	}

	@Override
	public InstructionStatus newCopyWithAcknowledgedState(InstructionState newState) {
		return new BasicInstructionStatus(this.instructionId, this.instructionState, this.statusDate,
				newState, this.resultParameters);
	}

	@Override
	public InstructionStatus newCopyWithState(InstructionState newState,
			Map<String, ?> resultParameters) {
		return new BasicInstructionStatus(this.instructionId, newState, this.statusDate,
				this.acknowledgedInstructionState, resultParameters);
	}

	@Override
	public InstructionStatus newCopyWithAcknowledgedState(InstructionState newState,
			Map<String, ?> resultParameters) {
		return new BasicInstructionStatus(this.instructionId, this.instructionState, this.statusDate,
				newState, resultParameters);
	}

	@Override
	public Long getInstructionId() {
		return instructionId;
	}

	@Override
	public InstructionState getInstructionState() {
		return instructionState;
	}

	@Override
	public Date getStatusDate() {
		return statusDate;
	}

	@Override
	public InstructionState getAcknowledgedInstructionState() {
		return acknowledgedInstructionState;
	}

	@Override
	public Map<String, ?> getResultParameters() {
		return resultParameters;
	}

}
