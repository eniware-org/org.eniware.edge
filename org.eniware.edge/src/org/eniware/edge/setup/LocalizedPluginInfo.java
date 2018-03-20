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
public class LocalizedPluginInfo implements PluginInfo {

	private final PluginInfo info;
	private final Locale locale;

	/**
	 * Construct a localized PluginInfo.
	 * 
	 * @param info
	 *        the non-localized info instance to delegate to
	 * @param locale
	 *        the desired locale to use
	 */
	public LocalizedPluginInfo(PluginInfo info, Locale locale) {
		super();
		this.info = info;
		this.locale = locale;
	}

	@Override
	public String getName() {
		return info.getLocalizedName(locale);
	}

	@Override
	public String getDescription() {
		return info.getLocalizedDescription(locale);
	}

	@Override
	public String getLocalizedName(Locale otherLocale) {
		return info.getLocalizedName(otherLocale);
	}

	@Override
	public String getLocalizedDescription(Locale otherLocale) {
		return info.getLocalizedDescription(otherLocale);
	}

}
