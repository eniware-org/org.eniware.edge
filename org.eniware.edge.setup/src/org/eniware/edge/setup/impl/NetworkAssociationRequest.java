/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */
package org.eniware.edge.setup.impl;

/**
 * Request bean for the NetworkAssociation service.
 * 
 * @author matt
 * @version 1.0
 */
public class NetworkAssociationRequest {

	private String username;
	private String key;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
