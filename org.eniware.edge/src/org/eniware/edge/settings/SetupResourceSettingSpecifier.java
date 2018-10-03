/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.settings;

import java.util.Map;

import org.eniware.edge.setup.SetupResource;
import org.eniware.edge.setup.SetupResourceProvider;

/**
 * Setting that renders a custom UI via {@link SetupResource} instances.
 * 
 * @version 1.0
 */
public interface SetupResourceSettingSpecifier extends SettingSpecifier {

	/**
	 * Get the provider of setup resources for this specifier.
	 * 
	 * @return The resource provider.
	 */
	SetupResourceProvider getSetupResourceProvider();

	/**
	 * Get a set of properties to associate with the resources managed by this
	 * setting.
	 * 
	 * @return A set of properties.
	 */
	Map<String, ?> getSetupResourceProperties();

}
