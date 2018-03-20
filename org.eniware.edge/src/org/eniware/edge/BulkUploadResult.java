/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge;

import org.eniware.edge.domain.Datum;

/**
 * Result object for a specific datum uploaded in bulk.
 * 
 * <p>
 * The {@code id} value represents the remote ID received from the server for
 * the given {@code datum}. This value can be stored in {@link DatumUpload}
 * objects as a receipt for the upload transaction.
 * </p>
 * 
 * @author matt
 * @version 1.1
 */
public class BulkUploadResult {

	private final Datum datum;
	private final String id;

	/**
	 * Constructor.
	 * 
	 * @param datum
	 *        the datum
	 * @param id
	 *        the ID
	 */
	public BulkUploadResult(Datum datum, String id) {
		this.datum = datum;
		this.id = id;
	}

	/**
	 * @return the datum
	 */
	public Datum getDatum() {
		return datum;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

}
