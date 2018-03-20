/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.settings;

import java.util.List;

/**
 * A grouping of other settings.
 * 
 * @author matt
 * @version 1.1
 */
public interface GroupSettingSpecifier extends SettingSpecifier, MappableSpecifier {

	/**
	 * Get the key for this setting.
	 * 
	 * @return the key to associate with this setting
	 * @since 1.1
	 */
	String getKey();

	/**
	 * Localizable text to display at the end of the group's content.
	 * 
	 * @return localizable
	 */
	String getFooterText();

	/**
	 * Get the settings in this group.
	 * 
	 * @return the list of group settings
	 */
	List<SettingSpecifier> getGroupSettings();

	/**
	 * Get dynamic flag. A dynamic group is one that a user can manage any
	 * number of copies of the group settings, adding and removing as necessary.
	 * 
	 * @return The dynamic flag.
	 * @since 1.1
	 */
	boolean isDynamic();

}
