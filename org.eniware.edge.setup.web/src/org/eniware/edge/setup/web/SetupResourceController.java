/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.setup.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import net.solarnetwork.node.setup.SetupResource;
import net.solarnetwork.node.setup.SetupResourceService;

/**
 * Controller for serving setup resources.
 * 
 * @author matt
 * @version 1.0
 */
@Controller
public class SetupResourceController extends BaseSetupWebServiceController {

	@Autowired
	private SetupResourceService resourceService;

	@RequestMapping({ "/rsrc/{id:.+}", "/a/rsrc/{id:.+}" })
	public void setupResource(@PathVariable("id") String id, WebRequest req, HttpServletResponse res)
			throws IOException {
		final SetupResource rsrc = resourceService.getSetupResource(id, req.getLocale());
		if ( rsrc == null ) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		if ( rsrc.getRequiredRoles() != null && !hasRequiredyRole(req, rsrc) ) {
			res.setStatus(HttpServletResponse.SC_FORBIDDEN);
			return;
		}
		if ( req.checkNotModified(rsrc.lastModified()) ) {
			return;
		}
		respondWithSetupResource(rsrc, res);
	}

	/**
	 * Handle an {@link IOException}.
	 * 
	 * @param e
	 *        the exception
	 * @param response
	 *        the response
	 */
	@ExceptionHandler(IOException.class)
	public void handleIOException(IOException e, HttpServletResponse res) {
		log.warn("IOException serving setup resource: {}", e.getMessage());
		res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	}

	private boolean hasRequiredyRole(WebRequest req, SetupResource rsrc) {
		Set<String> roles = rsrc.getRequiredRoles();
		if ( roles == null || roles.isEmpty() ) {
			return true;
		}
		for ( String role : roles ) {
			if ( req.isUserInRole(role) ) {
				return true;
			}
		}
		return false;
	}

	private void respondWithSetupResource(SetupResource rsrc, HttpServletResponse res)
			throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType(rsrc.getContentType()));
		if ( rsrc.lastModified() > 0 ) {
			headers.setLastModified(rsrc.lastModified());
		}
		if ( rsrc.contentLength() > 0 ) {
			headers.setContentLength(rsrc.contentLength());
		}
		if ( rsrc.getCacheMaximumSeconds() > 0 ) {
			headers.setCacheControl(CacheControl.maxAge(rsrc.getCacheMaximumSeconds(), TimeUnit.SECONDS)
					.getHeaderValue());
		}

		res.setStatus(HttpServletResponse.SC_OK);

		for ( Map.Entry<String, List<String>> me : headers.entrySet() ) {
			for ( String value : me.getValue() ) {
				res.addHeader(me.getKey(), value);
			}
		}

		FileCopyUtils.copy(rsrc.getInputStream(), res.getOutputStream());
	}

}
