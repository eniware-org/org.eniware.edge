/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.ocpp;

/**
 * DAO for a global singleton {@link ChargeConfiguration} entity.
 * 
 * @version 1.0
 * @since 0.6
 */
public interface ChargeConfigurationDao {

	/**
	 * The EventAdmin topic used to post events when the charge configuration
	 * has been updated.
	 */
	String EVENT_TOPIC_CHARGE_CONFIGURATION_UPDATED = "net/eniwarenetwork/Edge/ocpp/CHARGE_CONF_UPDATED";

	/**
	 * Store (create or update) a the charge configuration.
	 * 
	 * @param config
	 *        The {@link ChargeConfiguration} to store.
	 */
	void storeChargeConfiguration(ChargeConfiguration config);

	/**
	 * Get the {@link ChargeConfiguration}.
	 * 
	 * @return The charge configuration, or <em>null</em> if not available.
	 */
	ChargeConfiguration getChargeConfiguration();

}
