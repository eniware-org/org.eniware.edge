/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.setup;

import java.util.List;

/**
 * API for status information and feedback during plugin provisioning
 * operations, that is, installing or removing plugins.
 * 
 * @version 1.1
 */
public interface PluginProvisionStatus {

	/**
	 * Get a unique provisioning operation ID for this status, so clients of the
	 * {@link PluginService} API can track progress.
	 * 
	 * @return a unique provisioning operation ID
	 */
	String getProvisionID();

	/**
	 * Get a status message on the provision progress. This might include
	 * information on which plugin was being downloaded, installed, removed,
	 * etc.
	 * 
	 * @return a status message
	 */
	String getStatusMessage();

	/**
	 * Get an overall progress amount, as a percentage between 0 and 1.
	 * 
	 * @return percent complete
	 */
	float getOverallProgress();

	/**
	 * Get an overall number of bytes that must be downloaded to complete the
	 * provisioning operation.
	 * 
	 * @return number of bytes, or <em>null</em> if not known
	 */
	Long getOverallDownloadSize();

	/**
	 * Get the number of bytes that have been downloaded so far while executing
	 * this provisioning operation.
	 * 
	 * @return number of bytes, or <em>null</em> if not known
	 */
	Long getOverallDownloadedSize();

	/**
	 * Get a list of plugins to be installed as part of this provisioning
	 * operation.
	 * 
	 * @return a list of plugins to intall, or an empty list if none
	 */
	List<Plugin> getPluginsToInstall();

	/**
	 * Get a list of plugins to be removed as part of this provisioning
	 * operation.
	 * 
	 * @return a list of plugins to remove, or an empty list if none
	 */
	List<Plugin> getPluginsToRemove();

	/**
	 * Flag indicating a restart will be required after the provision task
	 * completes.
	 * 
	 * @return {@literal true} if a restart is required after the provision task
	 *         completes
	 * @since 1.1
	 */
	boolean isRestartRequired();

}
