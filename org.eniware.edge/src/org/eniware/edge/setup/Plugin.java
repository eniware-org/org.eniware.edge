/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.setup;

import java.util.Locale;

/**
 * API for a system "plugin" that can be manipulated by the application at
 * runtime.
 * 
 * @author matt
 * @version 1.0
 */
public interface Plugin {

	/**
	 * Get a unique identifier for this service. This should be meaningful to
	 * the service implementation.
	 * 
	 * @return unique identifier (should never be <em>null</em>)
	 */
	String getUID();

	/**
	 * Get the plugin version.
	 * 
	 * @return the version
	 */
	PluginVersion getVersion();

	/**
	 * Get the plugin information.
	 * 
	 * @return the info
	 */
	PluginInfo getInfo();

	/**
	 * Get the plugin information as a localized resource.
	 * 
	 * @param locale
	 *        the locale
	 * @return the info
	 */
	PluginInfo getLocalizedInfo(Locale locale);

	/**
	 * Return "core feature" flag. Core features are those plugins that are
	 * central to SolarNode functionality, and should not be removed by users.
	 * They can be upgraded, however.
	 * 
	 * @return core feature flag
	 */
	boolean isCoreFeature();

}
