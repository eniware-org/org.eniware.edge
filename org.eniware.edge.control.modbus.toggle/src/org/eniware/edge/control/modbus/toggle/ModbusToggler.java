/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.control.modbus.toggle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.springframework.context.MessageSource;
import org.eniware.domain.EdgeControlInfo;
import org.eniware.domain.EdgeControlPropertyType;
import org.eniware.edge.EdgeControlProvider;
import org.eniware.edge.domain.EdgeControlInfoDatum;
import org.eniware.edge.io.modbus.ModbusConnection;
import org.eniware.edge.io.modbus.ModbusConnectionAction;
import org.eniware.edge.io.modbus.ModbusDeviceSupport;
import org.eniware.edge.io.modbus.ModbusSerialConnectionFactory;
import org.eniware.edge.reactor.Instruction;
import org.eniware.edge.reactor.InstructionHandler;
import org.eniware.edge.reactor.InstructionStatus.InstructionState;
import org.eniware.edge.settings.SettingSpecifier;
import org.eniware.edge.settings.SettingSpecifierProvider;
import org.eniware.edge.settings.support.BasicTextFieldSettingSpecifier;
import org.eniware.edge.settings.support.BasicTitleSettingSpecifier;
import org.eniware.util.OptionalService;

/**
 * Control a Modbus "coil" type register to turn a switch on or off.
 * 
 * <p>
 * The configurable properties of this class are:
 * </p>
 * 
 * <dl class="class-properties">
 * <dt>address</dt>
 * <dd>The Modbus address of the coil-type register to use.</dd>
 * <dt>unitId</dt>
 * <dd>The Modbus unit ID to use.</dd>
 * <dt>controlId</dt>
 * <dd>The {@link EdgeControlProvider} UID to use.</dd>
 * <dt>connectionFactory</dt>
 * <dd>The {@link ModbusSerialConnectionFactory} to use.</dd>
 * </dl>
 * 
 * @version 1.2
 */
public class ModbusToggler extends ModbusDeviceSupport
		implements SettingSpecifierProvider, EdgeControlProvider, InstructionHandler {

	private Integer address = 0x4008;

	private String controlId = "/switch/1";
	private MessageSource messageSource;
	private OptionalService<EventAdmin> eventAdmin;

	@Override
	protected Map<String, Object> readDeviceInfo(ModbusConnection conn) {
		return null;
	}

	/**
	 * Get the values of the discreet values, as a Boolean.
	 * 
	 * @return Boolean for the switch status
	 */
	private synchronized Boolean currentValue() throws IOException {
		BitSet result = performAction(new ModbusConnectionAction<BitSet>() {

			@Override
			public BitSet doWithConnection(ModbusConnection conn) throws IOException {
				return conn.readDiscreetValues(new Integer[] { address }, 1);
			}
		});
		if ( log.isInfoEnabled() ) {
			log.info("Read {} value: {}", controlId, result.get(0));
		}
		return result.get(0);
	}

	private synchronized Boolean setValue(Boolean desiredValue) throws IOException {
		final BitSet bits = new BitSet(1);
		bits.set(0, desiredValue);
		log.info("Setting {} value to {}", controlId, desiredValue);
		final Integer[] addresses = new Integer[] { address };
		return performAction(new ModbusConnectionAction<Boolean>() {

			@Override
			public Boolean doWithConnection(ModbusConnection conn) throws IOException {
				return conn.writeDiscreetValues(addresses, bits);
			}
		});
	}

	// EdgeControlProvider

	@Override
	public List<String> getAvailableControlIds() {
		return Collections.singletonList(controlId);
	}

	@Override
	public String getUID() {
		return getControlId();
	}

	@Override
	public EdgeControlInfo getCurrentControlInfo(String controlId) {
		// read the control's current status
		log.debug("Reading {} status", controlId);
		EdgeControlInfoDatum result = null;
		try {
			Boolean value = currentValue();
			result = newEdgeControlInfoDatum(controlId, value);
		} catch ( Exception e ) {
			log.error("Error reading {} status: {}", controlId, e.getMessage());
		}
		if ( result != null ) {
			postControlEvent(result, EdgeControlProvider.EVENT_TOPIC_CONTROL_INFO_CAPTURED);
		}
		return result;
	}

	private EdgeControlInfoDatum newEdgeControlInfoDatum(String controlId, Boolean status) {
		EdgeControlInfoDatum info = new EdgeControlInfoDatum();
		info.setCreated(new Date());
		info.setSourceId(controlId);
		info.setType(EdgeControlPropertyType.Boolean);
		info.setReadonly(false);
		info.setValue(status.toString());
		return info;
	}

	private void postControlEvent(EdgeControlInfoDatum info, String topic) {
		final EventAdmin admin = (eventAdmin != null ? eventAdmin.service() : null);
		if ( admin == null ) {
			return;
		}
		Map<String, ?> props = info.asSimpleMap();
		admin.postEvent(new Event(topic, props));
	}

	// InstructionHandler

	@Override
	public boolean handlesTopic(String topic) {
		return InstructionHandler.TOPIC_SET_CONTROL_PARAMETER.equals(topic);
	}

	@Override
	public InstructionState processInstruction(Instruction instruction) {
		// look for a parameter name that matches a control ID
		InstructionState result = null;
		log.debug("Inspecting instruction {} against control {}", instruction.getId(), controlId);
		for ( String paramName : instruction.getParameterNames() ) {
			log.trace("Got instruction parameter {}", paramName);
			if ( controlId.equals(paramName) ) {
				// treat parameter value as a boolean String
				String str = instruction.getParameterValue(controlId);
				Boolean desiredValue = Boolean.parseBoolean(str);
				Boolean modbusResult = null;
				try {
					modbusResult = setValue(desiredValue);
				} catch ( Exception e ) {
					log.warn("Error handling instruction {} on control {}: {}", instruction.getTopic(),
							controlId, e.getMessage());
				}
				if ( modbusResult != null && modbusResult.booleanValue() ) {
					postControlEvent(newEdgeControlInfoDatum(controlId, desiredValue),
							EdgeControlProvider.EVENT_TOPIC_CONTROL_INFO_CHANGED);
					result = InstructionState.Completed;
				} else {
					result = InstructionState.Declined;
				}
			}
		}
		return result;
	}

	// SettingSpecifierProvider

	@Override
	public String getSettingUID() {
		return "org.eniware.edge.control.modbus.toggle";
	}

	@Override
	public String getDisplayName() {
		return "Modbus Switch Toggler";
	}

	@Override
	public List<SettingSpecifier> getSettingSpecifiers() {
		ModbusToggler defaults = new ModbusToggler();
		List<SettingSpecifier> results = new ArrayList<SettingSpecifier>(20);

		// get current value
		BasicTitleSettingSpecifier status = new BasicTitleSettingSpecifier("status", "N/A", true);
		try {
			Boolean val = currentValue();
			status.setDefaultValue(val.toString());
		} catch ( Exception e ) {
			log.debug("Error reading {} status: {}", controlId, e.getMessage());
		}
		results.add(status);

		results.add(new BasicTextFieldSettingSpecifier("controlId", defaults.controlId));
		results.add(new BasicTextFieldSettingSpecifier("groupUID", defaults.getGroupUID()));
		results.add(new BasicTextFieldSettingSpecifier("modbusNetwork.propertyFilters['UID']",
				"Serial Port"));
		results.add(new BasicTextFieldSettingSpecifier("unitId", String.valueOf(defaults.getUnitId())));
		results.add(new BasicTextFieldSettingSpecifier("address", defaults.address.toString()));

		return results;
	}

	@Override
	public MessageSource getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public Integer getAddress() {
		return address;
	}

	public void setAddress(Integer address) {
		this.address = address;
	}

	public String getControlId() {
		return controlId;
	}

	public void setControlId(String controlId) {
		this.controlId = controlId;
	}

	public OptionalService<EventAdmin> getEventAdmin() {
		return eventAdmin;
	}

	public void setEventAdmin(OptionalService<EventAdmin> eventAdmin) {
		this.eventAdmin = eventAdmin;
	}

}
