package com.scheduler.dao;

import java.util.Set;

import org.springframework.stereotype.Repository;

import com.scheduler.exceptionhandler.ContainerNotFoundException;
import com.scheduler.model.Container;
/**
 * The ContainerDAO is the Data access layer interface to access the DataStore
 *  ConatinerStore {@link com.scheduler.datastore.ContainerStore} data
 *
 * @author Kishori Patil
 */
@Repository
public interface ContainerDAO {

	/**
	 * <p>This method is used to get all the 
	 * Containers {@link Container}
	 * from ContainerStore {@link com.scheduler.datastore.ContainerStore}
	 * </p>
	 * @return {@link Set} of Containers {@link Container}
	 */
	public Set<Container> getContainers();

	/**
	 * <p>This method is used to add all the 
	 * Containers {@link Container}
	 * to ContainerStore {@link com.scheduler.datastore.ContainerStore}
	 * </p>
	 *
	 * @param containers the containers to be added
	 * @return {@link Set} of Containers {@link Container}
	 */
	public boolean addContainer(Container containers);

	/**
	 * <p>This method is used to delete a
	 * Container {@link Container}
	 * from ContainerStore {@link com.scheduler.datastore.ContainerStore}
	 * </p>
	 *
	 * @param id the id of the container to be deleted
	 * @return {@link Set} of Containers {@link Container}
	 */
	public boolean deleteContainer(String id);

	/**
	 * <p>This method is used to get
	 * Container {@link Container} by its Id
	 * from ContainerStore {@link com.scheduler.datastore.ContainerStore}
	 * </p>
	 *
	 * @param containerId the container id
	 * @return {@link Set} of Containers {@link Container}
	 * @throws ContainerNotFoundException {@link om.scheduler.exceptionHandler.ContainerNotFoundException} the container not found exception
	 */
	public Container getContainerById(String containerId) throws ContainerNotFoundException;

}
