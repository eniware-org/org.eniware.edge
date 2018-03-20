/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.io.modbus;

import net.wimpi.modbus.util.SerialParameters;

/**
 * A JavaBean wrapper around SerialParameters to make working with dependency
 * inject easier.
 * 
 * <p>
 * The {@link SerialParameters} class defines many JavaBean-esque accessor
 * methods, but violates the JavaBeans contract in some cases where different
 * method types are used on the same bean property. For example, the
 * <i>parity</i> property has {#link {@link SerialParameters#getParity()} that
 * returns an <i>int</i> and {@link SerialParameters#setParity(String)} as well
 * as {@link SerialParameters#setParity(int)}. This breaks JavaBean access in
 * some JVMs.
 * </p>
 * 
 * @author matt
 * @version 1.0
 */
public class SerialParametersBean extends SerialParameters {

	/**
	 * Set the parity as a String value.
	 * 
	 * @param parity
	 */
	public void setParityString(String parity) {
		setParity(parity);
	}

}
