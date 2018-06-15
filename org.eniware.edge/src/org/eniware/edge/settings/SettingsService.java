/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.settings;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Service API for settings.
 * 
 * @version 1.2
 */
public interface SettingsService {

	/**
	 * Get a list of all possible non-factory setting providers.
	 * 
	 * @return list of setting providers (never <em>null</em>)
	 */
	List<SettingSpecifierProvider> getProviders();

	/**
	 * Get a list of all possible setting provider factories.
	 * 
	 * @return list of setting provider factories (never <em>null</em>)
	 */
	List<SettingSpecifierProviderFactory> getProviderFactories();

	/**
	 * Get a specific factory for a given UID.
	 * 
	 * @param factoryUID
	 *        the factory UID to get the providers for
	 * 
	 * @return the factory, or <em>null</em> if not available
	 */
	SettingSpecifierProviderFactory getProviderFactory(String factoryUID);

	/**
	 * Add a new factory instance, and return the new instance ID.
	 * 
	 * @param factoryUID
	 *        the factory UID to create the new instance for
	 * @return the new instance ID
	 */
	String addProviderFactoryInstance(String factoryUID);

	/**
	 * Delete an existing factory instance.
	 * 
	 * @param factoryUID
	 *        the factory UID to create the new instance for
	 * @param instanceUID
	 *        the instance UID to create the new instance for
	 */
	void deleteProviderFactoryInstance(String factoryUID, String instanceUID);

	/**
	 * Get all possible setting providers for a specific factory UID, grouped by
	 * instance ID.
	 * 
	 * @param factoryUID
	 *        the factory UID to get the providers for
	 * 
	 * @return mapping of instance IDs to list of setting providers (never
	 *         <em>null</em>)
	 */
	Map<String, List<FactorySettingSpecifierProvider>> getProvidersForFactory(String factoryUID);

	/**
	 * Get the current value of a setting.
	 * 
	 * @param provider
	 *        the provider of the setting
	 * @param setting
	 *        the setting
	 * @return the currernt setting value
	 */
	Object getSettingValue(SettingSpecifierProvider provider, SettingSpecifier setting);

	/**
	 * Update setting values.
	 * 
	 * @param command
	 *        the update command
	 */
	void updateSettings(SettingsCommand command);

	/**
	 * Export all settings as CSV formatted text.
	 * 
	 * @param out
	 *        the output stream
	 */
	void exportSettingsCSV(Writer out) throws IOException;

	/**
	 * Import all settings from a CSV formatted text stream.
	 * 
	 * @param in
	 *        the input stream
	 */
	void importSettingsCSV(Reader in) throws IOException;

	/**
	 * Import all settings from a CSV formatted text stream, with options.
	 * 
	 * @param in
	 *        The input stream to import.
	 * @param options
	 *        The import options.
	 * @since 1.2
	 */
	void importSettingsCSV(Reader in, SettingsImportOptions options) throws IOException;

	/**
	 * Create a backup of all settings, and return a backup object if the backup
	 * was performed.
	 * 
	 * <p>
	 * A new backup need not be created if the settings are unchanged. In that
	 * case, or if this method does not create a backup for any reason, this
	 * method should return <em>null</em>.
	 * </p>
	 * 
	 * @return the backup object, or <em>null</em> if no backup created
	 */
	SettingsBackup backupSettings();

	/**
	 * Get a collection of all known settings backups.
	 * 
	 * @return the backups, never <em>null</em>
	 */
	Collection<SettingsBackup> getAvailableBackups();

	/**
	 * Get a {@link Reader} to the backup data for a given SettingsBackup
	 * object.
	 * 
	 * @param backup
	 *        the backup to get the Reader for
	 * @return the Reader, or <em>null</em> if the backup cannot be found
	 */
	Reader getReaderForBackup(SettingsBackup backup);
}
