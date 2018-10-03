/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.settings;

/**
 * A floating point range setting between a minimum and maximum value.
 * 
 * @version $Revision$
 */
public interface SliderSettingSpecifier extends KeyedSettingSpecifier<Double> {

	/**
	 * The minimum value allowed.
	 * 
	 * <p>If <em>null</em> then <code>0.0</code> is assumed.</p>
	 * 
	 * @return the minimum value
	 */
	Double getMinimumValue();
	
	/**
	 * The maximum value allowed.
	 * 
	 * <p>If <em>null</em> then <code>1.0</code> is assumed.</p>
	 * 
	 * @return the maximum value
	 */
	Double getMaximumValue();
	
	/**
	 * Get a step value for acceptable values between the minimum and maximum.
	 * 
	 * <p>
	 * If <em>null</em> then <code>1.0</code> is assumed.
	 * </p>
	 * 
	 * @return the step value
	 */
	Double getStep();

}
