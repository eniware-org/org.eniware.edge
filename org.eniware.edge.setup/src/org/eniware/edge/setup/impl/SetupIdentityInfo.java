/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.setup.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Information about a EniwareEdge setup identity.
 * 
 * @version 1.0
 */
public class SetupIdentityInfo {

	/**
	 * An identity for when there is no real one availalble.
	 */
	public static final SetupIdentityInfo UNKNOWN_IDENTITY = new SetupIdentityInfo(null, null, null,
			null, false, null);

	private final Long nodeId;
	private final String confirmationCode;
	private final String eniwareNetHostName;
	private final Integer eniwareNetHostPort;
	private final boolean eniwareNetForceTls;
	private final String keyStorePassword;

	/**
	 * Constructor.
	 * 
	 * @param nodeId
	 *        the node ID
	 * @param confirmationCode
	 *        the EniwareNetwork association confirmation code
	 * @param eniwareNetHostName
	 *        the EniwareNetwork host name
	 * @param eniwareNetHostPort
	 *        the EniwareNetwork host port
	 * @param eniwareNetForceTls
	 *        {@literal true} to force TLS when {@code port} is not
	 *        {@literal 443}
	 * @param keyStorePassword
	 *        the password to use for the key store
	 */
	@JsonCreator
	public SetupIdentityInfo(@JsonProperty("nodeId") Long nodeId,
			@JsonProperty("confirmationCode") String confirmationCode,
			@JsonProperty("eniwareNetHostName") String eniwareNetHostName,
			@JsonProperty("eniwareNetHostPort") Integer eniwareNetHostPort,
			@JsonProperty("eniwareNetForceTls") boolean eniwareNetForceTls,
			@JsonProperty("keyStorePassword") String keyStorePassword) {
		super();
		this.nodeId = nodeId;
		this.confirmationCode = confirmationCode;
		this.eniwareNetHostName = eniwareNetHostName;
		this.eniwareNetHostPort = eniwareNetHostPort;
		this.eniwareNetForceTls = eniwareNetForceTls;
		this.keyStorePassword = keyStorePassword;
	}

	/**
	 * Instantiate a new info instance with a specific key store password.
	 * 
	 * @param newPassword
	 *        the new password
	 * @return the new instance
	 */
	public SetupIdentityInfo withKeyStorePassword(String newPassword) {
		return new SetupIdentityInfo(nodeId, confirmationCode, eniwareNetHostName, eniwareNetHostPort,
				eniwareNetForceTls, newPassword);
	}

	@Override
	public String toString() {
		return "SetupIdentityInfo{" + nodeId + "@" + eniwareNetHostName + ":" + eniwareNetHostPort + "}";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((confirmationCode == null) ? 0 : confirmationCode.hashCode());
		result = prime * result + ((keyStorePassword == null) ? 0 : keyStorePassword.hashCode());
		result = prime * result + ((nodeId == null) ? 0 : nodeId.hashCode());
		result = prime * result + (eniwareNetForceTls ? 1231 : 1237);
		result = prime * result + ((eniwareNetHostName == null) ? 0 : eniwareNetHostName.hashCode());
		result = prime * result + ((eniwareNetHostPort == null) ? 0 : eniwareNetHostPort.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if ( this == obj ) {
			return true;
		}
		if ( obj == null ) {
			return false;
		}
		if ( !(obj instanceof SetupIdentityInfo) ) {
			return false;
		}
		SetupIdentityInfo other = (SetupIdentityInfo) obj;
		if ( confirmationCode == null ) {
			if ( other.confirmationCode != null ) {
				return false;
			}
		} else if ( !confirmationCode.equals(other.confirmationCode) ) {
			return false;
		}
		if ( keyStorePassword == null ) {
			if ( other.keyStorePassword != null ) {
				return false;
			}
		} else if ( !keyStorePassword.equals(other.keyStorePassword) ) {
			return false;
		}
		if ( nodeId == null ) {
			if ( other.nodeId != null ) {
				return false;
			}
		} else if ( !nodeId.equals(other.nodeId) ) {
			return false;
		}
		if ( eniwareNetForceTls != other.eniwareNetForceTls ) {
			return false;
		}
		if ( eniwareNetHostName == null ) {
			if ( other.eniwareNetHostName != null ) {
				return false;
			}
		} else if ( !eniwareNetHostName.equals(other.eniwareNetHostName) ) {
			return false;
		}
		if ( eniwareNetHostPort == null ) {
			if ( other.eniwareNetHostPort != null ) {
				return false;
			}
		} else if ( !eniwareNetHostPort.equals(other.eniwareNetHostPort) ) {
			return false;
		}
		return true;
	}

	public Long getNodeId() {
		return nodeId;
	}

	public String getConfirmationCode() {
		return confirmationCode;
	}

	public String getEniwareNetHostName() {
		return eniwareNetHostName;
	}

	public Integer getEniwareNetHostPort() {
		return eniwareNetHostPort;
	}

	public boolean isEniwareNetForceTls() {
		return eniwareNetForceTls;
	}

	public String getKeyStorePassword() {
		return keyStorePassword;
	}

}
