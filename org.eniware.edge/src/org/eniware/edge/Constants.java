/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge;

/**
 * EniwareEdge constants.
 * 
 * @version 1.1
 */
public final class Constants {

	/** The system property for the Edge's home directory. */
	public static final String SYSTEM_PROP_Edge_HOME = "sn.home";

	/**
	 * An event topic to post when a significant change has occurred to the
	 * system's configuration. An example of a listener interested in such an
	 * event would be an automatic backup service.
	 * 
	 * @since 1.1
	 */
	public static final String EVENT_TOPIC_CONFIGURATION_CHANGED = "net/eniwarenetwork/Edge/CONFIGURATION_CHANGED";

	private Constants() {
		// don't construct me
	}

}
