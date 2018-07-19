/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.setup.web.support;

/**
 * A command object for associating a Edge with a EniwareNet account.
 * 
 * @version 1.1
 */
public class AssociateEdgeCommand {

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
