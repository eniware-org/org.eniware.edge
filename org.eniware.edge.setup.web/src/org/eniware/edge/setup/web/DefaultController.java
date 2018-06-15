/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.setup.web;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Default controller.
 * 
 * @version 1.1
 */
@Controller
public class DefaultController {

	@RequestMapping({ "/", "/hello" })
	public String hello(Principal principal) {
		return (principal == null ? "home" : "a/home");
	}

}
