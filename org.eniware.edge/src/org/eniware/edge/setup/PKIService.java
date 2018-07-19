/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.setup;

import java.security.cert.X509Certificate;
import org.eniware.support.CertificateException;

/**
 * API for managing the Edge's certificate infrastructure.
 * 
 * @version 1.1
 */
public interface PKIService {

	/**
	 * Save the trusted CA certificate.
	 * 
	 * <p>
	 * The Edge maintains a root CA certificate for the EniwareNet network it is
	 * associated with.
	 * </p>
	 * 
	 * @param cert
	 *        the certificate
	 * @throws CertificateException
	 *         if any certificate related error occurs
	 */
	void saveCACertificate(X509Certificate cert) throws CertificateException;

	/**
	 * Get the configured CA certificate.
	 * 
	 * @return the CA certificate, or <em>null</em> if not available
	 * @throws CertificateException
	 *         if any certificate related error occurs
	 */
	X509Certificate getCACertificate() throws CertificateException;

	/**
	 * Get the configured Edge certificate.
	 * 
	 * @return the Edge certificate, or <em>null</em> if not available
	 * @throws CertificateException
	 *         if any certificate related error occurs
	 */
	X509Certificate getEdgeCertificate() throws CertificateException;

	/**
	 * Check if the Edge's certificate is valid.
	 * 
	 * <p>
	 * The certificate is considered valid if it is signed by the given
	 * authority and its chain can be verified and it has not expired.
	 * </p>
	 * 
	 * @param issuerDN
	 *        the expected issuer subject DN
	 * 
	 * @return boolean <em>true</em> if considered valid
	 * @throws CertificateException
	 *         if any certificate related error occurs
	 */
	boolean isEdgeCertificateValid(String issuerDN) throws CertificateException;

	/**
	 * Generate a new public and private key pair, and a new self-signed
	 * certificate.
	 * 
	 * @param dn
	 *        the certificate subject DN
	 * @return the Certificate
	 * @throws CertificateException
	 *         if any certificate related error occurs
	 */
	X509Certificate generateEdgeSelfSignedCertificate(String dn) throws CertificateException;

	/**
	 * Generate a PKCS#10 certificate signing request (CSR) for the Edge's
	 * certificate.
	 * 
	 * @return the PEM-encoded CSR
	 * @throws CertificateException
	 *         if any certificate related error occurs
	 */
	String generateEdgePKCS10CertificateRequestString() throws CertificateException;

	/**
	 * Generate a PKCS#7 PEM encoding of the Edge's certificate.
	 * 
	 * @return the PEM-encoded certificate
	 * @throws CertificateException
	 *         if any certificate related error occurs
	 */
	String generateEdgePKCS7CertificateString() throws CertificateException;

	/**
	 * Generate a PKCS#7 PEM encoding of the Edge's certificate chain.
	 * 
	 * @return the PEM-encoded certificate chain
	 * @throws CertificateException
	 *         if any certificate related error occurs
	 */
	String generateEdgePKCS7CertificateChainString() throws CertificateException;

	/**
	 * Save a signed Edge certificate (previously created via
	 * {@link #generateEdgeSelfSignedCertificate(String)}).
	 * 
	 * <p>
	 * The issuer of the certificate must match the subject of the configured CA
	 * certificate, and the certificate's subject must match the existing Edge
	 * certificate's subject.
	 * </p>
	 * 
	 * @param certificateChain
	 *        the PKCS#7 signed certificate chain
	 * @throws CertificateException
	 *         if any certificate related error occurs
	 */
	void saveEdgeSignedCertificate(String certificateChain) throws CertificateException;

	/**
	 * Save a PKCS#12 keystore as the Edge's certificate.
	 * 
	 * <p>
	 * The keystore can contain either a single self-signed certificate or a
	 * signed certificate chain.
	 * </p>
	 * 
	 * @param keystore
	 *        the PKCS#12 keystore as a Base64 encoded string
	 * @param password
	 *        the keystore password
	 * @throws CertificateException
	 *         if any certificate related error occurs
	 */
	void savePKCS12Keystore(String keystore, String password) throws CertificateException;

	/**
	 * Generate a PKCS#12 keystore from the Edge's keystore, encrpyted with the
	 * given password.
	 * 
	 * @param password
	 *        The password to encrypt the keystore with.
	 * @return The generated keystore.
	 * @throws CertificateException
	 *         if any certificate related error occurs
	 * @since 1.1
	 */
	String generatePKCS12KeystoreString(String password) throws CertificateException;

}
