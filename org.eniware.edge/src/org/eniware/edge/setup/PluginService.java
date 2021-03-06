/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.setup;

import java.util.Collection;
import java.util.List;
import java.util.Locale;

/**
 * Service for managing dynamic "plugins" within the application.
 * 
 * @version 1.0
 */
public interface PluginService {

	/**
	 * Ask the PluginService to refresh its list of available plugins. This is
	 * designed for implementations where the available plugins might change
	 * over time.
	 */
	void refreshAvailablePlugins();

	/**
	 * Get a list of all available plugins.
	 * 
	 * @param query
	 *        an optional query to apply to limit the returned results by. Pass
	 *        <em>null</em> to request all available Plugin instances
	 * @param locale
	 *        an optional locale to apply to PluginInfo
	 * @return list of available plugins, or an empty list if none available
	 */
	List<Plugin> availablePlugins(PluginQuery query, Locale locale);

	/**
	 * Get a list of all installed plugins.
	 * 
	 * @param locale
	 *        an optional locale to apply to PluginInfo
	 * @return list of installed plugins, or an empty list if none installed
	 */
	List<Plugin> installedPlugins(Locale locale);

	/**
	 * Remove one or more plugins based on their UIDs. This method might return
	 * immediately to allow the provisioning operation to complete
	 * asynchronously.
	 * 
	 * @param uids
	 *        the collection of plugins to remove
	 * @param locale
	 *        an optional locale to apply to the returned PluginProvisionStatus
	 * @return a status object
	 */
	PluginProvisionStatus removePlugins(Collection<String> uids, Locale locale);

	/**
	 * Get a "preview" status for installing a set of plugins based on their
	 * UIDs. This will return a status object synchronously that will contain
	 * details such as the list of plugins that will be installed and how many
	 * bytes must be downloaded. Note that the returned
	 * {@link PluginProvisionStatus#getProvisionID()} will not be valid for
	 * passing to {@link #statusForProvisioningOperation(String, Locale)}.
	 * 
	 * @param uids
	 *        the collection of plugins to remove
	 * @param locale
	 *        an optional locale to apply to the returned PluginProvisionStatus
	 * @return a status object
	 */
	PluginProvisionStatus previewInstallPlugins(Collection<String> uids, Locale locale);

	/**
	 * Install one or more plugins based on their UIDs. This method might return
	 * immediately to allow the provisioning operation to complete
	 * asynchronously.
	 * 
	 * @param uids
	 *        the collection of plugins to remove
	 * @param locale
	 *        an optional locale to apply to the returned PluginProvisionStatus
	 * @return a status object
	 */
	PluginProvisionStatus installPlugins(Collection<String> uids, Locale locale);

	/**
	 * Get a provisioning status based on a provisioning operation ID. The ID is
	 * one that would have been returned via a previous call to other methods
	 * like {@link #installedPlugins(Locale)}. The service will only maintain
	 * status information for a limited amount of time, and thus might return
	 * <em>null</em> even for an ID previously returned.
	 * 
	 * @param provisionID
	 *        the provisioning operation ID to find the status for
	 * @param locale
	 *        an optional locale to apply to the returned PluginProvisionStatus
	 * @return the status, or <em>null</em> if the status is not available
	 */
	PluginProvisionStatus statusForProvisioningOperation(String provisionID, Locale locale);

}
