/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge;

import javax.net.ssl.SSLSocketFactory;

/**
 * API for dealing with SSL connections.
 * 
 * @author matt
 * @version 1.0
 */
public interface SSLService {

	/**
	 * Get a SSLSocketFactory configured appropriately for the SolarIn
	 * application.
	 * 
	 * @return
	 */
	SSLSocketFactory getSolarInSocketFactory();

}
