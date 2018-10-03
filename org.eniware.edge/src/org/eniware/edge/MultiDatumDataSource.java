/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge;

import java.util.Collection;

import org.eniware.edge.domain.Datum;

/**
 * API for collecting multiple {@link Datum} objects from some device.
 * 
 * @version $Id$
 */
public interface MultiDatumDataSource<T extends Datum> extends Identifiable {

	/**
	 * Get the class supported by this DataSource.
	 * 
	 * @return class
	 */
	Class<? extends T> getMultiDatumType();

	/**
	 * Read multiple values from the data source, returning as a collection of
	 * unpersisted {@link Datum} objects.
	 * 
	 * @return Datum
	 */
	Collection<T> readMultipleDatum();

}
