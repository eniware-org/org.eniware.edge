/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.setup;

import org.eniware.edge.domain.NodeAppConfiguration;

import org.eniware.domain.NetworkAssociation;
import org.eniware.domain.NetworkAssociationDetails;
import org.eniware.domain.NetworkCertificate;
import org.eniware.domain.NetworkIdentity;

/**
 * API for node setup support.
 * 
 * @version 1.3
 */
public interface SetupService {

	/** Topic for when a network association has been accepted. */
	public static final String TOPIC_NETWORK_ASSOCIATION_ACCEPTED = "net/eniwarenetwork/node/setup/NETWORK_ASSOCIATION_ACCEPTED";

	/**
	 * Decode a EniwareNet verification code to determine the service that the
	 * node should register itself with.
	 * 
	 * @param verificationCode
	 *        The verification code supplied by EniwareNet to decode.
	 * @return details for the given EniwareNet host
	 * @throws InvalidVerificationCodeException
	 *         thrown if an error is encountered decoding the verification code.
	 */
	NetworkAssociationDetails decodeVerificationCode(String verificationCode)
			throws InvalidVerificationCodeException;

	/**
	 * Use the {@link NetworkIdentity} settings in the supplied
	 * <code>details</code> to retrieve the server identity, terms of service,
	 * security phrase, etc.
	 * 
	 * @param details
	 *        Contains the host details to determine where we retrieve the
	 *        association from
	 * @return the NetworkAssociation
	 */
	NetworkAssociation retrieveNetworkAssociation(NetworkAssociationDetails details);

	/**
	 * Associate this node with a EniwareNet central service, using details
	 * previously obtained via {@link #decodeVerificationCode(String)}.
	 * 
	 * @param details
	 *        the host details to associate with
	 * @return the resulting NetworkCertificate
	 * @throws SetupException
	 *         thrown if an error is encountered confirming the server
	 *         association
	 */
	NetworkCertificate acceptNetworkAssociation(NetworkAssociationDetails details) throws SetupException;

	/**
	 * Renew the node's active certificate. The node must already be associated
	 * before this method will work. The renewal will be processed in the
	 * future.
	 * 
	 * @param password
	 *        A password to encrypt the keystore with when passing to EniwareNet.
	 * @throws SetupException
	 *         if an error is encountered renewing the certificate
	 * @since 1.2
	 */
	void renewNetworkCertificate(String password) throws SetupException;

	/**
	 * Get the application configuration.
	 * 
	 * <p>
	 * The application configuration includes network service URLs returned by
	 * the EniwareUser and EniwareQuery applications.
	 * </p>
	 * 
	 * @return the app configuration, never {@code null}
	 * @since 1.3
	 */
	NodeAppConfiguration getAppConfiguration();

}
