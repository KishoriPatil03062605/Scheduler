package com.scheduler.service.container.impl;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.scheduler.dao.ContainerDAO;
import com.scheduler.exceptionhandler.ContainerNotFoundException;
import com.scheduler.model.Container;
import com.scheduler.service.container.ContainerManager;
import com.scheduler.service.node.NodeManager;

/**
 * The ContainerManagerImpl is the service Implementation for Managing Container
 * Container {@link com.scheduler.model.Container} It implements
 * ContainerManager interface {@link ContainerManager}
 *
 * @author Kishori Patil
 */
@Component
public class ContainerManagerImpl implements ContainerManager {

	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ContainerManagerImpl.class);

	/** The container DAO impl. */
	@Autowired
	ContainerDAO containerDAOImpl;

	/** The node manager impl. */
	@Autowired
	NodeManager nodeManagerImpl;

	/*
	 * @InheritDocs
	 */
	@Override
	public boolean deleteContainerById(String id) throws ContainerNotFoundException {
		LOGGER.debug("Deleting Container with ID {} from the Scheduled list", id);
		Container containerToDelete = containerDAOImpl.getContainerById(id);
		if (containerDAOImpl.deleteContainer(id)) {
			nodeManagerImpl.updateNodeCapacity(containerToDelete.getNode(), -1);
			return true;
		}
		return false;
	}

	/*
	 * @InheritDocs
	 */
	@Override
	public Set<Container> getAllContainers() {
		return containerDAOImpl.getContainers();
	}

	/*
	 * @InheritDocs
	 */
	@Override
	public Container getContainerByID(String containerId) throws ContainerNotFoundException {
		return containerDAOImpl.getContainerById(containerId);
	}

	@Override
	public boolean addContainer(Container container) {
		return containerDAOImpl.addContainer(container);
	}

}
