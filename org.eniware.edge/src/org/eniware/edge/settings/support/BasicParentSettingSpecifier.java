/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.settings.support;

import java.util.Collections;
import java.util.List;

import org.eniware.edge.settings.ParentSettingSpecifier;
import org.eniware.edge.settings.SettingSpecifier;

/**
 * Basic implementation of {@link ParentSettingSpecifier}.
 * 
 * @version $Revision$
 */
public class BasicParentSettingSpecifier extends BaseSettingSpecifier implements
		ParentSettingSpecifier {

	private List<SettingSpecifier> childSettings;

	@Override
	public List<SettingSpecifier> getChildSettings() {
		return this.childSettings;
	}

	public void setChildSettings(List<SettingSpecifier> childSettings) {
		this.childSettings = Collections.unmodifiableList(childSettings);
	}

}
