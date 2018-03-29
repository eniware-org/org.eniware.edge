/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.settings;

import java.util.Map;

/**
 * A read-only string setting.
 * 
 * @version $Revision$
 */
public interface TitleSettingSpecifier extends KeyedSettingSpecifier<String> {

	/**
	 * An optional mapping of possible values for this setting to associated titles.
	 * 
	 * <p>This can be used to display user-friendly titles for setting
	 * values if the setting value itself is cryptic.</p>
	 * 
	 * @return
	 */
	Map<String, String> getValueTitles();
	
}
