/* ==================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.setup.web;

import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.http.HttpHeaders;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;
import org.eniware.edge.PlatformService;
import org.eniware.web.domain.Response;

/**
 * Web controller for platform service support.
 * 
 * @version 1.0
 */
@RestController
public class PlatformController extends BaseSetupWebServiceController
		implements ApplicationListener<SessionSubscribeEvent> {

	private final PlatformService platformService;

	@Autowired
	public PlatformController(PlatformService platformService) {
		super();
		this.platformService = platformService;
	}

	@RequestMapping(value = "/pub/platform/state", method = RequestMethod.GET)
	public Response<PlatformService.PlatformState> activePlatformState() {
		PlatformService.PlatformState state = platformService.activePlatformState();
		return Response.response(state);
	}

	@RequestMapping(value = "/pub/platform/task", method = RequestMethod.GET)
	public Response<PlatformService.PlatformTaskInfo> activePlatformTaskInfo(Locale locale) {
		PlatformService.PlatformTaskInfo info = platformService.activePlatformTaskInfo(locale);
		return Response.response(info);
	}

	@Override
	public void onApplicationEvent(SessionSubscribeEvent event) {
		StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(event.getMessage(),
				StompHeaderAccessor.class);
		String topic = accessor.getDestination();
		if ( topic.endsWith("/platform/task") ) {
			String langHeaderValue = accessor.getFirstNativeHeader(HttpHeaders.ACCEPT_LANGUAGE);
			Locale locale = parseFirstLocaleFromAcceptHeader(langHeaderValue);
			platformService.subscribeToActivePlatformTaskInfo(locale);
		}
	}

	private Locale parseFirstLocaleFromAcceptHeader(String headerValue) {
		Locale result = null;
		if ( headerValue != null ) {
			String[] langTags = headerValue.split(",", 2);
			if ( langTags.length > 0 ) {
				String[] langComponents = langTags[0].trim().replace('-', '_').split(";", 2)[0]
						.split("_");
				switch (langComponents.length) {
					case 2:
						result = new Locale(langComponents[0], langComponents[1]);
						break;
					case 3:
						result = new Locale(langComponents[0], langComponents[1], langComponents[2]);
						break;
					default:
						result = new Locale(langComponents[0]);
						break;
				}
			}
		}
		if ( result == null ) {
			result = Locale.getDefault();
		}
		return result;
	}

}
