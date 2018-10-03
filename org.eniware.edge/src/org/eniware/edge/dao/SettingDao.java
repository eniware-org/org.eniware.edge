/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.dao;

import java.util.Date;
import java.util.List;

import org.eniware.edge.Setting;
import org.eniware.edge.support.KeyValuePair;

/**
 * Data access object API for setting key/value pairs.
 * 
 * <p>
 * This DAO is for very simple key/value based settings and other perstitable
 * runtime data.
 * </p>
 * 
 * <p>
 * It also supports key+type/value pairs, where key and type are separate
 * values. This can be useful for grouping sets of keys together, or for adding
 * and namespace to prevent key collisions across different packages.
 * </p>
 * 
 * @version 1.2
 */
public interface SettingDao extends BatchableDao<Setting> {

	/**
	 * Event topic for when a non-volatile setting has been changed, by either
	 * adding new, updating an existing, or deleting. The various
	 * {@code SETTING_*} properties will be provided as event properties.
	 */
	String EVENT_TOPIC_SETTING_CHANGED = "net/eniwarenetwork/Edge/dao/SETTING_CHANGED";

	/** Event property key for the setting key. */
	String SETTING_KEY = "Key";

	/** Event property key for the setting type. */
	String SETTING_TYPE = "Type";

	/** Event property key for the setting value. */
	String SETTING_VALUE = "Value";

	/**
	 * Persist a new key/value pair, or update an existing key.
	 * 
	 * <p>
	 * The type key will be set to a default value.
	 * </p>
	 * 
	 * @param key
	 *        the setting key
	 * @param value
	 *        the setting value
	 */
	void storeSetting(String key, String value);

	/**
	 * Persist a new key+type/value pair, or update an existing key+type.
	 * 
	 * @param key
	 *        the setting key
	 * @param type
	 *        the type key
	 * @param value
	 *        the setting value
	 */
	void storeSetting(String key, String type, String value);

	/**
	 * Persist a Setting, or update an existing Setting.
	 * 
	 * @param setting
	 *        the setting
	 */
	void storeSetting(Setting setting);

	/**
	 * Get a Setting object for a given setting key+type.
	 * 
	 * @param key
	 *        the key
	 * @param type
	 *        the type
	 * @return the setting, or <em>null</em> if not found
	 */
	Setting readSetting(String key, String type);

	/**
	 * Get the first value for a key.
	 * 
	 * @param key
	 *        the key to get the first value for
	 * @return the first associated value, or <em>null</em> if key not found
	 */
	String getSetting(String key);

	/**
	 * Get all settings for a specific key.
	 * 
	 * @param key
	 *        the key to get the settings for
	 * @return list of {@link KeyValuePair} objects, where the {@code key} will
	 *         be set to the {@code type} value
	 */
	List<KeyValuePair> getSettings(String key);

	/**
	 * Get the value for a key+type.
	 * 
	 * @param key
	 *        the key to get the value for
	 * @param type
	 *        the type to get the value for
	 * @return the associated value, or <em>null</em> if key not found
	 */
	String getSetting(String key, String type);

	/**
	 * Delete a setting key/value pair.
	 * 
	 * <p>
	 * This method will not fail if the key does not exist.
	 * </p>
	 * 
	 * @param key
	 *        the key to delete
	 * @return true if the key existed and was deleted
	 */
	boolean deleteSetting(String key);

	/**
	 * Delete a setting key+type/value pair.
	 * 
	 * <p>
	 * This method will not fail if the key does not exist.
	 * </p>
	 * 
	 * @param key
	 *        the key to delete
	 * @param type
	 *        the type to delete
	 * @return true if the key existed and was deleted
	 */
	boolean deleteSetting(String key, String type);

	/**
	 * Get the modification date for a specific setting.
	 * 
	 * @param key
	 *        the key
	 * @param type
	 *        the type
	 * @return the date
	 */
	Date getSettingModificationDate(String key, String type);

	/**
	 * Get the most recent modification date of all settings.
	 * 
	 * <p>
	 * The special {@code type} value {@link #TYPE_IGNORE_MODIFICATION_DATE} is
	 * considered by this method, and rows with this type are ignored when
	 * calculating the most recent modification date.
	 * </p>
	 * 
	 * @return the modification date
	 */
	Date getMostRecentModificationDate();

}
