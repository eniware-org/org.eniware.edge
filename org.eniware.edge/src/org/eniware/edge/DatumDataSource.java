/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge;

import org.eniware.edge.domain.Datum;

/**
 * API for collecting {@link Datum} objects from some device.
 * 
 * @version 1.3
 * @param <T>
 *        the Datum type
 */
public interface DatumDataSource<T extends Datum> extends Identifiable {

	/**
	 * An {@link org.osgi.service.event.Event} topic for when a {@link Datum}
	 * has been read, sampled, or in some way captured by a
	 * {@link DatumDataSource}. The properties of the event shall be any of the
	 * JavaBean properties of the Datum supported by events (i.e. any simple
	 * Java property such as numbers and strings). In addition, the
	 * {@link #EVENT_DATUM_CAPTURED_DATUM_TYPE} property shall be populated with
	 * the name of the <em>core</em> class name of the datum type.
	 * 
	 * @since 1.2
	 */
	public static final String EVENT_TOPIC_DATUM_CAPTURED = "net/eniwarenetwork/Edge/DatumDataSource/DATUM_CAPTURED";

	/**
	 * An {@link org.osgi.service.event.Event} property for the string name of
	 * the <em>core</em> class of the datum type associated with the event.
	 * 
	 * @since 1.2
	 * @deprecated use {@link Datum#DATUM_TYPE_PROPERTY}
	 */
	@Deprecated
	public static final String EVENT_DATUM_CAPTURED_DATUM_TYPE = Datum.DATUM_TYPE_PROPERTY;

	/**
	 * Get the class supported by this DataSource.
	 * 
	 * @return class
	 */
	Class<? extends T> getDatumType();

	/**
	 * Read the current value from the data source, returning as an unpersisted
	 * {@link Datum} object.
	 * 
	 * @return Datum
	 */
	T readCurrentDatum();

}
