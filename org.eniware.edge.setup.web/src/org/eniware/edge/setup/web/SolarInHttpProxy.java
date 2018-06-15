/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.setup.web;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.eniware.edge.IdentityService;
import org.eniware.edge.SSLService;
import org.eniware.edge.support.HttpClientSupport;
import org.eniware.util.OptionalServiceTracker;

/**
 * Proxy HTTP requests to EniwareIn.
 * 
 * <p>
 * This is designed to be used by the Settings app, to support calling EniwareIn
 * web services without relying on the user's browser be configured to support
 * the EniwareIn X.509 certificate.
 * </p>
 * 
 * @version 1.1
 */
@Controller
public class EniwareInHttpProxy extends HttpClientSupport {

	private static final String[] DEFAULT_PROXY_HEADERS_IGNORE = new String[] {
			"strict-transport-security", "transfer-encoding" };

	private Set<String> proxyHeadersIgnore = new LinkedHashSet<String>(
			Arrays.asList(DEFAULT_PROXY_HEADERS_IGNORE));

	@Autowired
	public EniwareInHttpProxy(@Qualifier("identityService") IdentityService identityService,
			@Qualifier("sslService") OptionalServiceTracker<SSLService> sslService) {
		super();
		setIdentityService(identityService);
		setSslService(sslService);
	}

	/**
	 * Proxy an HTTP request to EniwareIn and return the result on a given HTTP
	 * response.
	 * 
	 * @param request
	 *        the request to proxy
	 * @param response
	 *        the response to return the proxy response to
	 * @throws IOException
	 *         if an IO error occurs
	 */
	@RequestMapping(value = { "/api/v1/sec/location", "/api/v1/sec/location/price",
			"/api/v1/sec/location/weather" }, method = RequestMethod.GET)
	public void proxy(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String context = request.getContextPath();
		String path = request.getRequestURI();
		if ( path.startsWith(context) ) {
			path = path.substring(context.length());
		}
		String query = request.getQueryString();
		String url = getIdentityService().getEniwareInBaseUrl() + path;
		if ( query != null ) {
			url += '?' + query;
		}
		String accept = request.getHeader("Accept");
		if ( accept == null ) {
			accept = ACCEPT_JSON;
		}
		try {
			URLConnection conn = getURLConnection(url, request.getMethod(), accept);
			if ( conn instanceof HttpURLConnection ) {
				final HttpURLConnection httpConn = (HttpURLConnection) conn;
				final Map<String, List<String>> headers = httpConn.getHeaderFields();
				log.debug("Proxying EniwareIn headers: {}", headers);
				for ( Map.Entry<String, List<String>> me : headers.entrySet() ) {
					final String headerName = me.getKey();
					if ( headerName == null || proxyHeadersIgnore.contains(headerName.toLowerCase()) ) {
						log.debug("Not proxying header {}", headerName);
						continue;
					}
					for ( String val : me.getValue() ) {
						response.addHeader(headerName, val);
					}
				}
				response.setStatus(httpConn.getResponseCode());
			}
			FileCopyUtils.copy(conn.getInputStream(), response.getOutputStream());
			response.flushBuffer();
		} catch ( IOException e ) {
			log.debug("Error proxying EniwareIn URL [{}]", url, e);
			response.sendError(502, "Problem communicating with EniwareIn: " + e.getMessage());
		}
	}

	/**
	 * Configure a set of HTTP headers to <b>not</b> proxy.
	 * 
	 * @param proxyHeadersIgnore
	 */
	public void setProxyHeadersIgnore(Set<String> proxyHeadersIgnore) {
		Set<String> ignores = null;
		if ( proxyHeadersIgnore != null ) {
			ignores = new LinkedHashSet<String>(proxyHeadersIgnore.size());
			for ( String ignore : proxyHeadersIgnore ) {
				if ( ignore == null ) {
					continue;
				}
				ignores.add(ignore.toLowerCase());
			}
		}
		this.proxyHeadersIgnore = proxyHeadersIgnore;
	}

}
