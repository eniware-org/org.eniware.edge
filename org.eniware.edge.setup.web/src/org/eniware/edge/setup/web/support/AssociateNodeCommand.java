/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.setup.web.support;

/**
 * A command object for associating a node with a SolarNet account.
 * 
 * @version 1.1
 */
public class AssociateNodeCommand {

	private String verificationCode;
	private String keystorePassword;

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String associationCode) {
		this.verificationCode = associationCode;
	}

	public String getKeystorePassword() {
		return keystorePassword;
	}

	public void setKeystorePassword(String keystorePassword) {
		this.keystorePassword = keystorePassword;
	}

}
