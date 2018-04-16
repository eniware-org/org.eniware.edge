/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.setup.web.support;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.taglibs.standard.util.EscapeXML;
import org.eniware.edge.settings.SettingSpecifier;
import org.eniware.edge.settings.SettingSpecifierProvider;
import org.eniware.edge.settings.SettingsService;

/**
 * Expose the current value of a setting.
 * 
 * @version 1.1
 */
public class SettingValueTag extends TagSupport {

	private static final long serialVersionUID = 3625222283623204656L;

	private SettingsService service;
	private SettingSpecifierProvider provider;
	private SettingSpecifier setting;
	private boolean escapeXml = true;

	@Override
	public int doEndTag() throws JspException {
		assert service != null;
		assert provider != null;
		assert setting != null;
		if ( service == null || provider == null || setting == null ) {
			return EVAL_PAGE;
		}
		Object val = service.getSettingValue(provider, setting);
		if ( val != null ) {
			try {
				EscapeXML.emit(val, escapeXml, pageContext.getOut());
			} catch ( IOException e ) {
				throw new JspException(e);
			}
		}
		return EVAL_PAGE;
	}

	public void setService(SettingsService service) {
		this.service = service;
	}

	public void setProvider(SettingSpecifierProvider provider) {
		this.provider = provider;
	}

	public void setSetting(SettingSpecifier setting) {
		this.setting = setting;
	}

	public boolean isEscapeXml() {
		return escapeXml;
	}

	public void setEscapeXml(boolean escapeXml) {
		this.escapeXml = escapeXml;
	}

}
