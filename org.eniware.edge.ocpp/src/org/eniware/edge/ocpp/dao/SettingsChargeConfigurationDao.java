/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.ocpp.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eniware.edge.ocpp.ChargeConfiguration;
import org.eniware.edge.ocpp.ChargeConfigurationDao;
import org.eniware.edge.ocpp.support.SimpleChargeConfiguration;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.solarnetwork.node.dao.SettingDao;
import net.solarnetwork.node.support.KeyValuePair;
import net.solarnetwork.util.OptionalService;
import ocpp.v15.support.ConfigurationKeys;

/**
 * Implementation of {@link ChargeConfigurationDao} that uses {@link SettingDao}
 * for persistence.
 * 
 * All configuration properties are stored using a single {@code key} value of
 * {@link #SETTING_KEY} and the {@code type} values are derived from the
 * {@link ConfigurationKeys#getKey()} values.
 * 
 * @author matt
 * @version 1.0
 * @since 0.6
 */
public class SettingsChargeConfigurationDao implements ChargeConfigurationDao {

	/** A setting key constant used for all configuration properties. */
	public static final String SETTING_KEY = "ocpp-conf";

	private final SettingDao dao;
	private final OptionalService<EventAdmin> eventAdmin;

	private final Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * Constructor.
	 * 
	 * @param dao
	 *        The DAO to persist all configuration in.
	 */
	public SettingsChargeConfigurationDao(SettingDao dao) {
		this(dao, null);
	}

	/**
	 * Constructor with {@code EventAmdin}.
	 * 
	 * @param dao
	 *        The DAO to persist all configuration in.
	 * @param eventAdmin
	 *        An optional {@code EventAdmin} service (may be {@code null}).
	 */
	public SettingsChargeConfigurationDao(SettingDao dao, OptionalService<EventAdmin> eventAdmin) {
		super();
		this.dao = dao;
		this.eventAdmin = eventAdmin;
	}

	@Override
	public void storeChargeConfiguration(ChargeConfiguration config) {
		Map<String, Object> props = new HashMap<String, Object>();
		props.put(ConfigurationKeys.HeartBeatInterval.getKey(), config.getHeartBeatInterval());
		dao.storeSetting(SETTING_KEY, ConfigurationKeys.HeartBeatInterval.getKey(),
				String.valueOf(config.getHeartBeatInterval()));
		props.put(ConfigurationKeys.MeterValueSampleInterval.getKey(),
				config.getMeterValueSampleInterval());
		dao.storeSetting(SETTING_KEY, ConfigurationKeys.MeterValueSampleInterval.getKey(),
				String.valueOf(config.getMeterValueSampleInterval()));
		postEvent(EVENT_TOPIC_CHARGE_CONFIGURATION_UPDATED, props);
	}

	private void postEvent(String topic, Map<String, Object> props) {
		final EventAdmin admin = (eventAdmin != null ? eventAdmin.service() : null);
		if ( admin == null ) {
			return;
		}
		admin.postEvent(new Event(topic, props));
	}

	@Override
	public ChargeConfiguration getChargeConfiguration() {
		SimpleChargeConfiguration config = new SimpleChargeConfiguration();
		List<KeyValuePair> settings = dao.getSettings(SETTING_KEY);
		if ( settings != null ) {
			for ( KeyValuePair kv : settings ) {
				if ( kv.getValue() == null || kv.getValue().isEmpty() ) {
					continue;
				}
				try {
					if ( ConfigurationKeys.HeartBeatInterval.getKey().equals(kv.getKey()) ) {
						config.setHeartBeatInterval(Integer.parseInt(kv.getValue()));
					} else if ( ConfigurationKeys.MeterValueSampleInterval.getKey()
							.equals(kv.getKey()) ) {
						config.setMeterValueSampleInterval(Integer.parseInt(kv.getValue()));
					}
				} catch ( NumberFormatException e ) {
					log.warn("Unexpected number value for OCPP configuration {}: {}", kv.getKey(),
							e.getMessage());
				}
			}
		}
		return config;
	}

}
