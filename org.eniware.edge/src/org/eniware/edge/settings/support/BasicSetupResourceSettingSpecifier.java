/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */


package org.eniware.edge.settings.support;

import java.util.Map;

import org.eniware.edge.settings.SetupResourceSettingSpecifier;
import org.eniware.edge.setup.SetupResourceProvider;

/**
 * Basic implementation of {@link SetupResourceSettingSpecifier}.
 * 
 * @version 1.0
 */
public class BasicSetupResourceSettingSpecifier extends BaseSettingSpecifier
		implements SetupResourceSettingSpecifier {

	private final SetupResourceProvider provider;
	private final Map<String, ?> props;

	/**
	 * Construct without properties.
	 * 
	 * @param provider
	 *        The provider to use.
	 */
	public BasicSetupResourceSettingSpecifier(SetupResourceProvider provider) {
		this(provider, null);
	}

	/**
	 * Constructor.
	 * 
	 * @param provider
	 *        The provider to use.
	 * @param properties
	 *        The properties to use.
	 */
	public BasicSetupResourceSettingSpecifier(SetupResourceProvider provider,
			Map<String, ?> properties) {
		super();
		this.provider = provider;
		this.props = properties;
	}

	@Override
	public SetupResourceProvider getSetupResourceProvider() {
		return provider;
	}

	@Override
	public Map<String, ?> getSetupResourceProperties() {
		return props;
	}

}
