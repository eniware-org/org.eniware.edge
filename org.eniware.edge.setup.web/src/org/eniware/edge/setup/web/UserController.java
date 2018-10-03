/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.setup.web;

import org.eniware.edge.setup.web.support.ServiceAwareController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.eniware.edge.setup.UserProfile;
import org.eniware.edge.setup.UserService;
import org.eniware.web.domain.Response;

/**
 * Controller for user related tasks.
 * 
 * @version 1.1
 */
@ServiceAwareController
@RequestMapping("/a/user")
public class UserController extends BaseSetupWebServiceController {

	@Autowired
	private UserService userService;

	/**
	 * Render the change password form.
	 * 
	 * @param oldPassword
	 *        An optional old password to pre-populate in the form.
	 * @param model
	 *        The model object.
	 * @return The view name to render.
	 */
	@RequestMapping(value = "/change-password", method = RequestMethod.GET)
	public String changePasswordForm(@RequestParam(value = "old", required = false) String oldPassword,
			Model model) {
		UserProfile user = new UserProfile();
		if ( oldPassword != null ) {
			user.setOldPassword(oldPassword);
		}
		model.addAttribute("user", user);
		return "user/change-password";
	}

	/**
	 * Change the active user's password.
	 * 
	 * @param existingPassword
	 *        The existing password.
	 * @param newPassword
	 *        The new password to set.
	 * @param newPasswordAgain
	 *        The new password, repeated.
	 * @return An empty response on success.
	 */
	@RequestMapping(value = "/change-password", method = RequestMethod.POST)
	@ResponseBody
	public Response<Object> changePassword(UserProfile userProfile) {
		userService.changePassword(userProfile.getOldPassword(), userProfile.getPassword(),
				userProfile.getPasswordAgain());
		return Response.response(null);
	}

	/**
	 * Render the change username form.
	 * 
	 * @return The view name to render.
	 */
	@RequestMapping(value = "/change-username", method = RequestMethod.GET)
	public String changeUsernameForm() {
		return "user/change-username";
	}

	/**
	 * Change the active user's password.
	 * 
	 * @param userProfile
	 *        The new profile to set.
	 * @return An empty response on success.
	 */
	@RequestMapping(value = "/change-username", method = RequestMethod.POST)
	@ResponseBody
	public Response<Object> changeUsername(@RequestParam("username") String username,
			@RequestParam("usernameAgain") String usernameAgain) {
		userService.changeUsername(username, usernameAgain);
		return Response.response(null);
	}

}
