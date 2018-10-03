/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.settings;

/**
 * A logically boolean toggle setting.
 * 
 * @version $Revision$
 */
public interface ToggleSettingSpecifier extends KeyedSettingSpecifier<Object> {

	/**
	 * Get the "true" value for this setting.
	 * 
	 * <p>If this returns <em>null</em> then {@link Boolean#TRUE} is assumed.</p>
	 * 
	 * @return the "true" value
	 */
	Object getTrueValue();
	
	/**
	 * Get the "false" value for this setting
	 * 
	 * <p>If this returns <em>null</em> then {@link Boolean#FALSE} is assumed.</p>
	 * 
	 * @return the "false" value
	 */
	Object getFalseValue();
	
}
