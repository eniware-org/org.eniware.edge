/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.settings;

/**
 * Base API for a setting specifier.
 * 
 * <p>
 * This API is generally not used by application code. Rather, the various
 * interfaces that extend this interface are used.
 * </p>
 * 
 * @version $Revision$
 */
public interface SettingSpecifier {

	/**
	 * Localizable text to display with the setting's content.
	 * 
	 * @return the title
	 */
	String getTitle();
	
}
