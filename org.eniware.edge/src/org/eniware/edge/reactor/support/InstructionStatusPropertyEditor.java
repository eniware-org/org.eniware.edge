/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.reactor.support;

import java.beans.PropertyEditorSupport;
import java.util.Date;

import org.eniware.edge.reactor.InstructionStatus;

/**
 * PropertyEditor for {@link InstructionStatus} objects.
 * 
 * @version $Revision$
 */
public class InstructionStatusPropertyEditor extends PropertyEditorSupport
implements Cloneable {

	@Override
	public String getAsText() {
		Object val = getValue();
		if ( val == null ) {
			return null;
		}
		if ( val instanceof InstructionStatus  ) {
			return ((InstructionStatus)val).getInstructionState().toString();
		} 
		throw new IllegalArgumentException("Unsupported duration object [" 
				+val.getClass() +"]: " +val);
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		InstructionStatus.InstructionState state = 
				InstructionStatus.InstructionState.valueOf(text);
		setValue(new BasicInstructionStatus(null, state, new Date()));
	}

	@Override
	public Object clone() {
		try {
			return super.clone();
		} catch ( CloneNotSupportedException e ) {
			// should never get here
			throw new RuntimeException(e);
		}
	}

}
