/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.job;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eniware.edge.settings.MappableSpecifier;
import org.eniware.edge.settings.SettingSpecifier;
import org.eniware.edge.settings.SettingSpecifierProvider;
import org.eniware.edge.settings.support.BasicCronExpressionSettingSpecifier;
import org.eniware.edge.settings.support.KeyedSmartQuotedTemplateMapper;
import org.eniware.edge.util.PrefixedMessageSource;
import org.eniware.edge.util.TemplatedMessageSource;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;

/**
 * Extension of {@link SimpleTriggerAndJobDetail} that supports a
 * {@link SettingSpecifierProvider} to manage the job at runtime.
 * 
 * <p>
 * There are two basic ways to configure this class. The first way involves
 * configuring the {@code settingSpecifierProvider} property manually. In this
 * case the keyed settings and message keys will be dynamically adjusted to work
 * with a {@code jobDetail.jobDataMap['%s']} style pattern, where the {@code %s}
 * will be replaced by the top-level property names of all keys.
 * </p>
 * 
 * <p>
 * The second way involves leaving the {@code settingSpecifierProvider} not
 * configured, and letting this class find the first available
 * {@link SettingSpecifierProvider} instance in the configured
 * {@link JobDetail#getJobDataMap()}. In this case the keyed settings provided
 * by that {@link SettingSpecifierProvider} and all message keys will be
 * prefixed with {@code jobDetail.jobDataMap['$mapKey'].} where {@code $mapKey}
 * is the associated key of that object in the JobDataMap.
 * </p>
 * 
 * <p>
 * In most situations the later approach is simplest to set up.
 * </p>
 * 
 * <p>
 * The configurable properties of this class are:
 * </p>
 * 
 * <dl>
 * <dt>settingSpecifierProvider</dt>
 * <dd>The {@link SettingSpecifierProvider} that this class proxies all methods
 * for. If not configured, then {@link JobDetail#getJobDataMap()} will be
 * examined and the first value that implements {@link SettingSpecifierProvider}
 * will be used.</dd>
 * 
 * <dt>trigger</dt>
 * <dd>The job trigger.</dd>
 * 
 * <dt>jobDetail</dt>
 * <dd>The job detail.</dd>
 * 
 * <dt>serviceProviderConfigurations</dt>
 * <dd>An optional mapping of {@code jobDetail} keys to associated
 * {@link SimpleServiceProviderConfiguration} objects. The object on the given
 * key will be extracted from the {@code jobDetail} map and that object will be
 * returned as a {@link ServiceProvider.ServiceConfiguration} instance when
 * {@link #getServiceConfigurations()} is called. This allows services used by
 * the job to be exposed as services themselves in the runtime.</dd>
 * </dl>
 * 
 * @author matt
 * @version 2.0
 */
public class SimpleManagedTriggerAndJobDetail implements ManagedTriggerAndJobDetail, ServiceProvider {

	private static final Logger LOG = LoggerFactory.getLogger(SimpleManagedTriggerAndJobDetail.class);

	/**
	 * The regular expression used to delegate properties to the delegate
	 * {@code settingSpecifierProvider}.
	 */
	public static final String JOB_DETAIL_PROPERTY_MAPPING_REGEX = "jobDetail\\.jobDataMap\\['([a-zA-Z0-9_]*)'\\](.*)";

	private static final KeyedSmartQuotedTemplateMapper MAPPER = getMapper();

	private SettingSpecifierProvider settingSpecifierProvider;
	private Trigger trigger;
	private String triggerCronExpression;
	private JobDetail jobDetail;
	private MessageSource messageSource;
	private String simplePrefix;
	private Map<String, SimpleServiceProviderConfiguration> serviceProviderConfigurations;

	private static KeyedSmartQuotedTemplateMapper getMapper() {
		KeyedSmartQuotedTemplateMapper result = new KeyedSmartQuotedTemplateMapper();
		result.setTemplate("jobDetail.jobDataMap['%s']");
		return result;
	}

	@Override
	public String toString() {
		return "ManagedTriggerAndJobDetail{job=" + jobDetail.getKey().getName() + ",trigger="
				+ trigger.getKey().getName() + '}';
	}

	@Override
	public Trigger getTrigger() {
		Trigger result = trigger;
		if ( triggerCronExpression != null && result instanceof CronTrigger ) {
			try {
				result = ((CronTrigger) result).getTriggerBuilder().withSchedule(
						CronScheduleBuilder.cronScheduleNonvalidatedExpression(triggerCronExpression))
						.build();
			} catch ( ParseException e ) {
				LOG.warn("Error parsing cron expression [{}]: {}. Trigger unchanged as {}",
						triggerCronExpression, e.getMessage(),
						((CronTrigger) result).getCronExpression());
			}
		}
		return result;
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
	public String getSettingUID() {
		return getSettingSpecifierProvider().getSettingUID();
	}

	@Override
	public String getDisplayName() {
		return getSettingSpecifierProvider().getDisplayName();
	}

	/**
	 * Get the trigger cron expression, if available.
	 * 
	 * @return The cron expression, or <em>null</em> if not configured or the
	 *         configured {@code Trigger} is not a {@code CronTrigger}.
	 * @since 2.0
	 */
	public String getTriggerCronExpression() {
		if ( triggerCronExpression != null ) {
			return triggerCronExpression;
		}
		if ( trigger instanceof CronTrigger ) {
			return ((CronTrigger) trigger).getCronExpression();
		}
		return null;
	}

	/**
	 * Set a cron expression to use for the trigger.
	 * 
	 * @param cronExpression
	 *        The cron expression to use.
	 */
	public void setTriggerCronExpression(String cronExpression) {
		this.triggerCronExpression = cronExpression;
	}

	@Override
	public List<SettingSpecifier> getSettingSpecifiers() {
		List<SettingSpecifier> result = new ArrayList<SettingSpecifier>();
		if ( trigger instanceof CronTrigger ) {
			CronTrigger ct = (CronTrigger) trigger;
			result.add(new BasicCronExpressionSettingSpecifier("triggerCronExpression",
					ct.getCronExpression()));
		}
		for ( SettingSpecifier spec : getSettingSpecifierProvider().getSettingSpecifiers() ) {
			if ( spec instanceof MappableSpecifier ) {
				MappableSpecifier keyedSpec = (MappableSpecifier) spec;
				if ( simplePrefix != null ) {
					result.add(keyedSpec.mappedTo(simplePrefix));
				} else {
					result.add(keyedSpec.mappedWithMapper(MAPPER));
				}
			} else {
				result.add(spec);
			}
		}
		return result;
	}

	@Override
	public MessageSource getMessageSource() {
		if ( messageSource == null ) {
			TemplatedMessageSource tSource = new TemplatedMessageSource();
			tSource.setDelegate(getSettingSpecifierProvider().getMessageSource());
			tSource.setRegex(JOB_DETAIL_PROPERTY_MAPPING_REGEX);
			messageSource = tSource;
		}
		return messageSource;
	}

	public SettingSpecifierProvider getSettingSpecifierProvider() {
		if ( settingSpecifierProvider != null ) {
			return settingSpecifierProvider;
		}
		for ( Map.Entry<String, Object> me : jobDetail.getJobDataMap().entrySet() ) {
			Object o = me.getValue();
			if ( o instanceof SettingSpecifierProvider ) {
				SettingSpecifierProvider ssp = (SettingSpecifierProvider) o;
				PrefixedMessageSource pSource = new PrefixedMessageSource();
				String prefix = "jobDetail.jobDataMap['" + me.getKey() + "'].";
				pSource.setPrefix(prefix);
				pSource.setDelegate(ssp.getMessageSource());
				messageSource = pSource;
				settingSpecifierProvider = ssp;
				simplePrefix = prefix;
				break;
			}
		}
		return settingSpecifierProvider;
	}

	@Override
	public Collection<ServiceConfiguration> getServiceConfigurations() {
		Collection<ServiceConfiguration> result = null;
		if ( jobDetail != null && serviceProviderConfigurations != null
				&& serviceProviderConfigurations.size() > 0 ) {
			for ( Map.Entry<String, SimpleServiceProviderConfiguration> me : serviceProviderConfigurations
					.entrySet() ) {
				Object o = jobDetail.getJobDataMap().get(me.getKey());
				if ( o != null ) {
					SimpleServiceProviderConfiguration conf = me.getValue();
					SimpleServiceProviderConfiguration ser = new SimpleServiceProviderConfiguration();
					ser.setService(o);
					ser.setInterfaces(conf.getInterfaces());
					ser.setProperties(conf.getProperties());
					if ( result == null ) {
						result = new ArrayList<ServiceProvider.ServiceConfiguration>();
					}
					result.add(ser);
				}
			}
		}
		return result;
	}

	public void setSettingSpecifierProvider(SettingSpecifierProvider settingSpecifierProvider) {
		this.settingSpecifierProvider = settingSpecifierProvider;
	}

	public void setServiceProviderConfigurations(
			Map<String, SimpleServiceProviderConfiguration> serviceProviderConfigurations) {
		this.serviceProviderConfigurations = serviceProviderConfigurations;
	}

}
