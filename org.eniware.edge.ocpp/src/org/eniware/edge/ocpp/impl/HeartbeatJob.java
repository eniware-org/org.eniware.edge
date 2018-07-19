/* ==================================================================
 * 
 Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */
 
 

package org.eniware.edge.ocpp.impl;

import javax.xml.ws.WebServiceException;

import org.eniware.edge.ocpp.CentralSystemServiceFactory;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.PersistJobDataAfterExecution;
import org.eniware.edge.job.AbstractJob;
import ocpp.v15.cs.CentralSystemService;
import ocpp.v15.cs.HeartbeatRequest;
import ocpp.v15.cs.HeartbeatResponse;

/**
 * Job to post the {@link HeartbeatRequest} to let the OCPP system know the Edge
 * is alive and has network connectivity.
 * 
 * @version 2.0
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class HeartbeatJob extends AbstractJob {

	private CentralSystemServiceFactory service;

	@Override
	protected void executeInternal(JobExecutionContext jobContext) throws Exception {
		if ( service == null ) {
			log.warn("No CentralSystemServiceFactory available, cannot post heartbeat message.");
			return;
		}
		try {
			if ( !service.isBootNotificationPosted() ) {
				service.postBootNotification();
				return;
			}

			CentralSystemService client = service.service();
			if ( client == null ) {
				log.warn("No CentralSystemService avaialble, cannot post heartbeat message.");
				return;
			}
			HeartbeatRequest req = new HeartbeatRequest();
			HeartbeatResponse res = client.heartbeat(req, service.chargeBoxIdentity());
			log.info("OCPP heartbeat response: {}", res == null ? null : res.getCurrentTime());
		} catch ( WebServiceException e ) {
			log.warn("Error communicating with OCPP central system: {}", e.getMessage());
		}
	}

	/**
	 * Set the OCPP central service factory to use.
	 * 
	 * @param service
	 *        The service to use.
	 */
	public void setService(CentralSystemServiceFactory service) {
		this.service = service;
	}

}
