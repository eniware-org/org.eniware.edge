/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */
package org.eniware.edge.job;

import java.text.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;

/**
 * Extension of {@link CronTriggerBean} that can randomize specific fields of
 * the cron expression to distribute a job across several Edges over time.
 * 
 * <p>
 * The cron expression is only randomized if the
 * {@link #setCronExpression(String)} method is used to set the cron expression.
 * </p>
 *
 * @version 2.0
 */
public class RandomizedCronTriggerFactoryBean extends CronTriggerFactoryBean {

	/**
	 * A trigger job data map key for the original cron expression before
	 * randomization was applied.
	 */
	public static final String BASE_CRON_EXPRESSION_KEY = "org.eniware.edge.job.BASE_CRON_EXPRESSION";

	private boolean randomSecond = true;
	private String baseCronExpression;
	private String finalCronExpression;

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public void setCronExpression(final String cronExpression) {
		this.baseCronExpression = cronExpression;
		this.finalCronExpression = cronExpression;
		if ( randomSecond ) {
			int seconds = (int) Math.floor(Math.random() * 60);
			String newExpression = cronExpression.replaceAll("^\\s*\\d+(\\s+)",
					String.valueOf(seconds) + "$1");
			if ( !newExpression.equals(cronExpression) ) {
				log.debug("Randomized seconds of cron expression set to {}", seconds);
				finalCronExpression = newExpression;
			}
		}
		super.setCronExpression(finalCronExpression);
	}

	@Override
	public void afterPropertiesSet() throws ParseException {
		if ( baseCronExpression != null && !baseCronExpression.equals(finalCronExpression) ) {
			// add our base expression to the job data map, so we know at runtime it was altered
			getJobDataMap().put(BASE_CRON_EXPRESSION_KEY, baseCronExpression);
		}
		super.afterPropertiesSet();
	}

	public boolean isRandomSecond() {
		return randomSecond;
	}

	/**
	 * Set a flag to randomize the second field of the configured cron
	 * expression.
	 * 
	 * If <em>true</em> then set the seconds value of the cron expression will
	 * be set to a random value between 0-59. Will only replace the second value
	 * if a simple second value is specified in the cron expression. For example
	 * if the second value is {@code 0} it will be randomized, while if it is
	 * {@code 0/5} it will not. Defaults to <em>true</em>.
	 * 
	 * @param randomSecond
	 *        The random second flag value to set.
	 */
	public void setRandomSecond(boolean randomSecond) {
		this.randomSecond = randomSecond;
	}

	/**
	 * Get the random second flag.
	 * 
	 * @return <em>true</em> if seconds should be randomized.
	 */
	public String getBaseCronExpression() {
		return baseCronExpression;
	}

}
