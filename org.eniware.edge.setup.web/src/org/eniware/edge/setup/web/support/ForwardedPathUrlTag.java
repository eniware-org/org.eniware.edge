/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.setup.web.support;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import org.springframework.web.servlet.tags.UrlTag;

/**
 * Extension of Spring's {@code UrlTag} to support automatically injecting a
 * reverse proxy forward path.
 * 
 * <p>
 * If the request has a HTTP {@literal X-Forwarded-Path} value, that will be set
 * as the prefix to the request {@code contextPath} onto the {@code context}
 * property of the tag automatically.
 * </p>
 * 
 * @version 1.0
 */
public class ForwardedPathUrlTag extends UrlTag {

	private static final long serialVersionUID = 5059647940462361128L;

	@Override
	public int doStartTagInternal() throws JspException {
		int result = super.doStartTagInternal();
		HttpServletRequest req = (HttpServletRequest) this.pageContext.getRequest();
		String forwardedPath = req.getHeader("X-Forwarded-Path");
		String contextPath = req.getContextPath();
		if ( forwardedPath != null && forwardedPath.startsWith("/") ) {
			StringBuilder buf = new StringBuilder(forwardedPath);
			if ( contextPath.length() > 0
					&& !(forwardedPath.endsWith("/") || contextPath.startsWith("/")) ) {
				buf.append('/');
			}
			buf.append(contextPath);
			contextPath = buf.toString();
		}

		// always set the context value; for tag caching
		setContext(contextPath);

		return result;
	}

}
