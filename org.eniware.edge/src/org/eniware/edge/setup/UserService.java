/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */
package org.eniware.edge.setup;

/**
 * API for managing users and roles on the SolarNode system.
 * 
 * @version 1.0
 * @since 1.48
 */
public interface UserService {

	/**
	 * Test if any user exists.
	 * 
	 * @return <em>true</em> if some user exists
	 */
	public boolean someUserExists();

	/**
	 * Update the active user's password.
	 * 
	 * @param existingPassword
	 *        The existing password.
	 * @param newPassword
	 *        The new password to set.
	 * @param newPasswordAgain
	 *        The new password, repeated.
	 * @throws IllegalArgumentException
	 *         if the {@code newPassword} and {@code newPasswordAgain} values do
	 *         not match, or are <em>null</em>
	 */
	public void changePassword(final String existingPassword, final String newPassword,
			final String newPasswordAgain);

	/**
	 * Update the active user's username.
	 * 
	 * @param newUsername
	 *        The new username to set.
	 * @param newUsernameAgain
	 *        The new username, repeated.
	 * @throws IllegalArgumentException
	 *         if the {@code newUsername} and {@code newUsernameAgain} values do
	 *         not match, or are <em>null</em>
	 */
	public void changeUsername(String newUsername, String newUsernameAgain);

	/**
	 * Store a user profile into settings.
	 * 
	 * @param profile
	 *        The profile to store.
	 * @throws IllegalArgumentException
	 *         if {@code username} is <em>null</em>, or if the {@code password}
	 *         and {@code passwordAgain} values do not match or are
	 *         <em>null</em>
	 */
	public void storeUserProfile(UserProfile profile);

}
