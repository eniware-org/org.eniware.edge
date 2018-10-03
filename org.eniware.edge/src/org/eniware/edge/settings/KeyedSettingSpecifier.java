/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.settings;

/**
 * A setting specifier that can store a value associated with a key.
 * 
 * @param <T>
 * 
 * @version 1.3
 */
public interface KeyedSettingSpecifier<T> extends SettingSpecifier, MappableSpecifier {

	/**
	 * Get the key for this setting.
	 * 
	 * @return the key to associate with this setting
	 */
	String getKey();

	/**
	 * Get the default value for this setting.
	 * 
	 * @return the default value
	 */
	T getDefaultValue();

	/**
	 * Return a setting specifier mapped to a new path, using a {@link Mapper}.
	 * 
	 * @param mapper
	 *        the mapper
	 * @return the new instance
	 * @deprecated Use
	 *             {@link MappableSpecifier#mappedWithMapper(org.eniware.edge.settings.MappableSpecifier.Mapper)}
	 *             directly.
	 */
	@Deprecated
	SettingSpecifier mappedWithMapper(Mapper mapper);

	/**
	 * Get transient flag.
	 * 
	 * <p>
	 * If a setting is transient, its associated value is never actually
	 * persisted and the {@link #getDefaultValue()} is treated as its "current"
	 * value. This can be used for
	 * 
	 * @return
	 */
	boolean isTransient();

	/**
	 * API to dynamically map a key to a new key.
	 * 
	 * @deprecated Use {@link MappableSpecifier.Mapper} directly.
	 */
	@Deprecated
	interface Mapper extends MappableSpecifier.Mapper {
		// nothing added
	}

	/**
	 * Get an optional list of message arguments to use when rendering a
	 * description of this specifier.
	 * 
	 * @return An optional list of message arguments.
	 * @since 1.3
	 */
	Object[] getDescriptionArguments();
}
