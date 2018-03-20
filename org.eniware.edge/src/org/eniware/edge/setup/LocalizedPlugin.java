/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.setup;

import java.util.Locale;

/**
 * Localized version of {@link PluginInfo} so that calls to non-localized
 * JavaBean accessors return localized values.
 * 
 * @author matt
 * @version 1.0
 */
public class LocalizedPlugin implements Plugin {

	private final Plugin plugin;
	private final Locale locale;

	/**
	 * Construct with values.
	 * 
	 * @param plugin
	 *        the non-localized plugin to delegate to
	 * @param locale
	 *        the desired locale to use
	 */
	public LocalizedPlugin(Plugin plugin, Locale locale) {
		super();
		this.plugin = plugin;
		this.locale = locale;
	}

	@Override
	public String getUID() {
		return plugin.getUID();
	}

	@Override
	public PluginVersion getVersion() {
		return plugin.getVersion();
	}

	@Override
	public PluginInfo getInfo() {
		return new LocalizedPluginInfo(plugin.getInfo(), locale);
	}

	@Override
	public PluginInfo getLocalizedInfo(Locale otherLocale) {
		return plugin.getLocalizedInfo(otherLocale);
	}

	@Override
	public boolean isCoreFeature() {
		return plugin.isCoreFeature();
	}

}
