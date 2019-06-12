package com.scheduler.service.container;

import org.springframework.stereotype.Service;

import com.scheduler.exceptionhandler.SchedulerAPIException;
import com.scheduler.model.Container;

/**
 * The ScheduleManager is the service used for schedule a Container
 * {@link com.scheduler.model.Container}
 *
 * @author Kishori Patil
 */
@Service
public interface ScheduleManager {

	/**
	 * <p>
	 * Schedule container {@link com.scheduler.model.Container} to a Node
	 * {@link com.scheduler.model.Node}
	 * </p>
	 * 
	 * @param container
	 *            Container {@link com.scheduler.model.Container} the container
	 *            to be scheduled
	 * @return container Container {@link com.scheduler.model.Container} the
	 *         Scheduled container
	 * @throws {@link
	 *             com.scheduler.exceptionhandler.SchedulerAPIException}
	 */
	public Container scheduleContainer(Container container) throws SchedulerAPIException;

}
