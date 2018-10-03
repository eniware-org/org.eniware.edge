/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.util;

import java.util.Map;

/**
 * API for a configuration object.
 * 
 * <p>This API can be used to publish configuration information from one OSGi
 * bundle to another, without knowing the actual implementation of any 
 * target service. For example, imagine two serial IO implementation bundles
 * exist, but the EniwareEdge application does not know which one the user
 * will choose to use. In this case, the EniwareEdge application bundle need
 * not import either of the IO implementation bundles directly. Instead it
 * can publish a {@code BeanConfiguration} service that the IO implementations
 * can register to use to configure itself.</p>
 * 
 * <p>The {@link BeanConfigurationServiceRegistrationListener} provides an
 * easy way for implementation bundles to bind to {@link BeanConfiguration}
 * services at runtime and dynamically publish services based on this
 * configuration.</p>
 * 
 * @version $Id$
 * @see BeanConfigurationServiceRegistrationListener
 */
public interface BeanConfiguration {

	/**
	 * Get the configuration properties as a Map.
	 * 
	 * @return Map of configuration properties
	 */
	Map<String, Object> getConfiguration();
	
	/**
	 * Get configuration attributes as a Map.
	 * 
	 * <p>These attributes are not configuration properties that get applied
	 * directly to services, but can be used to distinguish one configuration
	 * from another.</p>
	 * 
	 * @return Map of configuration attributes
	 */
	Map<String, Object> getAttributes();
	
	/**
	 * Get a configuration ordering.
	 * 
	 * <p>The ordering can be used to treat similar configurations in an
	 * ordered fashion.</p>
	 * 
	 * @return an ordering
	 */
	Integer getOrdering();
	
}
