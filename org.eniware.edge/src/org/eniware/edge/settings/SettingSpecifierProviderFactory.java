/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.settings;

import org.springframework.context.MessageSource;

/**
 * API for a {@link SettingSpecifierProvider} managed as a factory.
 * 
 * @author matt
 * @version $Revision$
 */
public interface SettingSpecifierProviderFactory {

	/**
	 * Get a unique, application-wide factory ID.
	 * 
	 * <p>
	 * This ID must be unique across all setting providers registered within the
	 * system.
	 * </p>
	 * 
	 * @return unique ID
	 */
	String getFactoryUID();

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

}
