/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.job;

import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.context.MessageSource;

/**
 * A bean that combines a trigger and a job.
 * 
 * <p>The primary motivation for this class is to support the
 * {@link org.eniware.edge.runtime.JobServiceRegistrationListener}
 * for registering/un-registering jobs published as services in
 * bundles within a single "core" {@code Scheduler}.</p>
 *
 * @version $Revision$ $Date$
 */
public interface TriggerAndJobDetail {

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
	
	/**
	 * Get a MessageSource to localize the setting text.
	 * 
	 * <p>
	 * This method can return <em>null</em> if the provider does not have any
	 * localized resources.
	 * </p>
	 * 
	 * @return the MessageSource, or <em>null</em>
	 */
	MessageSource getMessageSource();
}
