/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */
package org.eniware.edge.setup;

import java.util.Collection;
import java.util.Locale;

import org.eniware.edge.settings.SettingSpecifier;

/**
 * API for a provider of resource(s) to support {@link SettingSpecifier}
 * clients.
 * 
 * @author matt
 * @version 1.0
 */
public interface SetupResourceProvider {

	/** A consumer type for webapp-based resources. */
	String WEB_CONSUMER_TYPE = "web";

	/**
	 * Get a specific resource for a resource UID.
	 * 
	 * @param resourceUID
	 *        The ID of the resource to get.
	 * @param locale
	 *        The desired locale.
	 * @return The resource, or {@code null} if not available.
	 */
	SetupResource getSetupResource(String resourceUID, Locale locale);

	/**
	 * Get a set of resources for specific context and content type.
	 * 
	 * A {@code consumerType} represents the type of application the consumer of
	 * the setup resources represents. The {@link #WEB_CONSUMER_TYPE} represents
	 * a webapp, for example, and would be interested in resources such as
	 * JavaScript, CSS, images, etc.
	 * 
	 * @param consumerType
	 *        The consumer type to get all appropriate resources for.
	 * @param locale
	 *        The desired locale.
	 * @return All matching resources, never <em>null</em>.
	 */
	Collection<SetupResource> getSetupResourcesForConsumer(String consumerType, Locale locale);

}
