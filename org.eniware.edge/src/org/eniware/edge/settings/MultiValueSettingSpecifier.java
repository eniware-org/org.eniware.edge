/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.settings;

/**
 * A string setting selected from a list of possible values.
 * 
 * <p>The {@link #getValueTitles()} is required for this setting, and 
 * provides the list of possible setting values for the user to choose from.</p>
 * 
 * @author matt
 * @version $Revision$
 */
public interface MultiValueSettingSpecifier extends TextFieldSettingSpecifier {

}
