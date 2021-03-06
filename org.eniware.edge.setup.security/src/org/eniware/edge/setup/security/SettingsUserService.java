/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.setup.security;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicBoolean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.eniware.edge.IdentityService;
import org.eniware.edge.Setting;
import org.eniware.edge.dao.BasicBatchOptions;
import org.eniware.edge.dao.BatchableDao.BatchCallback;
import org.eniware.edge.dao.BatchableDao.BatchCallbackResult;
import org.eniware.edge.dao.SettingDao;
import org.eniware.edge.setup.UserProfile;
import org.eniware.edge.setup.UserService;

/**
 * {@link UserDetailsService} that uses {@link SettingDao} for users and roles.
 * 
 * @version 1.0
 */
public class SettingsUserService implements UserService, UserDetailsService {

	public static final String SETTING_TYPE_USER = "eniwareedge.user";
	public static final String SETTING_TYPE_ROLE = "eniwareedge.role";
	public static final String GRANTED_AUTH_USER = "ROLE_USER";

	private final SettingDao settingDao;
	private final IdentityService identityService;
	private final PasswordEncoder passwordEncoder;

	/**
	 * Constructor.
	 * 
	 * @param settingDao
	 *        The setting DAO to use.
	 * @param identityService
	 *        The {@link IdentityService} to use.
	 * @param passwordEncoder
	 *        The {@link PasswordEncoder} to use for legacy user support.
	 */
	public SettingsUserService(SettingDao settingDao, IdentityService identityService,
			PasswordEncoder passwordEncoder) {
		super();
		this.settingDao = settingDao;
		this.identityService = identityService;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails result = null;
		String password = settingDao.getSetting(username, SETTING_TYPE_USER);
		if ( password == null && identityService != null && passwordEncoder != null
				&& !someUserExists() ) {
			// for backwards-compat with Edges created before user auth, provide a default
			Long EdgeId = identityService.getEdgeId();
			if ( EdgeId != null && EdgeId.toString().equalsIgnoreCase(username) ) {
				password = passwordEncoder.encode("eniware");
				GrantedAuthority auth = new SimpleGrantedAuthority(GRANTED_AUTH_USER);
				result = new User(username, password, Collections.singleton(auth));
			}
		} else if ( password != null ) {
			Collection<GrantedAuthority> auths;
			String role = settingDao.getSetting(username, SETTING_TYPE_ROLE);
			if ( role != null ) {
				GrantedAuthority auth = new SimpleGrantedAuthority(role);
				auths = Collections.singleton(auth);
			} else {
				auths = Collections.emptySet();
			}
			result = new User(username, password, auths);
		}
		if ( result == null ) {
			throw new UsernameNotFoundException(username);
		}
		return result;
	}

	@Override
	public boolean someUserExists() {
		final AtomicBoolean result = new AtomicBoolean(false);
		settingDao.batchProcess(new BatchCallback<Setting>() {

			@Override
			public BatchCallbackResult handle(Setting domainObject) {
				if ( domainObject.getType().equals(SETTING_TYPE_USER) ) {
					result.set(true);
					return BatchCallbackResult.STOP;
				}
				return BatchCallbackResult.CONTINUE;
			}
		}, new BasicBatchOptions("FindUser"));
		return result.get();
	}

	/**
	 * Update the active user's password.
	 * 
	 * @param existingPassword
	 *        The existing password.
	 * @param newPassword
	 *        The new password to set.
	 * @param newPasswordAgain
	 *        The new password, repeated.
	 * @throws InsufficientAuthenticationException
	 *         If an active user is not available.
	 * @throws UsernameNotFoundException
	 *         If the active user cannot be found in settings.
	 * @throws BadCredentialsException
	 *         If {@code existingPassword} does not match the password in
	 *         settings.
	 * @throws IllegalArgumentException
	 *         if the {@code newPassword} and {@code newPasswordAgain} values do
	 *         not match, or are <em>null</em>
	 */
	@Override
	public void changePassword(final String existingPassword, final String newPassword,
			final String newPasswordAgain) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails activeUser = (auth == null ? null : (UserDetails) auth.getPrincipal());
		if ( activeUser == null ) {
			throw new InsufficientAuthenticationException("Active user not found.");
		}
		UserDetails dbUser = loadUserByUsername(activeUser.getUsername());
		if ( dbUser == null ) {
			throw new UsernameNotFoundException("User not found");
		}
		if ( passwordEncoder != null ) {
			if ( !passwordEncoder.matches(existingPassword, dbUser.getPassword()) ) {
				throw new BadCredentialsException("Existing password does not match.");
			}
		} else if ( !existingPassword.equals(dbUser.getPassword()) ) {
			throw new BadCredentialsException("Existing password does not match.");
		}
		if ( newPassword == null || newPasswordAgain == null || !newPassword.equals(newPasswordAgain) ) {
			throw new IllegalArgumentException(
					"New password not provided or does not match repeated password.");
		}
		String password;
		if ( passwordEncoder != null ) {
			password = passwordEncoder.encode(newPassword);
		} else {
			password = newPassword;
		}
		settingDao.storeSetting(dbUser.getUsername(), SETTING_TYPE_USER, password);
		settingDao.storeSetting(dbUser.getUsername(), SETTING_TYPE_ROLE, GRANTED_AUTH_USER);
	}

	@Override
	public void changeUsername(final String newUsername, final String newUsernameAgain) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails activeUser = (auth == null ? null : (UserDetails) auth.getPrincipal());
		if ( activeUser == null ) {
			throw new InsufficientAuthenticationException("Active user not found.");
		}
		final UserDetails dbUser = loadUserByUsername(activeUser.getUsername());
		if ( dbUser == null ) {
			throw new UsernameNotFoundException("User not found");
		}
		if ( newUsername == null || newUsernameAgain == null || !newUsername.equals(newUsernameAgain) ) {
			throw new IllegalArgumentException(
					"New username not provided or does not match repeated username.");
		}
		final AtomicBoolean updatedUsername = new AtomicBoolean(false);
		final AtomicBoolean updatedRole = new AtomicBoolean(false);
		settingDao.batchProcess(new BatchCallback<Setting>() {

			@Override
			public BatchCallbackResult handle(Setting domainObject) {
				if ( domainObject.getType().equals(SETTING_TYPE_USER)
						&& domainObject.getKey().equals(dbUser.getUsername()) ) {
					updatedUsername.set(true);
					domainObject.setKey(newUsername);
					return (updatedRole.get() ? BatchCallbackResult.UPDATE_STOP
							: BatchCallbackResult.UPDATE);
				} else if ( domainObject.getType().equals(SETTING_TYPE_ROLE)
						&& domainObject.getKey().equals(dbUser.getUsername()) ) {
					updatedRole.set(true);
					domainObject.setKey(newUsername);
					return (updatedUsername.get() ? BatchCallbackResult.UPDATE_STOP
							: BatchCallbackResult.UPDATE);
				}
				return BatchCallbackResult.CONTINUE;
			}
		}, new BasicBatchOptions("UpdateUser", BasicBatchOptions.DEFAULT_BATCH_SIZE, true, null));
		if ( !updatedUsername.get() ) {
			// no username exists, treat as a legacy Edge whose password was "eniware"
			UserProfile newProfile = new UserProfile();
			newProfile.setUsername(newUsername);
			newProfile.setPassword("eniware");
			newProfile.setPasswordAgain("eniware");
			storeUserProfile(newProfile);
		}

		// update active user details to new usenrame
		User newUser = new User(newUsername, "",
				Collections.singleton(new SimpleGrantedAuthority(GRANTED_AUTH_USER)));
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
				newUser, null, newUser.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	@Override
	public void storeUserProfile(UserProfile profile) {
		if ( profile.getUsername() == null || profile.getPassword() == null
				|| !profile.getPassword().equals(profile.getPasswordAgain()) ) {
			throw new IllegalArgumentException(
					"Username, password, and repeated password must be provided.");
		}

		String password;
		if ( passwordEncoder != null ) {
			password = passwordEncoder.encode(profile.getPassword());
		} else {
			password = profile.getPassword();
		}
		settingDao.storeSetting(profile.getUsername(), SETTING_TYPE_USER, password);
		settingDao.storeSetting(profile.getUsername(), SETTING_TYPE_ROLE, GRANTED_AUTH_USER);
	}

	/**
	 * Get the configured {@link SettingDao}.
	 * 
	 * @return The DAO.
	 */
	public SettingDao getSettingDao() {
		return settingDao;
	}

	/**
	 * Get the configured password encoder.
	 * 
	 * @return The password encoder.
	 */
	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

}
