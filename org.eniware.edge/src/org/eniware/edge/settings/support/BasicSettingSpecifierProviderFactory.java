/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.settings.support;

import org.eniware.edge.settings.SettingSpecifierProviderFactory;
import org.springframework.context.MessageSource;

/**
 * Basic implementation of {@link SettingSpecifierProviderFactory}.
 * 
 * @version $Revision$
 */
public class BasicSettingSpecifierProviderFactory implements SettingSpecifierProviderFactory {

	private String factoryUID;
	private String displayName;
	private MessageSource messageSource;

	@Override
	public String getFactoryUID() {
		return factoryUID;
	}

	@Override
	public String getDisplayName() {
		return displayName;
	}

	@Override
	public MessageSource getMessageSource() {
		return messageSource;
	}

	public void setFactoryUID(String factoryUID) {
		this.factoryUID = factoryUID;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

}
