/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.setup.web;

import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.eniware.edge.IdentityService;
import org.eniware.edge.setup.SetupService;

/**
 * Base class for setup controllers.
 * 
 * <p>
 * The configurable properties of this class are:
 * </p>
 * 
 * <dl class="class-properties">
 * <dt>setupBiz</dt>
 * <dd>The {@link SetupService} to use for querying/storing application state
 * information.</dd>
 * 
 * <dt>identityService</dt>
 * <dd>The {@link IdentityService} to use for querying identity
 * information.</dd>
 * </dl>
 * 
 * @version 1.1
 */
public class BaseSetupController {

	protected final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private SetupService setupBiz;

	@Autowired
	private IdentityService identityService;

	@Autowired(required = false)
	private BundleContext bundleContext;

	/**
	 * Shutdown EniwareEdge in the near future.
	 * 
	 * This can be used during the setup process, when restoring backups for
	 * example. By shutting down we assume some external watchdog process will
	 * bring EniwareEdge back up, such as {@code systemd} or Monit.
	 * 
	 * @since 1.1
	 */
	protected void shutdownSoon() {
		final BundleContext ctx = bundleContext;
		if ( ctx != null ) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						Thread.sleep(2000);
					} catch ( Exception e ) {
						// ignore
					}

					final Thread shutdownThread = Thread.currentThread();
					final long start = System.currentTimeMillis();

					// start another thread to monitor the shutdown process, in case OSGi takes too long or gets hung up
					Thread shutdownMonitorThread = new Thread(new Runnable() {

						@Override
						public void run() {
							try {
								shutdownThread.join(8000);
								final long end = (System.currentTimeMillis() - start);
								if ( end < 900 ) {
									Thread.sleep(2000);
								}
							} catch ( Exception e ) {
								// ignore
							} finally {
								System.err.println("Exiting from shutdown request.");
								System.exit(0);
							}
						}
					}, "Backup Restore Shutdown Monitor");
					shutdownMonitorThread.setDaemon(true);
					shutdownMonitorThread.start();

					try {
						log.warn("Stopping OSGi from shutdown request...");
						ctx.getBundle(0).stop(org.osgi.framework.Bundle.STOP_TRANSIENT);
					} catch ( Exception e ) {
						System.err.println("Exception shutting down OSGi: " + e);
					}
				}
			}, "Backup Restore Shutdown").start();
		}
	}

	public SetupService getSetupBiz() {
		return setupBiz;
	}

	public void setSetupBiz(SetupService setupBiz) {
		this.setupBiz = setupBiz;
	}

	public IdentityService getIdentityService() {
		return identityService;
	}

	public void setIdentityService(IdentityService identityService) {
		this.identityService = identityService;
	}

}
