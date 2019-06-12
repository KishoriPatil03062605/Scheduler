package com.scheduler.service.container;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.scheduler.exceptionhandler.ContainerNotFoundException;
import com.scheduler.model.Container;

/**
 * The ContainerManager is the service used for managing Container Entity
 * {@link com.scheduler.model.Container}
 *
 * @author Kishori Patil
 */
@Service
public interface ContainerManager {

	/**
	 * <p>
	 * Delete Container {@link com.scheduler.model.Container} to ContainerStore
	 * {@link com.scheduler.datastore.ContainerStore} by ID
	 * </p>
	 *
	 * @param id
	 *            the id of the Container to be deleted
	 * @return {@link Boolean} true, if successful
	 * @throws ContainerNotFoundException
	 *             {@link om.scheduler.exceptionHandler.ContainerNotFoundException}
	 *             the container not found exception
	 */
	public boolean deleteContainerById(String id) throws ContainerNotFoundException;

	/**
	 * <p>
	 * Get all Container {@link com.scheduler.model.Container} from
	 * ContainerStore {@link com.scheduler.datastore.ContainerStore}
	 * </p>
	 * 
	 * @return {@link Set} the all containers
	 */
	public Set<Container> getAllContainers();

	/**
	 * <p>
	 * Get Container {@link com.scheduler.model.Container} from ContainerStore
	 * {@link com.scheduler.datastore.ContainerStore} By ID
	 * </p>
	 * 
	 * @param containerId
	 *            the container id
	 * @return the Container {@link com.scheduler.model.Container} by ID
	 * @throws ContainerNotFoundException
	 *             {@link om.scheduler.exceptionHandler.ContainerNotFoundException}
	 *             the container not found exception
	 */
	public Container getContainerByID(String containerId) throws ContainerNotFoundException;

	/**
	 * <p>
	 * Add Container {@link com.scheduler.model.Container} to ContainerStore
	 * {@link com.scheduler.datastore.ContainerStore}
	 * </p>
	 * 
	 * @param container
	 * @return boolean
	 */
	public boolean addContainer(Container container);

}
