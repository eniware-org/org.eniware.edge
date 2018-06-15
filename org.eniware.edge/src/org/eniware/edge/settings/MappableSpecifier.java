/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.settings;

/**
 * API for a specifier that can be mapped to some other specifier.
 * 
 * @version 1.0
 */
public interface MappableSpecifier {

	/**
	 * API to dynamically map a key to a new key.
	 */
	interface Mapper {

		/**
		 * Map an input key to an output key.
		 * 
		 * @param key
		 *        the key to map
		 * @return the mapped key
		 */
		String mapKey(String key);

	}

	/**
	 * Return a setting specifier mapped to a new path.
	 * 
	 * <p>
	 * This is to allow delegating setting specifiers to re-map the key.
	 * </p>
	 * 
	 * @param prefix
	 *        the new prefix to add to the key
	 * @return the new instance
	 */
	SettingSpecifier mappedTo(String prefix);

	/**
	 * Return a setting specifier mapped to a new path, using a format template.
	 * 
	 * @param template
	 *        the format template
	 * @return the new instance
	 */
	SettingSpecifier mappedWithPlaceholer(String template);

	/**
	 * Return a setting specifier mapped to a new path, using a {@link Mapper}.
	 * 
	 * @param mapper
	 *        the mapper
	 * @return the new instance
	 */
	SettingSpecifier mappedWithMapper(Mapper mapper);

}
