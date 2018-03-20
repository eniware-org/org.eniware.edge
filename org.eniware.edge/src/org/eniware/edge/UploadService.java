/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge;

import org.eniware.edge.domain.Datum;

/**
 * API for posting local SolarNode data to a remote server.
 * 
 * @author matt.magoffin
 * @version 1.2
 */
public interface UploadService {

	/**
	 * An event topic for when a {@link Datum} has been uploaded.
	 * 
	 * <p>
	 * The properties of the event shall be {@link Datum#asSimpleMap()}.
	 * </p>
	 * 
	 * @since 1.2
	 */
	public static final String EVENT_TOPIC_DATUM_UPLOADED = "net/solarnetwork/node/UploadService/DATUM_UPLOADED";

	/**
	 * Get a unique key for this service.
	 * 
	 * <p>
	 * This key can be used as the {@code destination} value for
	 * {@link DatumUpload} objects. It need be unique across other UploadService
	 * implementations only.
	 * </p>
	 * 
	 * @return unique key
	 */
	String getKey();

	/**
	 * Upload Datum data.
	 * 
	 * <p>
	 * The returned primary key can be used as the {@code trackingId} value for
	 * {@link DatumUpload} objects.
	 * </p>
	 * 
	 * <p>
	 * If the supplied Datum object is not supported by an implementation this
	 * method will throw an {@link IllegalArgumentException}.
	 * </p>
	 * 
	 * @param data
	 *        the data to upload
	 * @return the remote primary key
	 */
	String uploadDatum(Datum data);

}
