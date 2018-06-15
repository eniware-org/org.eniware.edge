/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.setup;

/**
 * A plugin version.
 * 
 * <p>
 * Purposely compatible with OSGi {@code org.osgi.framework.Version} class.
 * </p>
 * 
 * @version 1.0
 */
public interface PluginVersion extends Comparable<PluginVersion> {

	/**
	 * Returns the major component of this version identifier.
	 * 
	 * @return The major component.
	 */
	public int getMajor();

	/**
	 * Returns the minor component of this version identifier.
	 * 
	 * @return The minor component.
	 */
	public int getMinor();

	/**
	 * Returns the micro component of this version identifier.
	 * 
	 * @return The micro component.
	 */
	public int getMicro();

	/**
	 * Returns the qualifier component of this version identifier.
	 * 
	 * @return The qualifier component.
	 */
	public String getQualifier();

	/**
	 * Get a normalized string encoding of this version.
	 * 
	 * @return a string
	 */
	public String asNormalizedString();

}
