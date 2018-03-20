/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.settings;

/**
 * Extension of {@link SettingSpecifierProvider} that adds info about the
 * factory instance a provider is being managed under.
 * 
 * @author matt
 * @version $Revision$
 */
public interface FactorySettingSpecifierProvider extends SettingSpecifierProvider {

	/**
	 * Get the factory-wide unique ID for this provider.
	 * 
	 * @return the factory-wide unique ID
	 */
	String getFactoryInstanceUID();

}
