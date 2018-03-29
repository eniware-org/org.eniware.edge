/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.runtime;

import java.util.Locale;

import org.eniware.edge.PlatformService;
import org.eniware.edge.PlatformService.PlatformTaskInfo;

/**
 * Simple implementation of {@link PlatformTaskInfo}.
 * 
 * @version 1.0
 */
public class SimplePlatformTaskInfo implements PlatformTaskInfo {

	private final String taskId;
	private final String title;
	private final String message;
	private final double percentComplete;
	private final boolean complete;
	private final boolean restartRequired;

	/**
	 * Construct from values.
	 * 
	 * @param taskId
	 *        the task ID
	 * @param title
	 *        the title
	 * @param message
	 *        the message
	 * @param percentComplete
	 *        the percent complete
	 * @param restartRequired
	 *        the restart required flag
	 */
	public SimplePlatformTaskInfo(String taskId, String title, String message, double percentComplete,
			boolean complete, boolean restartRequired) {
		super();
		this.taskId = taskId;
		this.title = title;
		this.message = message;
		this.percentComplete = percentComplete;
		this.complete = complete;
		this.restartRequired = restartRequired;
	}

	/**
	 * Construct from a status and locale.
	 * 
	 * @param status
	 *        the status
	 * @param locale
	 *        the desired locale
	 */
	public SimplePlatformTaskInfo(PlatformService.PlatformTaskStatus status, Locale locale) {
		this(status.getTaskId(), status.getTitle(locale), status.getMessage(locale),
				status.getPercentComplete(), status.isComplete(), status.isRestartRequired());
	}

	@Override
	public String getTaskId() {
		return taskId;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public double getPercentComplete() {
		return percentComplete;
	}

	@Override
	public boolean isComplete() {
		return complete;
	}

	@Override
	public boolean isRestartRequired() {
		return restartRequired;
	}

}
