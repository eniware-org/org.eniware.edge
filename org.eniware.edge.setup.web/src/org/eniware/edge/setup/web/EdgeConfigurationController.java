/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.setup.web;

import static org.eniware.web.domain.Response.response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.eniware.edge.domain.EdgeAppConfiguration;
import org.eniware.edge.setup.SetupService;
import org.eniware.web.domain.Response;

/**
 * REST controller for Edge configuration.
 * 
 * 
 * @version 1.0
 */
@RequestMapping("/a")
@RestController
public class EdgeConfigurationController extends BaseSetupWebServiceController {

	private final SetupService setupService;

	@Autowired
	public EdgeConfigurationController(SetupService setupService) {
		super();
		this.setupService = setupService;
	}

	@RequestMapping(value = "/config", method = RequestMethod.GET)
	@ResponseBody
	public Response<EdgeAppConfiguration> getAppConfiguration() {
		return response(setupService.getAppConfiguration());
	}

}
