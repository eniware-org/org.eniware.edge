/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.setup;

import org.osgi.framework.Version;

/**
 * PluginVersion implementation that wraps an OSGi {@link Version}.
 * 
 * @version 1.0
 */
public class BundlePluginVersion implements PluginVersion {

	private final Version version;

	/**
	 * Construct with a {@link Version}.
	 * 
	 * @param version
	 *        the Version to wrap
	 */
	public BundlePluginVersion(Version version) {
		super();
		this.version = version;
	}

	@Override
	public int compareTo(PluginVersion o) {
		if ( !(o instanceof BundlePluginVersion) ) {
			throw new IllegalArgumentException("Only BundlePluginVersion supported");
		}
		return this.version.compareTo(((BundlePluginVersion) o).version);
	}

	@Override
	public int getMajor() {
		return version.getMajor();
	}

	@Override
	public int getMinor() {
		return version.getMinor();
	}

	@Override
	public int getMicro() {
		return version.getMicro();
	}

	@Override
	public String getQualifier() {
		return version.getQualifier();
	}

	@Override
	public String asNormalizedString() {
		return version.toString();
	}

	@Override
	public String toString() {
		return asNormalizedString();
	}
}
