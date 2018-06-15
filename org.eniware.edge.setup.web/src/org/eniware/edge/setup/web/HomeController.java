/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.setup.web;

import static org.eniware.web.domain.Response.response;
import javax.annotation.Resource;

import org.eniware.edge.setup.web.support.ServiceAwareController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.eniware.edge.SystemService;
import org.eniware.util.OptionalService;
import org.eniware.web.domain.Response;

/**
 * Controller to manage the initial home screen.
 * 
 * @version 1.0
 * @since 1.23
 */
@ServiceAwareController
@RequestMapping("/a/home")
public class HomeController {

	@Resource(name = "systemService")
	private OptionalService<SystemService> systemService;

	/**
	 * Setup the home view.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String home() {
		return "a/home";
	}

	/**
	 * Restart or reboot EniwareEdge.
	 * 
	 * @param reboot
	 *        If {@code true} then call {@link SystemService#reboot()},
	 *        otherwise call {@link SystemService#exit(boolean)}
	 * @param saveState
	 *        Flag to pass to {@link SystemService#exit(boolean)}
	 * @return A status result.
	 */
	@RequestMapping(value = "/restart", method = RequestMethod.POST)
	@ResponseBody
	public Response<Boolean> restart(
			@RequestParam(name = "reboot", required = false, defaultValue = "false") final boolean reboot,
			@RequestParam(name = "saveState", required = false, defaultValue = "false") final boolean saveState) {
		final SystemService sysService = (systemService != null ? systemService.service() : null);
		if ( sysService == null ) {
			return new Response<Boolean>(false, null, "No service available", Boolean.FALSE);
		} else {
			if ( reboot ) {
				sysService.reboot();
			} else {
				sysService.exit(false);
			}
		}
		return response(Boolean.TRUE);
	}

}
