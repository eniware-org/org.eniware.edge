/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */
package org.eniware.edge;

import java.util.List;
import org.eniware.domain.EdgeControlInfo;

/**
 * API for control providers to implement.
 * 
 * For many control providers that implement a single control, the
 * {@link Identifiable#getUID()} will return the <em>control ID</em> of that
 * control.
 * 
 * @version 1.3
 */
public interface EdgeControlProvider extends Identifiable {

	/**
	 * An {@link org.osgi.service.event.Event} topic for when a
	 * {@link EdgeControlInfo} has been read, sampled, or in some way captured
	 * by a {@link EdgeControlProvider}. The properties of the event shall be
	 * any of the JavaBean properties of the EdgeControlInfo supported by events
	 * (i.e. any simple Java property such as numbers and strings) with enums
	 * provided as strings.
	 * 
	 * @since 1.2
	 */
	String EVENT_TOPIC_CONTROL_INFO_CAPTURED = "net/eniwarenetwork/Edge/EdgeControlProvider/CONTROL_INFO_CAPTURED";

	/**
	 * An {@code org.osgi.service.event.Event} topic for when a
	 * {@code EdgeControlInfo} has in some way been changed. The properties of
	 * the event shall be any of the JavaBean properties of the EdgeControlInfo
	 * supported by events (i.e. any simple Java property such as numbers and
	 * strings) with enums provided as strings.
	 * 
	 * @since 1.3
	 */
	String EVENT_TOPIC_CONTROL_INFO_CHANGED = "net/eniwarenetwork/Edge/EdgeControlProvider/CONTROL_INFO_CHANGED";

	/**
	 * Get a list of available controls this provider supports.
	 * 
	 * @return the components
	 */
	List<String> getAvailableControlIds();

	/**
	 * Get the current instantaneous component value for the given component ID.
	 * 
	 * @param controlId
	 *        the ID of the control to get the info for
	 * @return the current value
	 */
	EdgeControlInfo getCurrentControlInfo(String controlId);

}
