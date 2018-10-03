/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.ocpp.auth;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.eniware.edge.ocpp.Authorization;
import org.eniware.edge.ocpp.AuthorizationDao;
import org.eniware.edge.ocpp.AuthorizationManager;
import org.eniware.edge.ocpp.support.CentralSystemServiceFactorySupport;
import org.eniware.edge.settings.SettingSpecifier;
import org.eniware.edge.settings.support.BasicToggleSettingSpecifier;
import org.eniware.util.OptionalService;
import ocpp.v15.cs.AuthorizationStatus;
import ocpp.v15.cs.AuthorizeRequest;
import ocpp.v15.cs.AuthorizeResponse;

/**
 * Default implementation of {@link AuthorizationManager}. This implementation
 * supports local caching of ID tags.
 * 
 * @version 1.0
 */
public class DefaultAuthorizationManager extends CentralSystemServiceFactorySupport
		implements AuthorizationManager {

	private OptionalService<AuthorizationDao> authorizationDao;
	private boolean authorizeOnFailedCommunication = true;

	@Override
	public AuthorizationStatus authorize(String idTag) {
		Authorization auth = authorizationForTag(idTag);
		if ( isAuthorized(auth) ) {
			return auth.getStatus();
		} else if ( isCachedAuthorizationValid(auth) ) {
			// no need to validate with central system
			return auth.getStatus();
		}
		AuthorizeRequest req = new AuthorizeRequest();
		req.setIdTag(idTag);
		AuthorizeResponse res = null;
		try {
			res = getCentralSystem().service().authorize(req, getCentralSystem().chargeBoxIdentity());
		} catch ( RuntimeException e ) {
			log.warn("Exception authorizing {} with central system: {}", idTag, e.getMessage());
			if ( authorizeOnFailedCommunication ) {
				return AuthorizationStatus.ACCEPTED;
			}
			throw e;
		}
		if ( res != null && res.getIdTagInfo() != null ) {
			auth = new Authorization(idTag, res.getIdTagInfo());
			saveAuthorization(auth);
			return auth.getStatus();
		}
		return AuthorizationStatus.INVALID;
	}

	/**
	 * Return <em>true</em> if {@code auth} has an {@code expiryDate} whose date
	 * is in the future.
	 * 
	 * @param auth
	 *        The authorization to check (mey be <em>null</em>).
	 * @return Cached validity flag.
	 */
	private boolean isCachedAuthorizationValid(Authorization auth) {
		return (auth != null && auth.getExpiryDate() != null && auth.getExpiryDate()
				.toGregorianCalendar().getTimeInMillis() > System.currentTimeMillis());
	}

	private boolean isAuthorized(Authorization auth) {
		return (auth != null && auth.isAccepted());
	}

	private Authorization authorizationForTag(String idTag) {
		AuthorizationDao dao = (authorizationDao != null ? authorizationDao.service() : null);
		if ( dao == null ) {
			return null;
		}
		return dao.getAuthorization(idTag);
	}

	private void saveAuthorization(Authorization auth) {
		AuthorizationDao dao = (authorizationDao != null ? authorizationDao.service() : null);
		if ( dao == null ) {
			return;
		}
		dao.storeAuthorization(auth);
	}

	// SettingSpecifierProvider

	@Override
	public String getSettingUID() {
		return "org.eniware.edge.ocpp.auth";
	}

	@Override
	public String getDisplayName() {
		return "OCPP Authorization Manager";
	}

	@Override
	public List<SettingSpecifier> getSettingSpecifiers() {
		List<SettingSpecifier> results = super.getSettingSpecifiers();
		DefaultAuthorizationManager defaults = new DefaultAuthorizationManager();
		results.add(new BasicToggleSettingSpecifier("authorizeOnFailedCommunication",
				defaults.authorizeOnFailedCommunication));
		return results;
	}

	@Override
	protected String getInfoMessage(Locale locale) {
		AuthorizationDao dao = (authorizationDao != null ? authorizationDao.service() : null);
		if ( dao == null ) {
			return getMessageSource().getMessage("status.noDao", null, locale);
		}
		Map<AuthorizationStatus, Integer> statusCounts = dao.statusCounts();
		if ( statusCounts.size() < 1 ) {
			return getMessageSource().getMessage("status.none", null, locale);
		}
		StringBuilder buf = new StringBuilder();
		for ( Map.Entry<AuthorizationStatus, Integer> me : statusCounts.entrySet() ) {
			if ( buf.length() > 0 ) {
				buf.append("; ");
			}
			buf.append(me.getKey()).append(": ").append(me.getValue());
		}
		return getMessageSource().getMessage("status.counts", new Object[] { buf.toString() }, locale);
	}

	public OptionalService<AuthorizationDao> getAuthorizationDao() {
		return authorizationDao;
	}

	public void setAuthorizationDao(OptionalService<AuthorizationDao> authorizationDao) {
		this.authorizationDao = authorizationDao;
	}

	/**
	 * Get the authorize on communication failure setting.
	 * 
	 * @return The setting value. Defaults to <em>true</em>.
	 */
	public boolean isAuthorizeOnFailedCommunication() {
		return authorizeOnFailedCommunication;
	}

	/**
	 * Setting to allow authorization if there is a problem communicating with
	 * the central system.
	 * 
	 * @param authorizeOnFailedCommunication
	 *        <em>true</em> to authorize on communication failures,
	 *        <em>false</em> to reject
	 */
	public void setAuthorizeOnFailedCommunication(boolean authorizeOnFailedCommunication) {
		this.authorizeOnFailedCommunication = authorizeOnFailedCommunication;
	}

}
