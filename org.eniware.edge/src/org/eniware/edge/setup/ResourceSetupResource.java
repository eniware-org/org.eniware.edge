/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.setup;

import static org.eniware.edge.setup.SetupResourceUtils.localeForPath;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Set;
import org.springframework.core.io.Resource;

/**
 * A {@link SetupResource} that delegates to a {@link Resource}.
 * 
 * @version 1.1
 */
public class ResourceSetupResource extends BaseStaticSetupResource {

	private final Resource resource;

	/**
	 * Construct from a resource, detecting the locale from the resource
	 * filename.
	 * 
	 * @param resource
	 *        the resource
	 * @param uid
	 *        the {@code resourceUID}
	 * @param contentType
	 *        the content type
	 * @param cacheSeconds
	 *        the maximum cache seconds
	 * @param consumerTypes
	 *        the optional consumer types
	 * @param roles
	 *        the optional required roles
	 */
	public ResourceSetupResource(Resource resource, String uid, String contentType, int cacheSeconds,
			Set<String> consumerTypes, Set<String> roles) throws IOException {
		super(uid, contentType, localeForPath(resource.getFilename()), cacheSeconds, consumerTypes,
				roles, resource);
		this.resource = resource;
	}

	/**
	 * Construct from a resource, detecting the locale from the resource
	 * filename.
	 * 
	 * @param resource
	 *        the resource
	 * @param uid
	 *        the {@code resourceUID}
	 * @param contentType
	 *        the content type
	 * @param cacheSeconds
	 *        the maximum cache seconds
	 * @param consumerTypes
	 *        the optional consumer types
	 * @param roles
	 *        the optional required roles
	 * @param scope
	 *        the scope to use
	 * @since 1.1
	 */
	public ResourceSetupResource(Resource resource, String uid, String contentType, int cacheSeconds,
			Set<String> consumerTypes, Set<String> roles, SetupResourceScope scope) throws IOException {
		super(uid, contentType, localeForPath(resource.getFilename()), cacheSeconds, consumerTypes,
				roles, resource, scope);
		this.resource = resource;
	}

	/**
	 * Construct from a resource.
	 * 
	 * @param resource
	 *        the resource
	 * @param uid
	 *        the {@code resourceUID}
	 * @param contentType
	 *        the content type
	 * @param locale
	 *        the locale to use
	 * @param cacheSeconds
	 *        the maximum cache seconds
	 * @param consumerTypes
	 *        the optional consumer types
	 * @param roles
	 *        the optional required roles
	 */
	public ResourceSetupResource(Resource resource, String uid, String contentType, Locale locale,
			int cacheSeconds, Set<String> consumerTypes, Set<String> roles) throws IOException {
		super(uid, contentType, locale, cacheSeconds, consumerTypes, roles, resource);
		this.resource = resource;
	}

	/**
	 * Construct from a resource.
	 * 
	 * @param resource
	 *        the resource
	 * @param uid
	 *        the {@code resourceUID}
	 * @param contentType
	 *        the content type
	 * @param locale
	 *        the locale to use
	 * @param cacheSeconds
	 *        the maximum cache seconds
	 * @param consumerTypes
	 *        the optional consumer types
	 * @param roles
	 *        the optional required roles
	 * @param scope
	 *        the scope to use
	 * @since 1.1
	 */
	public ResourceSetupResource(Resource resource, String uid, String contentType, Locale locale,
			int cacheSeconds, Set<String> consumerTypes, Set<String> roles, SetupResourceScope scope)
			throws IOException {
		super(uid, contentType, locale, cacheSeconds, consumerTypes, roles, resource, scope);
		this.resource = resource;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return resource.getInputStream();
	}

}
