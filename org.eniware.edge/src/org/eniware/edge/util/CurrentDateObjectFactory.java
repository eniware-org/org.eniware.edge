/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.util;

import java.util.Date;

import org.springframework.beans.factory.ObjectFactory;

/**
 * Factory for Date objects.
 * 
 * @author matt
 * @version $Id$
 */
public class CurrentDateObjectFactory implements ObjectFactory<Date> {

	/* (non-Javadoc)
	 * @see net.solarnetwork.node.util.GenericObjectFactory#getObject()
	 */
	public Date getObject() {
		return new Date();
	}

}
