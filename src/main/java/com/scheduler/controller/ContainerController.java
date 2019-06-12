package com.scheduler.controller;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.scheduler.dto.ContainerDTO;
import com.scheduler.exceptionhandler.ContainerNotFoundException;
import com.scheduler.model.Container;
import com.scheduler.service.container.ContainerManager;
import com.scheduler.utility.Converter;

/**
 * The ContainerController is the Container Controller to for managing
 * Container/s {@link com.scheduler.model.Container}
 *
 * @author Kishori Patil
 */
@RestController
public class ContainerController {

	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ContainerController.class);

	/** The container manager impl. */
	@Autowired
	ContainerManager containerManagerImpl;

	/** The converter. */
	@Autowired
	Converter converter;

	/**
	 * <p>
	 * Delete Container {@link com.scheduler.model.Container} to ContainerStore
	 * {@link com.scheduler.datastore.ContainerStore} by ID
	 * </p>
	 * 
	 * @param id {@link String}
	 *            the id of the Container to be deleted
	 * @return {@link Boolean} if entity is deleted
	 * @throws Exception 
	 */
	@DeleteMapping(path = "container/{containerID}")
	public ResponseEntity<Boolean> deleteContainer(@PathVariable("containerID") String id) throws ContainerNotFoundException {
		LOGGER.debug("Delete Container {}", id);
		return new ResponseEntity<>(containerManagerImpl.deleteContainerById(id), HttpStatus.OK);
	}

	/**
	 * <p>
	 * Get all Container {@link com.scheduler.model.Container} from
	 * ContainerStore {@link com.scheduler.datastore.ContainerStore}
	 * </p>
	 * 
	 * @return {@link Set} of all containers {@link com.scheduler.model.Container}
	 */
	@GetMapping(path = "/containers")
	public ResponseEntity<Set<ContainerDTO>> getAllContainers() {
		LOGGER.debug("Get All Container ....");
		Set<Container> containers = containerManagerImpl.getAllContainers();
		Set<ContainerDTO> containerDto = null;
		if (containers != null) {
			containerDto = converter.convertContainerListToDTO(containers);
		}
		return new ResponseEntity<>(containerDto, HttpStatus.OK);

	}

}
