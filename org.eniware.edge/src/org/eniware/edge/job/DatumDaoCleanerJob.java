/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.job;

import org.eniware.edge.UploadService;
import org.eniware.edge.dao.DatumDao;
import org.eniware.edge.domain.Datum;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.PersistJobDataAfterExecution;

/**
 * Job to query a {@link DatumDao} for data to upload via an
 * {@link UploadService}.
 * 
 * <p>
 * This job will call {@link DatumDao#deleteUploadedDataOlderThan(int)} and emit
 * a log line if this returns a positive value.
 * </p>
 * 
 * <p>
 * The configurable properties of this class are:
 * </p>
 * 
 * <dl class="class-properties">
 * <dt>datumDao</dt>
 * <dd>The {@link DatumDao} to use to query for {@link Datum} to upload.</dd>
 * 
 * <dt>hours</dt>
 * <dd>The minimum age of data that has been uploaded to delete. Defaults to
 * {@link #DEFAULT_HOURS}</dd>
 * </dl>
 *
 * @param <T>
 *        the Datum type for this job
 * @version 2.0
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class DatumDaoCleanerJob<T extends Datum> extends AbstractJob {

	/** The default value for the {@code hours} property. */
	public static final int DEFAULT_HOURS = 4;

	private int hours = DEFAULT_HOURS;
	private DatumDao<T> datumDao = null;

	@Override
	protected void executeInternal(JobExecutionContext jobContext) throws Exception {
		if ( log.isDebugEnabled() ) {
			log.debug("Deleting [" + datumDao.getDatumType().getSimpleName() + "] data older than ["
					+ hours + "] hours");
		}
		int result = datumDao.deleteUploadedDataOlderThan(hours);
		if ( log.isInfoEnabled() && result > 0 ) {
			log.info("Deleted " + result + " [" + datumDao.getDatumType().getSimpleName()
					+ "] entities older than " + hours + " hours");
		}
	}

	/**
	 * @return the hours
	 */
	public int getHours() {
		return hours;
	}

	/**
	 * @param hours
	 *        the hours to set
	 */
	public void setHours(int hours) {
		this.hours = hours;
	}

	/**
	 * @return the datumDao
	 */
	public DatumDao<T> getDatumDao() {
		return datumDao;
	}

	/**
	 * @param datumDao
	 *        the datumDao to set
	 */
	public void setDatumDao(DatumDao<T> datumDao) {
		this.datumDao = datumDao;
	}

}
