/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.job;

import org.eniware.edge.settings.SettingSpecifierProvider;

/**
 * API for a simple job service that extends {@link SettingSpecifierProvider}.
 * 
 * @version 1.0
 */
public interface JobService extends SettingSpecifierProvider {

	/**
	 * Execute the service job.
	 */
	void executeJobService() throws Exception;

}
