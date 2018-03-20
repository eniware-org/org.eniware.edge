/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.setup.web;

import static net.solarnetwork.web.domain.Response.response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import net.solarnetwork.node.domain.NodeAppConfiguration;
import net.solarnetwork.node.setup.SetupService;
import net.solarnetwork.web.domain.Response;

/**
 * REST controller for node configuration.
 * 
 * 
 * @author matt
 * @version 1.0
 */
@RequestMapping("/a")
@RestController
public class NodeConfigurationController extends BaseSetupWebServiceController {

	private final SetupService setupService;

	@Autowired
	public NodeConfigurationController(SetupService setupService) {
		super();
		this.setupService = setupService;
	}

	@RequestMapping(value = "/config", method = RequestMethod.GET)
	@ResponseBody
	public Response<NodeAppConfiguration> getAppConfiguration() {
		return response(setupService.getAppConfiguration());
	}

}
