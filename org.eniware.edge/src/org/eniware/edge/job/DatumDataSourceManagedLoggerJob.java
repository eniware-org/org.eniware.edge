/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.job;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eniware.edge.DatumDataSource;
import org.eniware.edge.MultiDatumDataSource;
import org.eniware.edge.dao.DatumDao;
import org.eniware.edge.domain.Datum;
import org.eniware.edge.settings.KeyedSettingSpecifier;
import org.eniware.edge.settings.SettingSpecifier;
import org.eniware.edge.settings.SettingSpecifierProvider;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.context.MessageSource;
import org.springframework.dao.DuplicateKeyException;

import org.eniware.util.OptionalService;

/**
 * Extension of {@link DatumDataSourceLoggerJob} designed to be used as a
 * managed service.
 * 
 * <p>
 * This class implements {@link SettingSpecifierProvider} but delegates that API
 * to the configured {@link #getDatumDataSource()}.
 * </p>
 *
 * @version 2.0
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class DatumDataSourceManagedLoggerJob<T extends Datum> extends AbstractJob
		implements SettingSpecifierProvider {

	private DatumDataSource<T> datumDataSource = null;
	private MultiDatumDataSource<T> multiDatumDataSource = null;
	private OptionalService<DatumDao<T>> datumDao = null;

	@Override
	protected void executeInternal(JobExecutionContext jobContext) throws Exception {
		try {
			if ( multiDatumDataSource != null ) {
				executeForMultiDatumDataSource(jobContext);
			} else {
				executeForDatumDataSource(jobContext);
			}
		} catch ( Throwable e ) {
			logThrowable(e);
		}
	}

	private void executeForDatumDataSource(JobExecutionContext jobContext) {
		if ( log.isDebugEnabled() ) {
			log.debug("Collecting [{}] from [{}]", datumDataSource.getDatumType().getSimpleName(),
					datumDataSource);
		}
		T datum = datumDataSource.readCurrentDatum();
		if ( datum != null ) {
			persistDatum(Collections.singleton(datum));
		} else {
			log.info("No data returned from [{}]", datumDataSource);
		}
	}

	private void executeForMultiDatumDataSource(JobExecutionContext jobContext) {
		if ( log.isDebugEnabled() ) {
			log.debug("Collecting [{}] from [{}]",
					multiDatumDataSource.getMultiDatumType().getSimpleName(), multiDatumDataSource);
		}
		Collection<T> datum = multiDatumDataSource.readMultipleDatum();
		if ( datum != null && datum.size() > 0 ) {
			persistDatum(datum);
		} else {
			log.info("No data returned from [{}]", multiDatumDataSource);
		}
	}

	private void persistDatum(Collection<T> datumList) {
		if ( datumList == null || datumList.size() < 1 ) {
			return;
		}
		if ( log.isInfoEnabled() ) {
			log.info("Got Datum to persist: {}", (datumList.size() == 1
					? datumList.iterator().next().toString() : datumList.toString()));
		}
		DatumDao<T> dao = datumDao.service();
		if ( dao == null ) {
			log.info("No DatumDao available to persist {}, not saving", datumList);
			return;
		}
		for ( T datum : datumList ) {
			try {
				dao.storeDatum(datum);
				log.debug("Persisted Datum {}", datum);
			} catch ( DuplicateKeyException e ) {
				// we ignore duplicate key exceptions, as we sometimes collect the same 
				// datum multiple times for redundancy
				log.info("Duplicate datum {}; not persisting", datum);
			}
		}
	}

	private SettingSpecifierProvider getSettingSpecifierProvider() {
		if ( multiDatumDataSource instanceof SettingSpecifierProvider ) {
			return (SettingSpecifierProvider) multiDatumDataSource;
		}
		if ( datumDataSource instanceof SettingSpecifierProvider ) {
			return (SettingSpecifierProvider) datumDataSource;
		}
		return null;
	}

	@Override
	public String getSettingUID() {
		SettingSpecifierProvider delegate = getSettingSpecifierProvider();
		if ( delegate != null ) {
			return delegate.getSettingUID();
		}
		return getDatumDataSource().getClass().getName();
	}

	@Override
	public String getDisplayName() {
		SettingSpecifierProvider delegate = getSettingSpecifierProvider();
		if ( delegate != null ) {
			return delegate.getDisplayName();
		}
		return null;
	}

	@Override
	public MessageSource getMessageSource() {
		SettingSpecifierProvider delegate = getSettingSpecifierProvider();
		if ( delegate != null ) {
			return delegate.getMessageSource();
		}
		return null;
	}

	@Override
	public List<SettingSpecifier> getSettingSpecifiers() {
		SettingSpecifierProvider delegate = getSettingSpecifierProvider();
		if ( delegate == null ) {
			return Collections.emptyList();
		}
		List<SettingSpecifier> result = new ArrayList<SettingSpecifier>();
		final String prefix = (multiDatumDataSource != null ? "multiDatumDataSource."
				: "datumDataSource.");
		for ( SettingSpecifier spec : delegate.getSettingSpecifiers() ) {
			if ( spec instanceof KeyedSettingSpecifier<?> ) {
				KeyedSettingSpecifier<?> keyedSpec = (KeyedSettingSpecifier<?>) spec;
				result.add(keyedSpec.mappedTo(prefix));
			} else {
				result.add(spec);
			}
		}
		return result;
	}

	public DatumDataSource<T> getDatumDataSource() {
		return datumDataSource;
	}

	public void setDatumDataSource(DatumDataSource<T> datumDataSource) {
		this.datumDataSource = datumDataSource;
	}

	public MultiDatumDataSource<T> getMultiDatumDataSource() {
		return multiDatumDataSource;
	}

	public void setMultiDatumDataSource(MultiDatumDataSource<T> multiDatumDataSource) {
		this.multiDatumDataSource = multiDatumDataSource;
	}

	public OptionalService<DatumDao<T>> getDatumDao() {
		return datumDao;
	}

	public void setDatumDao(OptionalService<DatumDao<T>> datumDao) {
		this.datumDao = datumDao;
	}

}
