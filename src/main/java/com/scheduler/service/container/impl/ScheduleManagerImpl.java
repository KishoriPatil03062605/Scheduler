package com.scheduler.service.container.impl;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.scheduler.enums.Status;
import com.scheduler.exceptionhandler.NoSuitableNodeFoundException;
import com.scheduler.exceptionhandler.SchedulerAPIException;
import com.scheduler.model.Container;
import com.scheduler.model.Node;
import com.scheduler.service.container.ContainerManager;
import com.scheduler.service.container.ScheduleManager;
import com.scheduler.service.node.NodeManager;
import com.scheduler.utility.NodeUtils;

/**
 * The SchedulerManagerImpl is the service Implementation for scheduling
 * Container Container {@link com.scheduler.model.Container} to Node
 * {@link com.scheduler.model.Node} It implements ScheduleManager interface
 * {@link ScheduleManager}
 *
 * @author Kishori Patil
 */
@Component
public class ScheduleManagerImpl implements ScheduleManager {

	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleManagerImpl.class);

	/** The container manager impl. */
	@Autowired
	ContainerManager containerManagerImpl;

	/** The node utils. */
	@Autowired
	NodeUtils nodeUtils;

	/** The node helper. */
	@Autowired
	NodeManager nodeManagerImpl;

	/*
	 * @InheritDocs
	 */
	@Override
	public Container scheduleContainer(Container container) throws SchedulerAPIException {
		LOGGER.debug("Begin to schedule Container {}", container.getId());
		Set<Node> eligibleNodes = nodeUtils.findNodeByCriteria(container.getSchedulerHints(),
				nodeManagerImpl.getAllNodes());
		if (eligibleNodes != null && eligibleNodes.isEmpty()) {
			NoSuitableNodeFoundException e = new NoSuitableNodeFoundException(
					"Container cannot be scheduled as none of the available nodes match the required criteria");
			LOGGER.error(
					"Container cannot be scheduled as none of the available nodes match the required criteria :: {}",
					e);
			throw e;
		}
		Node selectedNode;
		try {
			selectedNode = nodeUtils.findLeastBusyNode(eligibleNodes);
		} catch (Exception e) {
			LOGGER.error(
					"Exception occured while finding the least busy Node for scheduling conatiner ID {}. Exception {}",
					container.getId(), e);
			throw new NoSuitableNodeFoundException(
					"Conatiner cannot be scheduled as not node matches the required criteria");
		}
		nodeManagerImpl.updateNodeCapacity(selectedNode, 1);
		container.setNode(selectedNode);
		container.setStatus(Status.SCHEDULED);
		containerManagerImpl.addContainer(container);
		return container;
	}

}
