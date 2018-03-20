/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */
package org.eniware.edge.setup;

import java.util.Locale;
import org.osgi.framework.Bundle;

/**
 * PluginInfo implementation that wraps a {@link Bundle}.
 * 
 * @author matt
 * @version 1.0
 */
public class BundlePluginInfo implements PluginInfo {

	private final Bundle bundle;

	/**
	 * Construct with a Bundle.
	 * 
	 * @param bundle
	 *        the bundle
	 */
	public BundlePluginInfo(Bundle bundle) {
		super();
		this.bundle = bundle;
	}

	@Override
	public String getName() {
		return bundle.getHeaders().get("Bundle-Name");
	}

	@Override
	public String getDescription() {
		return bundle.getHeaders().get("Bundle-Description");
	}

	@Override
	public String getLocalizedName(Locale locale) {
		// TODO l10n
		return getName();
	}

	@Override
	public String getLocalizedDescription(Locale locale) {
		// TODO l10n
		return getDescription();
	}

}
