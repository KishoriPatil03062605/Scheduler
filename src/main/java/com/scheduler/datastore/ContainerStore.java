package com.scheduler.datastore;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.scheduler.model.Container;

/**
 * The ContainerStore is the Data store i.e. in memory Database like storage for
 * for Container {@link com.scheduler.model.Container} data.
 *
 * @author Kishori Patil
 */
@Component
@Scope(value = "singleton")
public class ContainerStore {

	/** The containers. */
	private Set<Container> containers;

	/**
	 * Gets the containers.
	 *
	 * @return the containers
	 */
	public Set<Container> getContainers() {
		if (containers == null) {
			containers = new LinkedHashSet<>();
		}
		return containers;
	}

	/**
	 * Sets the containers.
	 *
	 * @param containers
	 *            the new containers
	 */
	public void setContainers(Set<Container> containers) {
		this.containers = containers;
	}

}
