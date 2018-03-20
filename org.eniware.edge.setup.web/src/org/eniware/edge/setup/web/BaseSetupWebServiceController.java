/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.setup.web;

import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import net.solarnetwork.web.domain.Response;

/**
 * Base class for web service support.
 * 
 * @author matt
 * @version 1.0
 */
public class BaseSetupWebServiceController {

	/** A class-level logger. */
	protected final Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * Handle an {@link AuthenticationException}.
	 * 
	 * @param e
	 *        the exception
	 * @param response
	 *        the response
	 * @return an error response object
	 */
	@ExceptionHandler(AuthenticationException.class)
	@ResponseBody
	public Response<?> handleAuthorizationException(AuthenticationException e,
			HttpServletResponse response) {
		log.debug("AuthenticationException in {} controller: {}", getClass().getSimpleName(),
				e.getMessage());
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		return new Response<Object>(Boolean.FALSE, null, e.getMessage(), null);
	}

	/**
	 * Handle an {@link IllegalArgumentException}.
	 * 
	 * @param e
	 *        the exception
	 * @param response
	 *        the response
	 * @return an error response object
	 * @since 1.3
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseBody
	public Response<?> handleIllegalArgument(IllegalArgumentException e, HttpServletResponse response) {
		log.error("IllegalArgumentException in {} controller: {}", getClass().getSimpleName(),
				e.getMessage());
		return new Response<Object>(Boolean.FALSE, null, "Illegal argument: " + e.getMessage(), null);
	}

	/**
	 * Handle a {@link RuntimeException}.
	 * 
	 * @param e
	 *        the exception
	 * @param response
	 *        the response
	 * @return an error response object
	 */
	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public Response<?> handleRuntimeException(RuntimeException e, HttpServletResponse response) {
		log.error("RuntimeException in {} controller", getClass().getSimpleName(), e);
		return new Response<Object>(Boolean.FALSE, null, "Internal error", null);
	}

}
