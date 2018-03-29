/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge;

import java.util.Collection;
import java.util.List;

import org.eniware.edge.domain.Datum;

/**
 * API for posting local SolarNode data to a remote server in bulk.
 * 
 * @version 1.2
 */
public interface BulkUploadService extends UploadService {

	/**
	 * Upload Datum data in bulk.
	 * 
	 * <p>
	 * The returned list of results will be ordered in normal iterating order
	 * for the passed in {@code data} Collection, and will contain exactly the
	 * same number of objects (one for each Datum). The
	 * {@link BulkUploadResult#getId()} value can be used to determine if the
	 * Datum was uploaded successfully (if it is non-null, it should be
	 * considered successfully uploaded).
	 * </p>
	 * 
	 * <p>
	 * If the supplied Datum object is not supported by an implementation this
	 * method will throw an {@link IllegalArgumentException}.
	 * </p>
	 * 
	 * @param data
	 *        the data to upload
	 * @return list of BulkUploadResult objects
	 */
	List<BulkUploadResult> uploadBulkDatum(Collection<Datum> data);

}
