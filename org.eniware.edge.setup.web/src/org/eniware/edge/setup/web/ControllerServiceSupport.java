/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.setup.web;

import javax.annotation.Resource;

import org.eniware.edge.setup.web.support.ServiceAwareController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.eniware.edge.IdentityService;
import org.eniware.edge.SystemService;
import org.eniware.util.OptionalService;

/**
 * Add global services to all MVC controllers.
 * 
 * @version 1.1
 * @since 1.23
 */
@ControllerAdvice(annotations = { ServiceAwareController.class })
public class ControllerServiceSupport {

	/** The model attribute name for the {@code SystemService}. */
	public static final String SYSTEM_SERVICE_ATTRIBUTE = "systemService";

	/** The model attribute name for the {@code IdentityService}. */
	public static final String IDENTITY_SERVICE_ATTRIBUTE = "identityService";

	@Resource(name = "systemService")
	private OptionalService<SystemService> systemService;

	@Autowired
	private IdentityService identityService;

	@ModelAttribute(value = SYSTEM_SERVICE_ATTRIBUTE)
	public SystemService systemService() {
		final SystemService sysService = (systemService != null ? systemService.service() : null);
		return sysService;
	}

	/**
	 * The {@link IdentityService}.
	 * 
	 * @return the identity service
	 * @since 1.1
	 */
	@ModelAttribute(value = IDENTITY_SERVICE_ATTRIBUTE)
	public IdentityService identityService() {
		return identityService;
	}

}
