/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.job;

import java.util.ArrayList;
import java.util.List;

import org.eniware.edge.settings.MappableSpecifier;
import org.eniware.edge.settings.SettingSpecifier;
import org.eniware.edge.settings.SettingSpecifierProvider;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.context.MessageSource;

/**
 * Simple stateful job to execute {@link JobService#executeJobService()}.
 * 
 * @version 2.0
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class SimpleServiceJob extends AbstractJob implements SettingSpecifierProvider {

	private JobService service;

	@Override
	protected void executeInternal(JobExecutionContext jobContext) throws Exception {
		service.executeJobService();
	}

	@Override
	public String getSettingUID() {
		return service.getSettingUID();
	}

	@Override
	public String getDisplayName() {
		return service.getDisplayName();
	}

	@Override
	public MessageSource getMessageSource() {
		return service.getMessageSource();
	}

	@Override
	public List<SettingSpecifier> getSettingSpecifiers() {
		List<SettingSpecifier> result = new ArrayList<SettingSpecifier>();
		for ( SettingSpecifier spec : service.getSettingSpecifiers() ) {
			if ( spec instanceof MappableSpecifier ) {
				MappableSpecifier keyedSpec = (MappableSpecifier) spec;
				result.add(keyedSpec.mappedTo("service."));
			} else {
				result.add(spec);
			}
		}
		return result;
	}

	public JobService getService() {
		return service;
	}

	public void setService(JobService service) {
		this.service = service;
	}

}
