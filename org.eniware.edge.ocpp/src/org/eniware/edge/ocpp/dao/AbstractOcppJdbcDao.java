/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.ocpp.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import net.solarnetwork.node.dao.jdbc.AbstractJdbcDao;

/**
 * Abstract base class for OCPP related DAOs.
 * 
 * @version 1.0
 * @param <T>
 *        the primary domain object type managed by this DAO
 */
public abstract class AbstractOcppJdbcDao<T> extends AbstractJdbcDao<T> {

	protected final Calendar utcCalendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
	protected final DatatypeFactory datatypeFactory = getDatatypeFactory();
	protected final XMLGregorianCalendar timestampDefaults = getTimestampDefaults();

	protected final Calendar calendarForDate(Date date) {
		Calendar utcCal = (Calendar) utcCalendar.clone();
		utcCal.setTime(date);
		return utcCal;
	}

	protected final Calendar calendarForXMLDate(XMLGregorianCalendar xmlDate) {
		Calendar cal = xmlDate.toGregorianCalendar(null, null, timestampDefaults);
		Calendar utcCal = (Calendar) utcCalendar.clone();
		utcCal.setTimeInMillis(cal.getTimeInMillis());
		return utcCal;
	}

	protected final DatatypeFactory getDatatypeFactory() {
		try {
			return DatatypeFactory.newInstance();
		} catch ( DatatypeConfigurationException e ) {
			throw new RuntimeException(e);
		}
	}

	protected final XMLGregorianCalendar getTimestampDefaults() {
		try {
			DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
			return datatypeFactory.newXMLGregorianCalendarTime(0, 0, 0, 0);
		} catch ( Exception e ) {
			log.error("Exception greating default XMLGregorianCalendar instance", e);
			return null;
		}
	}

}
