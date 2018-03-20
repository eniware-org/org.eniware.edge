/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.setup.web;


/**
 * Bean for a SetControlParameter instruction.
 * 
 * @author matt
 * @version 1.0
 */
public class SetControlParameterInstruction {

	private String controlId;
	private String parameterValue;

	public String getControlId() {
		return controlId;
	}

	public void setControlId(String controlId) {
		this.controlId = controlId;
	}

	public String getParameterValue() {
		return parameterValue;
	}

	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}

}
