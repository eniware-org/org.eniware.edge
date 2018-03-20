/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.setup;

import java.util.Locale;

/**
 * Descriptive information about a plugin, designed to help users of the plugin.
 * 
 * @author matt
 * @version 1.0
 */
public interface PluginInfo {

	/**
	 * Get a name of the plugin.
	 * 
	 * @return the name
	 */
	String getName();

	/**
	 * Get a description of the plugin.
	 * 
	 * @return the description
	 */
	String getDescription();

	/**
	 * Get a localized name of the plugin.
	 * 
	 * @param locale
	 *        the desired locale
	 * @return the name
	 */
	String getLocalizedName(Locale locale);

	/**
	 * Get a localized description of the plugin.
	 * 
	 * @param locale
	 *        the desired locale
	 * @return the description
	 */
	String getLocalizedDescription(Locale locale);

}
