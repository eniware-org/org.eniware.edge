/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.runtime;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.eniware.edge.DatumDataSource;
import org.eniware.edge.EdgeControlProvider;
import org.eniware.edge.PlatformService;
import org.eniware.edge.UploadService;
import org.eniware.edge.dao.DatumDao;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.support.GenericMessage;
import org.eniware.domain.Result;
import org.eniware.util.OptionalService;
import org.eniware.util.StringUtils;

/**
 * Bridge between OSGi EventAdmin events and a Spring Messaging.
 * 
 * @version 1.1
 */
public class EventMessageBridge implements EventHandler {

	/** A prefix automatically stripped from event topic values. */
	public static final String Edge_EVENT_PREFIX = "net/eniwarenetwork/Edge/";

	/** The prefix automatically added to every message topic value. */
	public static final String MESSAGE_TOPIC_PREFIX = "/topic/";

	/**
	 * The prefix automatically added to every public message topic value.
	 * 
	 * @since 1.1
	 */
	public static final String PUBLIC_MESSAGE_TOPIC_PREFIX = "/pub/topic/";

	private final OptionalService<SimpMessageSendingOperations> messageSendingOps;
	private Map<String, String> topicMapping;
	private Map<String, String> publicTopicMapping;

	private final Logger log = LoggerFactory.getLogger(getClass());

	private static Map<String, String> defaultTopicMapping() {
		Map<String, String> map = new HashMap<String, String>();
		map.put(DatumDataSource.EVENT_TOPIC_DATUM_CAPTURED, "datum/captured/{sourceId}");
		map.put(DatumDao.EVENT_TOPIC_DATUM_STORED, "datum/stored/{sourceId}");
		map.put(UploadService.EVENT_TOPIC_DATUM_UPLOADED, "datum/uploaded/{sourceId}");
		map.put(EdgeControlProvider.EVENT_TOPIC_CONTROL_INFO_CAPTURED, "control/captured/{controlId}");
		map.put(EdgeControlProvider.EVENT_TOPIC_CONTROL_INFO_CHANGED, "control/changed/{controlId}");
		return Collections.unmodifiableMap(map);
	}

	private static Map<String, String> defaultPublicTopicMapping() {
		Map<String, String> map = new HashMap<String, String>();
		map.put(PlatformService.EVENT_TOPIC_PLATFORM_STATE_CHANGED, "platform/state");
		return Collections.unmodifiableMap(map);
	}

	/**
	 * Constructor.
	 * 
	 * @param messageSendingOps
	 *        the optional message sending service to use
	 */
	public EventMessageBridge(OptionalService<SimpMessageSendingOperations> messageSendingOps) {
		super();
		this.messageSendingOps = messageSendingOps;
		topicMapping = defaultTopicMapping();
		publicTopicMapping = defaultPublicTopicMapping();
	}

	@Override
	public void handleEvent(Event event) {
		Map<String, Object> data = mapForEvent(event);
		String topic = messageTopicForEvent(event, data);
		if ( topic == null ) {
			return;
		}
		log.debug("Posting event {} to message topic {} with data {}", event.getTopic(), topic, data);
		postMessage(topic, data);
	}

	private String messageTopicForEvent(Event event, Map<String, Object> data) {
		boolean pubTopic = false;
		String topic = event.getTopic();
		if ( topicMapping != null ) {
			String val = topicMapping.get(topic);
			if ( val != null ) {
				topic = val;
			}
		}
		if ( publicTopicMapping != null ) {
			String val = publicTopicMapping.get(topic);
			if ( val != null ) {
				topic = val;
				pubTopic = true;
			}
		}
		topic = StringUtils.expandTemplateString(topic, data);
		if ( topic.startsWith(Edge_EVENT_PREFIX) ) {
			topic = topic.substring(Edge_EVENT_PREFIX.length());
		}

		// remove double-slashes
		topic = topic.replaceAll("\\/\\/", "/");

		return (pubTopic ? PUBLIC_MESSAGE_TOPIC_PREFIX : MESSAGE_TOPIC_PREFIX) + topic;
	}

	private Map<String, Object> mapForEvent(Event event) {
		String[] propNames = event.getPropertyNames();
		Map<String, Object> map = new LinkedHashMap<String, Object>(propNames.length);
		for ( String propName : propNames ) {
			map.put(propName, event.getProperty(propName));
		}
		return map;
	}

	/**
	 * Post a message without any headers, converting it first.
	 * 
	 * <p>
	 * Will silently ignore the event if no {@link MessageSendingOperations} is
	 * available.
	 * </p>
	 * 
	 * @param dest
	 *        The destination to post to.
	 * @param body
	 *        The message body to post. This will be wrapped in a {@link Result}
	 *        object if it is not one already.
	 * @see #postMessage(String, Object, Map, boolean)
	 */
	protected void postMessage(String dest, Object body) {
		postMessage(dest, body, null, true);
	}

	/**
	 * Post a message. Will silently ignore the event if no
	 * {@link MessageSendingOperations} is available.
	 * 
	 * <p>
	 * If {@code convert} is {@literal true} the message will be sent via the
	 * {@link MessageSendingOperations#convertAndSend(Object, Object, Map)}
	 * method. Otherwise the
	 * {@link MessageSendingOperations#send(Object, Message)} method will be
	 * used to send the body as-is.
	 * </p>
	 * 
	 * @param dest
	 *        The destination to post to.
	 * @param body
	 *        The message body to post. If {@code convert} is {@literal true}
	 *        then this will be wrapped in a {@link Result} object if it is not
	 *        one already.
	 * @param headers
	 *        an optional set of message headers to include
	 * @param convert
	 *        {@literal true} to convert the message before sending,
	 *        {@literal false} to send without any conversion
	 * @since 1.1
	 */
	protected void postMessage(String dest, Object body, Map<String, Object> headers, boolean convert) {
		SimpMessageSendingOperations ops = (messageSendingOps != null ? messageSendingOps.service()
				: null);
		if ( ops == null ) {
			return;
		}
		if ( convert ) {
			Result<?> r = (body instanceof Result ? (Result<?>) body : Result.result(body));
			ops.convertAndSend(dest, r, headers);
		} else {
			Message<Object> msg = new GenericMessage<Object>(body, headers);
			ops.send(dest, msg);
		}
	}

	/**
	 * Set a mapping of event topic values to corresponding message topic
	 * values.
	 * 
	 * <p>
	 * The event topic values are first stripped of any
	 * {@link #Edge_EVENT_PREFIX}, and the resulting value used as a key to
	 * lookup a message topic value to use from this map. The message topics can
	 * use template variables as defined in
	 * {@link StringUtils#expandTemplateString(String, Map)}; all the event
	 * properties are made available to the expansion.
	 * </p>
	 * 
	 * @param topicMapping
	 *        the topicMapping to set
	 */
	public void setTopicMapping(Map<String, String> topicMapping) {
		this.topicMapping = topicMapping;
	}

	/**
	 * Set the mapping of event topic values to corresponding public message
	 * topic values.
	 * 
	 * <p>
	 * The same messaging handling rules as documented in
	 * {@link #setTopicMapping(Map)} apply here, except that the resulting topic
	 * will have the {@link #PUBLIC_MESSAGE_TOPIC_PREFIX} instead of the
	 * {@link #MESSAGE_TOPIC_PREFIX}.
	 * </p>
	 * 
	 * @param publicTopicMapping
	 *        the public topic mapping to set
	 * @since 1.1
	 * @see #setTopicMapping(Map)
	 */
	public void setPublicTopicMapping(Map<String, String> publicTopicMapping) {
		this.publicTopicMapping = publicTopicMapping;
	}

}
