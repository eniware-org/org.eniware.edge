/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.setup;

import java.util.Collection;
import java.util.Locale;

/**
 * API for a centrally managed manager of {@link SetupResource} instances.
 * 
 * @author matt
 * @version 1.0
 */
public interface SetupResourceService {

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
	 * Get a set of resources for a specific consumer type.
	 * 
	 * @param consumerType
	 *        The consumer type to get resources for.
	 * @param locale
	 *        The desired locale.
	 * @return All matching resources, never <em>null</em>.
	 */
	Collection<SetupResource> getSetupResourcesForConsumer(String consumerType, Locale locale);

}
