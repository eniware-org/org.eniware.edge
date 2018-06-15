/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.setup;

/**
 * Simple implementation of {@link PluginQuery}.
 * 
 * @version 1.0
 */
public class SimplePluginQuery implements PluginQuery {

	private boolean latestVersionOnly = true;
	private String simpleQuery = null;

	@Override
	public boolean isLatestVersionOnly() {
		return latestVersionOnly;
	}

	public void setLatestVersionOnly(boolean latestVersionOnly) {
		this.latestVersionOnly = latestVersionOnly;
	}

	@Override
	public String getSimpleQuery() {
		return simpleQuery;
	}

	public void setSimpleQuery(String simpleQuery) {
		this.simpleQuery = simpleQuery;
	}

}
