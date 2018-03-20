/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.ocpp.dao;

import java.util.Calendar;

import org.eniware.edge.ocpp.AuthorizationDao;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.PersistJobDataAfterExecution;
import net.solarnetwork.node.job.AbstractJob;

/**
 * Job to purge expired authorizations by calling
 * {@link AuthorizationDao#deleteExpiredAuthorizations(java.util.Date)}. The
 * maximum expired date to delete is derived from
 * {@link #getMinPurgeExpiredAuthorizationDays()}.
 * 
 * @author matt
 * @version 2.0
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class PurgeExpiredAuthorizationsJob extends AbstractJob {

	private AuthorizationDao authorizationDao;
	private int minPurgeExpiredAuthorizationDays = 1;

	@Override
	protected void executeInternal(JobExecutionContext jobContext) throws Exception {
		if ( authorizationDao == null ) {
			log.debug("No AuthorizationDao avaiable, cannot purge exipred authorizations");
			return;
		}
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -minPurgeExpiredAuthorizationDays);
		int result = authorizationDao.deleteExpiredAuthorizations(c.getTime());
		log.info("Purged {} expired OCPP authorizations {} days old", result,
				minPurgeExpiredAuthorizationDays);
	}

	/**
	 * Set the {@link AuthorizationDao} to use.
	 * 
	 * @param authorizationDao
	 *        The DAO to use.
	 */
	public void setAuthorizationDao(AuthorizationDao authorizationDao) {
		this.authorizationDao = authorizationDao;
	}

	/**
	 * Set the minimum number of days past expiration an authorization must be
	 * before qualifying for purging.
	 * 
	 * @param minPurgeExpiredAuthorizationDays
	 *        The number of days.
	 */
	public void setMinPurgeExpiredAuthorizationDays(int minPurgeExpiredAuthorizationDays) {
		this.minPurgeExpiredAuthorizationDays = minPurgeExpiredAuthorizationDays;
	}

}
