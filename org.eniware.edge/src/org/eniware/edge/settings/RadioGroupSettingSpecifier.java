/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.settings;

/**
 * Similar to the {@link MultiValueSettingSpecifier} except that
 * these settings are displayed in-line.
 * 
 * <p>The {@link #getValueTitles()} is required for this setting, and 
 * provides the list of possible setting values for the user to choose from.</p>
 * 
 * @version $Revision$
 */
public interface RadioGroupSettingSpecifier extends TextFieldSettingSpecifier {

	/**
	 * Localizable text to display at the end of the group's content.
	 * 
	 * @return localizable 
	 */
	String getFooterText();
	
}
