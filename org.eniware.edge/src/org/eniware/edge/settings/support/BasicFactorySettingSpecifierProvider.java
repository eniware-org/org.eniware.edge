/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.settings.support;

import java.util.List;

import org.eniware.edge.settings.FactorySettingSpecifierProvider;
import org.eniware.edge.settings.SettingSpecifier;
import org.eniware.edge.settings.SettingSpecifierProvider;
import org.springframework.context.MessageSource;

/**
 * Basic implementation of {@link FactorySettingSpecifierProvider} that
 * delegates all {@link SettingSpecifierProvider} methods to a delegate.
 * 
 * @author matt
 * @version $Revision$
 */
public class BasicFactorySettingSpecifierProvider implements FactorySettingSpecifierProvider {

	private final String factoryInstanceUID;
	private final SettingSpecifierProvider delegate;

	public BasicFactorySettingSpecifierProvider(String factoryInstanceUID,
			SettingSpecifierProvider delegate) {
		super();
		this.factoryInstanceUID = factoryInstanceUID;
		this.delegate = delegate;
	}

	@Override
	public String getSettingUID() {
		return delegate.getSettingUID();
	}

	@Override
	public String getDisplayName() {
		return delegate.getDisplayName();
	}

	@Override
	public MessageSource getMessageSource() {
		return delegate.getMessageSource();
	}

	@Override
	public List<SettingSpecifier> getSettingSpecifiers() {
		return delegate.getSettingSpecifiers();
	}

	@Override
	public String getFactoryInstanceUID() {
		return factoryInstanceUID;
	}

}
