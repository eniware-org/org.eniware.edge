/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.setup.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.eniware.edge.setup.web.support.ServiceAwareController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.eniware.domain.EdgeControlInfo;
import org.eniware.edge.EdgeControlProvider;
import org.eniware.edge.reactor.InstructionHandler;
import org.eniware.edge.reactor.InstructionStatus;
import org.eniware.edge.reactor.support.BasicInstruction;

/**
 * Controller to act as a local Instructor to the local Edge.
 * 
 * @version 1.0
 */
@ServiceAwareController
@RequestMapping("/a/controls")
public class InstructorController {

	private static final String KEY_CONTROL_ID = "controlId";
	private static final String KEY_CONTROL_INFO = "info";
	private static final String KEY_CONTROL_IDS = "controlIds";

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Resource(name = "EdgeControlProviderList")
	private Collection<EdgeControlProvider> providers = Collections.emptyList();

	@Resource(name = "instructionHandlerList")
	private Collection<InstructionHandler> handlers = Collections.emptyList();

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String settingsList(ModelMap model) {
		List<String> providerIds = new ArrayList<String>();
		for ( EdgeControlProvider provider : providers ) {
			providerIds.addAll(provider.getAvailableControlIds());
		}
		model.put(KEY_CONTROL_IDS, providerIds);
		return "control/list";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manage(@RequestParam("id") String controlId, ModelMap model) {
		EdgeControlProvider provider = null;
		for ( EdgeControlProvider p : providers ) {
			for ( String s : p.getAvailableControlIds() ) {
				if ( s.equals(controlId) ) {
					provider = p;
					break;
				}
			}
			if ( provider != null ) {
				break;
			}
		}
		if ( provider != null ) {
			model.put(KEY_CONTROL_ID, controlId);
			EdgeControlInfo info = null;
			try {
				info = provider.getCurrentControlInfo(controlId);
			} catch ( RuntimeException e ) {
				log.warn("Error getting control {} info: {}", controlId, e.getMessage());
			}
			if ( info != null ) {
				model.put(KEY_CONTROL_INFO, info);
			}
		}
		return "control/manage";
	}

	@RequestMapping(value = "/setControlParameter", method = RequestMethod.POST)
	public String setControlParameter(SetControlParameterInstruction instruction, ModelMap model,
			HttpServletRequest request) {
		BasicInstruction instr = new BasicInstruction(InstructionHandler.TOPIC_SET_CONTROL_PARAMETER,
				new Date(), "LOCAL", "LOCAL", null);
		instr.addParameter(instruction.getControlId(), instruction.getParameterValue());
		InstructionStatus.InstructionState result = null;
		try {
			for ( InstructionHandler handler : handlers ) {
				if ( handler.handlesTopic(instr.getTopic()) ) {
					result = handler.processInstruction(instr);
				}
				if ( result != null ) {
					break;
				}
			}
		} catch ( RuntimeException e ) {
			log.error("Exception setting control parameter {} to {}", instruction.getControlId(),
					instruction.getParameterValue(), e);
		}
		if ( result == null ) {
			// nobody handled it!
			result = InstructionStatus.InstructionState.Declined;
		}
		String keyPrefix = (result == InstructionStatus.InstructionState.Completed ? "status" : "error");
		HttpSession session = request.getSession();
		session.setAttribute(keyPrefix + "MessageKey", "controls.manage.SetControlParameter.result");
		session.setAttribute(keyPrefix + "MessageParam0", result);
		return "redirect:/a/controls/manage?id=" + instruction.getControlId();
	}

	public void setProviders(Collection<EdgeControlProvider> providers) {
		this.providers = providers;
	}

	public void setHandlers(Collection<InstructionHandler> handlers) {
		this.handlers = handlers;
	}

}
