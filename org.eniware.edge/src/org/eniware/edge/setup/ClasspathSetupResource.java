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
import org.springframework.core.io.ClassPathResource;

/**
 * Static classpath based implementation of {@link SetupResource}.
 * 
 * @version 1.1
 */
public class ClasspathSetupResource extends BaseStaticSetupResource {

	private final String path;
	private final Class<?> clazz;

	/**
	 * Construct with values. Caching will be one day, and all consumer types
	 * will be supported. The locale will be inferred from the path.
	 * 
	 * @param uid
	 *        the {@code resourceUID} value
	 * @param path
	 *        the classpath resource path
	 * @param clazz
	 *        the class to load the resource relative to
	 * @param contentType
	 *        the content tpye
	 * @throws IOException
	 *         if an error occurs accessing the resource
	 */
	public ClasspathSetupResource(String uid, String path, Class<?> clazz, String contentType)
			throws IOException {
		this(uid, path, clazz, contentType, localeForPath(path), 86400, null, null);
	}

	/**
	 * Construct with values. Caching will be one day. The locale will be
	 * inferred from the path.
	 * 
	 * @param uid
	 *        the {@code resourceUID} value
	 * @param path
	 *        the classpath resource path
	 * @param clazz
	 *        the class to load the resource relative to
	 * @param contentType
	 *        the content type
	 * @param consumerTypes
	 *        the optional consumer types
	 * @throws IOException
	 *         if an error occurs accessing the resource
	 */
	public ClasspathSetupResource(String uid, String path, Class<?> clazz, String contentType,
			Set<String> consumerTypes) throws IOException {
		this(uid, path, clazz, contentType, localeForPath(path), 86400, consumerTypes, null);
	}

	/**
	 * Construct with values. Caching will be one day. The locale will be
	 * inferred from the path.
	 * 
	 * @param uid
	 *        the {@code resourceUID} value
	 * @param path
	 *        the classpath resource path
	 * @param clazz
	 *        the class to load the resource relative to
	 * @param contentType
	 *        the content type
	 * @param consumerTypes
	 *        the optional consumer types
	 * @param roles
	 *        the optional required roles
	 * @throws IOException
	 *         if an error occurs accessing the resource
	 */
	public ClasspathSetupResource(String uid, String path, Class<?> clazz, String contentType,
			Set<String> consumerTypes, Set<String> roles) throws IOException {
		this(uid, path, clazz, contentType, localeForPath(path), 86400, consumerTypes, roles);
	}

	/**
	 * Construct with values.
	 * 
	 * @param uid
	 *        the {@code resourceUID} value
	 * @param path
	 *        the classpath resource path
	 * @param clazz
	 *        the class to load the resource relative to
	 * @param contentType
	 *        the content type
	 * @param locale
	 *        the locale
	 * @param cacheSeconds
	 *        the maximum cache seconds
	 * @throws IOException
	 *         if an error occurs accessing the resource
	 */
	public ClasspathSetupResource(String uid, String path, Class<?> clazz, String contentType,
			Locale locale, int cacheSeconds) throws IOException {
		this(uid, path, clazz, contentType, locale, cacheSeconds, null, null);
	}

	/**
	 * Constructor.
	 * 
	 * @param uid
	 *        the {@code resourceUID} value
	 * @param path
	 *        the classpath resource path
	 * @param clazz
	 *        the class to load the resource relative to
	 * @param contentType
	 *        the content type
	 * @param locale
	 *        the locale
	 * @param cacheSeconds
	 *        the maximum cache seconds
	 * @param consumerTypes
	 *        the optional consumer types
	 * @param roles
	 *        the optional required roles
	 * @throws IOException
	 *         if an error occurs accessing the resource
	 */
	public ClasspathSetupResource(String uid, String path, Class<?> clazz, String contentType,
			Locale locale, int cacheSeconds, Set<String> consumerTypes, Set<String> roles)
			throws IOException {
		this(uid, path, clazz, contentType, locale, cacheSeconds, consumerTypes, roles,
				SetupResourceScope.Default);
	}

	/**
	 * Full constructor.
	 * 
	 * @param uid
	 *        the {@code resourceUID} value
	 * @param path
	 *        the classpath resource path
	 * @param clazz
	 *        the class to load the resource relative to
	 * @param contentType
	 *        the content type
	 * @param locale
	 *        the locale
	 * @param cacheSeconds
	 *        the maximum cache seconds
	 * @param consumerTypes
	 *        the optional consumer types
	 * @param roles
	 *        the optional required roles
	 * @param scope
	 *        the scope to use
	 * @throws IOException
	 *         if an error occurs accessing the resource
	 * @since 1.1
	 */
	public ClasspathSetupResource(String uid, String path, Class<?> clazz, String contentType,
			Locale locale, int cacheSeconds, Set<String> consumerTypes, Set<String> roles,
			SetupResourceScope scope) throws IOException {
		super(uid, contentType, locale, cacheSeconds, consumerTypes, roles,
				new ClassPathResource(path, clazz), scope);
		this.path = path;
		this.clazz = clazz;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return clazz.getResourceAsStream(path);
	}

}
