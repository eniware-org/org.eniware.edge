/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobDetail;
import org.quartz.PersistJobDataAfterExecution;
import org.quartz.Trigger;
import org.springframework.context.MessageSource;

/**
 * Simple implementation of {@link TriggerAndJobDetail}.
 * 
 * @author matt
 * @version 1.1
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class SimpleTriggerAndJobDetail implements TriggerAndJobDetail {

	private Trigger trigger;
	private JobDetail jobDetail;
	private MessageSource messageSource;

	@Override
	public String toString() {
		return "TriggerAndJobDetail{trigger=" + trigger.getKey().getName() + ",job="
				+ jobDetail.getKey().getName() + '}';
	}

	@Override
	public Trigger getTrigger() {
		return trigger;
	}

	public void setTrigger(Trigger trigger) {
		this.trigger = trigger;
	}

	@Override
	public JobDetail getJobDetail() {
		return jobDetail;
	}

	public void setJobDetail(JobDetail jobDetail) {
		this.jobDetail = jobDetail;
	}

	@Override
	public MessageSource getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

}
