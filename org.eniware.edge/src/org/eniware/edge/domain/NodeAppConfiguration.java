/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.domain;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Bean for immutable node application configuration.
 * 
 * @author matt
 * @version 1.0
 * @since 1.53
 */
public class NodeAppConfiguration {

	private final long created;
	private final Map<String, String> networkServiceUrls;

	/**
	 * Default constructor.
	 */
	public NodeAppConfiguration() {
		super();
		this.created = 0; // make expired
		this.networkServiceUrls = Collections.emptyMap();
	}

	/**
	 * Constructor.
	 * 
	 * @param networkServiceUrls
	 *        the newtork service URLs
	 */
	public NodeAppConfiguration(Map<String, String> networkServiceUrls) {
		super();
		this.created = System.currentTimeMillis();
		this.networkServiceUrls = (networkServiceUrls == null || networkServiceUrls.isEmpty()
				? Collections.<String, String> emptyMap()
				: Collections.unmodifiableMap(new LinkedHashMap<String, String>(networkServiceUrls)));
	}

	/**
	 * Get the creation date.
	 * 
	 * @return the created date, as milliseconds since the epoch
	 */
	public long getCreated() {
		return created;
	}

	/**
	 * Get the network service URL mappings.
	 * 
	 * @return mapping of network service names to associated URLs
	 */
	public Map<String, String> getNetworkServiceUrls() {
		return networkServiceUrls;
	}

}
