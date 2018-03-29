/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.ocpp;

import net.solarnetwork.node.Identifiable;
import ocpp.v15.cs.CentralSystemService;

/**
 * A factory for {@link CentralSystemService} instances.
 * 
 * @version 1.0
 */
public interface CentralSystemServiceFactory extends Identifiable {

	/**
	 * Get the configured CentralSystemService.
	 * 
	 * @return The service.
	 */
	CentralSystemService service();

	/**
	 * Return <em>true</em> if the {@code BootNotification} message has been
	 * posted to the central system.
	 * 
	 * @return Boolean
	 */
	boolean isBootNotificationPosted();

	/**
	 * Post the {@code BootNotification} message to the central system.
	 * 
	 * @return Return <em>true</em> if the notification was sent and the
	 *         response received without error.
	 */
	boolean postBootNotification();

	/**
	 * Get the ChargeBoxIdentity value to use.
	 * 
	 * @return The ChargeBoxIdentity value to use, or <em>null</em> if not
	 *         available.
	 */
	public String chargeBoxIdentity();

}
