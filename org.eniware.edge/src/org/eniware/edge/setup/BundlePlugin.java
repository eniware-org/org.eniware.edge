/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.setup;

import java.util.Locale;
import org.eniware.util.SerializeIgnore;
import org.osgi.framework.Bundle;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Implementation of {@link Plugin} that wraps a {@link Bundle}.
 * 
 * @version 1.0
 */
public class BundlePlugin implements Plugin {

	private final Bundle bundle;
	private final BundlePluginVersion version;
	private final BundlePluginInfo info;
	private final boolean coreFeature;

	/**
	 * Construct with a {@link Bundle}.
	 * 
	 * @param bundle
	 *        the bundle
	 */
	public BundlePlugin(Bundle bundle, boolean coreFeature) {
		super();
		this.bundle = bundle;
		this.coreFeature = coreFeature;
		this.version = new BundlePluginVersion(bundle.getVersion());
		this.info = new BundlePluginInfo(bundle);
	}

	@Override
	public String getUID() {
		return bundle.getSymbolicName();
	}

	@Override
	public PluginVersion getVersion() {
		return version;
	}

	@Override
	public PluginInfo getInfo() {
		return info;
	}

	@Override
	public PluginInfo getLocalizedInfo(Locale locale) {
		return new LocalizedPluginInfo(info, locale);
	}

	@Override
	public boolean isCoreFeature() {
		return coreFeature;
	}

	/**
	 * Get the Bundle associated with this plugin.
	 * 
	 * @return the Bundle
	 */
	@JsonIgnore
	@SerializeIgnore
	public Bundle getBundle() {
		return bundle;
	}

}
