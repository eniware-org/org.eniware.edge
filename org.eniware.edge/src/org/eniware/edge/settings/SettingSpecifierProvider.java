/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.settings;

import java.util.List;

import org.springframework.context.MessageSource;

/**
 * API for a provider of {@link SettingSpecifier} instances, to 
 * publish application-managed settings.
 * 
 * @version $Revision$
 */
public interface SettingSpecifierProvider {

	/**
	 * Get a unique, application-wide setting ID.
	 * 
	 * <p>
	 * This ID must be unique across all setting providers registered within the
	 * system.
	 * </p>
	 * 
	 * @return unique ID
	 */
	String getSettingUID();

	/**
	 * Get a non-localized display name.
	 * 
	 * @return non-localized display name
	 */
	String getDisplayName();

	/**
	 * Get a MessageSource to localize the setting text.
	 * 
	 * <p>
	 * This method can return <em>null</em> if the provider does not have any
	 * localized resources.
	 * </p>
	 * 
	 * @return the MessageSource, or <em>null</em>
	 */
	MessageSource getMessageSource();
	
	/**
	 * Get a list of {@link SettingSpecifier} instances.
	 * 
	 * @return list of {@link SettingSpecifier}
	 */
	List<SettingSpecifier> getSettingSpecifiers();

}
