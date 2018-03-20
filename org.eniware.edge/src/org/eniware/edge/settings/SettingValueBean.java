/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.settings;

/**
 * An individual setting value.
 * 
 * @author matt
 * @version 1.1
 */
public class SettingValueBean {

	private String providerKey;
	private String instanceKey;
	private String key;
	private String value;
	private boolean trans;
	private boolean remove;

	/**
	 * Get the remove flag. If <em>true</em> this setting should be deleted.
	 * 
	 * @return The remove flag.
	 * @since 1.1
	 */
	public boolean isRemove() {
		return remove;
	}

	/**
	 * Set the remove flag.
	 * 
	 * @param remove
	 *        The flag to set.
	 * @since 1.2
	 */
	public void setRemove(boolean delete) {
		this.remove = delete;
	}

	public boolean isTransient() {
		return trans;
	}

	public void setTransient(boolean value) {
		this.trans = value;
	}

	public String getProviderKey() {
		return providerKey;
	}

	public void setProviderKey(String providerKey) {
		this.providerKey = providerKey;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getInstanceKey() {
		return instanceKey;
	}

	public void setInstanceKey(String instanceKey) {
		this.instanceKey = instanceKey;
	}

}
