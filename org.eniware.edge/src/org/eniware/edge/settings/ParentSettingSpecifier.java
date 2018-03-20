/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.settings;

import java.util.List;

/**
 * A placeholder setting for a list of "child" settings.
 * 
 * @author matt
 * @version $Revision$
 */
public interface ParentSettingSpecifier extends SettingSpecifier {

	/**
	 * Get the settings in this parent.
	 * 
	 * @return the list of child settings
	 */
	List<SettingSpecifier> getChildSettings();
	
}
