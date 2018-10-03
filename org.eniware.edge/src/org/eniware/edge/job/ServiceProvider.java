/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.job;

import java.util.Collection;
import java.util.Map;

/**
 * API for a provider of runtime service configuration. This is designed for the
 * scenario where a managed job wants to expose a service that is used by the
 * job instance as a registered service itself.
 * 
 * @version 1.0
 */
public interface ServiceProvider {

	/**
	 * A single service configuration.
	 */
	interface ServiceConfiguration {

		/**
		 * The service instance.
		 * 
		 * @return The service instance.
		 */
		Object getService();

		/**
		 * The name of all interfaces this service supports.
		 * 
		 * @return An array of interface names the service supports.
		 */
		String[] getInterfaces();

		/**
		 * Get an optional map of service properties.
		 * 
		 * @return An optional map of service properties.
		 */
		Map<String, ?> getProperties();

	}

	/**
	 * Get a collection of service configurations.
	 * 
	 * @return A collection of configuration objects.
	 */
	Collection<ServiceConfiguration> getServiceConfigurations();

}
