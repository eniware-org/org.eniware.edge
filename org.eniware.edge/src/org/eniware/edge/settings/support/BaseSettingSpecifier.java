/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.settings.support;

import org.eniware.edge.settings.SettingSpecifier;

/**
 * Base implementation of {@link SettingSpecifier}.
 * 
 * @version $Revision$
 */
public abstract class BaseSettingSpecifier implements SettingSpecifier {

	private String title;

	@Override
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
