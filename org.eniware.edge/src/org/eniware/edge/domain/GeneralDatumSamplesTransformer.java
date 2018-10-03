/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.domain;

import org.eniware.domain.GeneralDatumSamples;

/**
 * API for taking a {@link GeneralDatumSamples} object and transforming it in
 * some way into a different {@link GeneralDatumSamples) object.
 * 
 * @version 1.1
 */
public interface GeneralDatumSamplesTransformer {

	/**
	 * Transform a samples instance.
	 * 
	 * <p>
	 * Generally this method is not meant to make changes to the passed in
	 * {@code samples} instance. Rather it should apply changes to a copy of
	 * {@code samples} and return the copy. If no changes are necessary then the
	 * {@code samples} instance may be returned.
	 * </p>
	 * 
	 * <p>
	 * This method may also return {@literal null} to indicate the
	 * {@code samples} instance should not be processed, or that there is
	 * essentially no data to associate with this particular {@code datum}.
	 * </p>
	 * 
	 * @param datum
	 *        The {@link Datum} associated with {@code samples}.
	 * @param samples
	 *        The samples object to transform.
	 * @return The transformed samples instance, which may be the
	 *         {@code samples} instance or a new instance, or {@literal null} to
	 *         indicate the samples should not be processed.
	 */
	GeneralDatumSamples transformSamples(Datum datum, GeneralDatumSamples samples);

}
