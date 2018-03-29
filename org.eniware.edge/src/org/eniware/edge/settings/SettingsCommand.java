/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */
package org.eniware.edge.settings;

import java.util.ArrayList;
import java.util.List;

/**
 * Edit settings command object.
 * 
 * @version $Revision$
 */
public class SettingsCommand {

	private String providerKey;
	private String instanceKey;

	private List<SettingValueBean> values = new ArrayList<SettingValueBean>();

	public List<SettingValueBean> getValues() {
		return values;
	}

	public void setValues(List<SettingValueBean> values) {
		this.values = values;
	}

	public String getProviderKey() {
		return providerKey;
	}

	public void setProviderKey(String providerKey) {
		this.providerKey = providerKey;
	}

	public String getInstanceKey() {
		return instanceKey;
	}

	public void setInstanceKey(String instanceKey) {
		this.instanceKey = instanceKey;
	}

}
