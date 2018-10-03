/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge;

import javax.net.ssl.SSLSocketFactory;

/**
 * API for dealing with SSL connections.
 * 
 * @version 1.0
 */
public interface SSLService {

	/**
	 * Get a SSLSocketFactory configured appropriately for the EniwareIn
	 * application.
	 * 
	 * @return
	 */
	SSLSocketFactory getEniwareInSocketFactory();

}
