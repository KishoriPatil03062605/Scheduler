package com.scheduler.dao.impl;

import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.scheduler.dao.ContainerDAO;
import com.scheduler.datastore.ContainerStore;
import com.scheduler.exceptionhandler.ContainerNotFoundException;
import com.scheduler.model.Container;

/**
 * The ContainerDAOImpl is the Data access layer implementation to access the
 * DataStore ContainerStore {@link com.scheduler.datastore.ContainerStore} data.
 * It implements ContainerDAO interface {@link ContainerDAO}
 *
 * @author Kishori Patil
 */
@Component
public class ContainerDAOImpl implements ContainerDAO {
	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ContainerDAOImpl.class);

	/** The container store. */
	@Autowired
	ContainerStore containerStore;

	/*
	 * @inheritDoc
	 */
	@Override
	public Set<Container> getContainers() {
		return containerStore.getContainers();
	}

	/*
	 * @inheritDoc
	 */
	@Override
	public boolean addContainer(Container container) {
		containerStore.getContainers().add(container);
		return true;
	}

	/*
	 * @inheritDoc
	 */
	@Override
	public boolean deleteContainer(String containerId) {
		Optional<Container> currentContainer = containerStore.getContainers().stream()
				.filter(cont -> cont.getId().toString().equals(containerId)).findFirst();
		if (currentContainer.isPresent()) {
			containerStore.getContainers().remove(currentContainer.get());
			return true;
		}
		return false;
	}

	/*
	 * @inheritDoc
	 */
	@Override
	public Container getContainerById(String containerId) throws ContainerNotFoundException {
		Optional<Container> currentContainer = containerStore.getContainers().stream()
				.filter(cont -> cont.getId().toString().equals(containerId)).findFirst();
		if (currentContainer.isPresent()) {
			return currentContainer.get();
		}
		ContainerNotFoundException e = new ContainerNotFoundException("Container is invalid ");
		LOGGER.error("No container found with id {} :: {}", containerId, e);
		throw e;
	}

}
