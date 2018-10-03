/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.job;

import org.eniware.edge.settings.SettingSpecifierProvider;
import org.quartz.JobDetail;
import org.quartz.Trigger;

/**
 * A bean that combines a trigger and a job, designed to be managed via
 * settings.
 *
 * @version 1.1
 */
public interface ManagedTriggerAndJobDetail extends SettingSpecifierProvider, ServiceProvider {

	/**
	 * Get the Trigger.
	 * 
	 * @return the trigger
	 */
	Trigger getTrigger();

	/**
	 * Get the JobDetail.
	 * 
	 * @return the jobDetail
	 */
	JobDetail getJobDetail();

}
