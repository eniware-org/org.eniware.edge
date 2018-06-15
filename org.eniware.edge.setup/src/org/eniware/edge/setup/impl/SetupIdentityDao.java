/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.setup.impl;

/**
 * API for accessing the singleton {@link SetupIdentityInfo}.
 * 
 * @version 1.0
 */
public interface SetupIdentityDao {

	/**
	 * Get the current identity.
	 * 
	 * @return the current identity, never {@literal null}
	 */
	SetupIdentityInfo getSetupIdentityInfo();

	void saveSetupIdentityInfo(SetupIdentityInfo info);

}
