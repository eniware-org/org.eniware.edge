/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.job;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eniware.edge.BulkUploadResult;
import org.eniware.edge.BulkUploadService;
import org.eniware.edge.dao.DatumDao;
import org.eniware.edge.domain.Datum;
import org.eniware.edge.setup.SetupException;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.PersistJobDataAfterExecution;

/**
 * Job to query a collection of {@link DatumDao} instances for data to upload
 * via a {@link BulkUploadService}.
 * 
 * <p>
 * This job will call {@link DatumDao#getDatumNotUploaded(String)} for each
 * configured {@link DatumDao} and combine them into a single collection to pass
 * to {@link BulkUploadService#uploadBulkDatum(java.util.Collection)}. For each
 * non-null {@link BulkUploadResult#getId()} tracking ID returned, the
 * associated {@link BulkUploadResult#getDatum()} will be passed to the
 * appropriate {@link DatumDao} instance's
 * {@link DatumDao#storeDatumUpload(Datum, String, Long)} method.
 * </p>
 * 
 * <p>
 * The configurable properties of this class are:
 * </p>
 * 
 * <dl class="class-properties">
 * <dt>daos</dt>
 * <dd>A collection of {@link DatumDao} instances to collect Datum objects from
 * that need uploading, and to then store the uploaded tracking IDs with.</dd>
 * 
 * <dt>uploadService</dt>
 * <dd>The {@link BulkUploadService} to upload the data with.</dd>
 * </dl>
 * 
 * @version 2.0
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class DatumDaoBulkUploadJob extends AbstractJob {

	private Collection<DatumDao<Datum>> daos;
	private BulkUploadService uploadService;

	@Override
	protected void executeInternal(JobExecutionContext jobContext) throws Exception {
		Map<Class<? extends Datum>, DatumDao<Datum>> daoMapping = new LinkedHashMap<Class<? extends Datum>, DatumDao<Datum>>(
				daos.size());

		List<Datum> uploadList = new ArrayList<Datum>();

		for ( DatumDao<Datum> datumDao : daos ) {
			if ( log.isDebugEnabled() ) {
				log.debug("Collecting [{}] data to bulk upload to [{}]",
						datumDao.getDatumType().getSimpleName(), uploadService.getKey());
			}

			daoMapping.put(datumDao.getDatumType(), datumDao);

			List<Datum> toUpload = datumDao.getDatumNotUploaded(uploadService.getKey());

			if ( log.isDebugEnabled() ) {
				log.debug("Found " + toUpload.size() + " [" + datumDao.getDatumType().getSimpleName()
						+ "] data to bulk upload to [" + uploadService.getKey() + ']');
			}

			uploadList.addAll(toUpload);
		}
		if ( log.isInfoEnabled() ) {
			log.info("Collected {} datum to bulk upload to [{}]", uploadList.size(),
					uploadService.getKey());
		}
		final Date uploadDate = new Date();
		try {
			int count = 0;
			List<BulkUploadResult> results = uploadService.uploadBulkDatum(uploadList);
			if ( results != null ) {
				for ( BulkUploadResult result : results ) {
					String tid = result.getId();
					if ( log.isTraceEnabled() ) {
						log.trace("Bulk uploaded [{} {}] [{}] and received tid [{}]",
								new Object[] { result.getDatum().getClass().getSimpleName(),
										(result.getDatum().getCreated() == null ? null
												: result.getDatum().getCreated().getTime()),
										result.getDatum().getSourceId(), tid });
					}

					if ( tid != null ) {
						DatumDao<Datum> datumDao = daoMapping.get(result.getDatum().getClass());
						datumDao.setDatumUploaded(result.getDatum(), uploadDate, uploadService.getKey(),
								tid);
						count++;
					}
				}
			}
			if ( log.isInfoEnabled() ) {
				log.info("Bulk uploaded {} objects to [{}]", count, uploadService.getKey());
			}
		} catch ( RuntimeException e ) {
			Throwable root = e;
			while ( root.getCause() != null ) {
				root = root.getCause();
			}
			if ( root instanceof IOException ) {
				if ( log.isWarnEnabled() ) {
					log.warn("Network problem posting data ({}): {}", root.getClass().getSimpleName(),
							root.getMessage());
				}
			} else if ( root instanceof SetupException ) {
				log.warn("Unable to post data: {}", root.getMessage());
			} else {
				if ( log.isErrorEnabled() ) {
					log.error("Exception posting data", root);
				}
			}
		}
	}

	public void setDaos(Collection<DatumDao<Datum>> daos) {
		this.daos = daos;
	}

	public void setUploadService(BulkUploadService uploadService) {
		this.uploadService = uploadService;
	}

}
