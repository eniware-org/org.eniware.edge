/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.dao;

import java.util.Date;
import java.util.List;

import org.eniware.edge.domain.Datum;

/**
 * Data Access Object (DAO) API for {@link Datum} objects.
 * 
 * @version 1.2
 * @param <T>
 *        the type of Datum this DAO supports
 */
public interface DatumDao<T extends Datum> {

	/**
	 * An {@link org.osgi.service.event.Event} topic for when a {@link Datum}
	 * has been persisted.
	 * 
	 * <p>
	 * The properties of the event shall be any of the JavaBean properties of
	 * the Datum supported by events (i.e. any simple Java property such as
	 * numbers and strings). In addition, the {@link Datum#DATUM_TYPE_PROPERTY}
	 * property shall be populated with the name of the <em>core</em> class name
	 * of the datum type.
	 * </p>
	 * 
	 * @since 1.2
	 */
	public static final String EVENT_TOPIC_DATUM_STORED = "net/eniwarenetwork/Edge/dao/DATUM_STORED";

	/**
	 * Get the class supported by this Dao.
	 * 
	 * @return class
	 */
	Class<? extends T> getDatumType();

	/**
	 * Store (create or update) a datum.
	 * 
	 * @param datum
	 *        the datum to persist
	 * @return the generated primary key
	 */
	void storeDatum(T datum);

	/**
	 * Get a List of Datum instances that have not been uploaded yet to a
	 * specific destination.
	 * 
	 * <p>
	 * This does not need to return all data, it can limit the amount returned
	 * at one time to conserve memory. This method can be called repeatedly if
	 * needed.
	 * </p>
	 * 
	 * @param destination
	 *        the destination to check
	 * @return list of Datum, or empty List if none available
	 */
	List<T> getDatumNotUploaded(String destination);

	/**
	 * Persist a {@link DatumUpload} instance.
	 * 
	 * @param datum
	 *        the Datum that has been uploaded successfully
	 * @param date
	 *        the date it was uploaded
	 * @param destination
	 *        the destination the Datum was uploaded to
	 * @param trackingId
	 *        the remote tracking ID assigned to the uploaded Datum
	 */
	void setDatumUploaded(T datum, Date date, String destination, String trackingId);

	/**
	 * Delete both Datum and DatumUpload objects that have been successfully
	 * uploaded to at least one destination and are older than the specified
	 * number of hours.
	 * 
	 * <p>
	 * This is designed to free up space from local database storage for devices
	 * with limited storage capacity. It will not delete any Datum objects that
	 * have not been successfully uploaded anywhere.
	 * </p>
	 * 
	 * @param hours
	 *        the minimum number of hours old the data must be to delete
	 * @return the number of Datum (and associated DatumUpload) entities deleted
	 */
	int deleteUploadedDataOlderThan(int hours);

}
