/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.job;

import org.eniware.edge.DatumDataSource;
import org.eniware.edge.UploadService;
import org.eniware.edge.domain.Datum;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.PersistJobDataAfterExecution;

/**
 * Job that obtains a {@link Datum} from a {@link DatumDataSource} and then
 * uploads it immediately via {@link UploadService}, without persisting the
 * Datum locally first.
 * 
 * <p>
 * This job can be used to collect data such as weather where the resolution of
 * the data is not very fine and persisting it locally would be overkill.
 * </p>
 * 
 * <p>
 * The configurable properties of this class are:
 * </p>
 * 
 * <dl class="class-properties">
 * <dt>datumDataSource</dt>
 * <dd>The {@link DatumDataSource} to collect the data from. The
 * {@link DatumDataSource#readCurrentDatum()} method will be called to get the
 * currently available data.</dd>
 * 
 * <dt>uploadService</dt>
 * <dd>The {@link UploadService} implementation to use to upload the datum
 * to.</dd>
 * </dl>
 * 
 * @param <T>
 *        
 * @version 2.0
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class DatumDataSourceUploadJob<T extends Datum> extends AbstractJob {

	private DatumDataSource<T> datumDataSource = null;
	private UploadService uploadService;

	@Override
	protected void executeInternal(JobExecutionContext jobContext) throws Exception {
		if ( log.isInfoEnabled() ) {
			log.info("Collecting [" + datumDataSource.getDatumType().getSimpleName() + "] now from ["
					+ datumDataSource + ']');
		}

		T datum = datumDataSource.readCurrentDatum();
		if ( datum == null ) {
			if ( log.isInfoEnabled() ) {
				log.info("No data returned from DatumDataSource.");
			}
			return;
		}

		if ( log.isInfoEnabled() ) {
			log.info("Uploading [" + datum + "] to [" + uploadService.getKey() + ']');
		}

		String tid = uploadService.uploadDatum(datum);
		if ( log.isTraceEnabled() ) {
			log.trace("Just uploaded [" + datumDataSource.getDatumType().getSimpleName()
					+ "] and received tid [" + tid + "]");
		}

	}

	/**
	 * @return the datumDataSource
	 */
	public DatumDataSource<T> getDatumDataSource() {
		return datumDataSource;
	}

	/**
	 * @param datumDataSource
	 *        the datumDataSource to set
	 */
	public void setDatumDataSource(DatumDataSource<T> datumDataSource) {
		this.datumDataSource = datumDataSource;
	}

	/**
	 * @return the uploadService
	 */
	public UploadService getUploadService() {
		return uploadService;
	}

	/**
	 * @param uploadService
	 *        the uploadService to set
	 */
	public void setUploadService(UploadService uploadService) {
		this.uploadService = uploadService;
	}

}
