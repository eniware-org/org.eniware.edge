/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.settings.support;

import org.eniware.edge.settings.KeyedSettingSpecifier;
import org.eniware.edge.settings.SettingSpecifier;

/**
 * Base implementation of {@link KeyedSettingSpecifier}.
 * 
 * @author matt
 * @version 1.2
 */
public abstract class BaseKeyedSettingSpecifier<T> extends BaseSettingSpecifier
		implements KeyedSettingSpecifier<T> {

	private String key;
	private T defaultValue;
	private boolean trans;
	private Object[] descriptionArguments;

	/**
	 * Constructor.
	 * 
	 * <p>
	 * The {@code transient} property will be set to <em>false</em>
	 * </p>
	 * 
	 * @param key
	 *        the key
	 * @param defaultValue
	 *        the default value
	 */
	public BaseKeyedSettingSpecifier(String key, T defaultValue) {
		this(key, defaultValue, false);
	}

	/**
	 * Constructor.
	 * 
	 * @param key
	 *        the key
	 * @param defaultValue
	 *        the default value
	 * @param trans
	 *        the transient flag value
	 */
	public BaseKeyedSettingSpecifier(String key, T defaultValue, boolean trans) {
		super();
		setKey(key);
		setDefaultValue(defaultValue);
		setTransient(trans);
	}

	@Override
	public String getKey() {
		return this.key;
	}

	@Override
	public T getDefaultValue() {
		return this.defaultValue;
	}

	@Override
	public boolean isTransient() {
		return trans;
	}

	@Override
	public SettingSpecifier mappedTo(String prefix) {
		return mappedWithPlaceholer(prefix + "%s");
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(getClass().getSimpleName());
		builder.append("{key=");
		builder.append(key);
		builder.append("}");
		return builder.toString();
	}

	public void setTransient(boolean value) {
		this.trans = value;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setDefaultValue(T defaultValue) {
		this.defaultValue = defaultValue;
	}

	/**
	 * Set the optional description arguments.
	 * 
	 * @param descriptionArguments
	 *        The arguments to set.
	 * @since 1.2
	 */
	public void setDescriptionArguments(Object[] descriptionArguments) {
		this.descriptionArguments = descriptionArguments;
	}

	@Override
	public Object[] getDescriptionArguments() {
		return descriptionArguments;
	}

}
