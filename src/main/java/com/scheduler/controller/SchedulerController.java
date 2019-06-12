package com.scheduler.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.scheduler.dto.ContainerDTO;
import com.scheduler.exceptionhandler.ContainerNotFoundException;
import com.scheduler.exceptionhandler.NoSuitableNodeFoundException;
import com.scheduler.exceptionhandler.SchedulerAPIException;
import com.scheduler.model.Container;
import com.scheduler.service.container.ContainerManager;
import com.scheduler.service.container.ScheduleManager;
import com.scheduler.utility.Converter;

/**
 * The SchedulerController is the Rest Controller to for scheduling a Container
 * {@link com.scheduler.model.Container} to a Node
 * {@link com.scheduler.model.Node}
 *
 * @author Kishori Patil
 */
@RestController
public class SchedulerController {

	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SchedulerController.class);

	/** The schedule manager impl. */
	@Autowired
	ScheduleManager scheduleManagerImpl;

	/** The container manager impl. */
	@Autowired
	ContainerManager containerManagerImpl;

	/** The converter. */
	@Autowired
	Converter converter;

	/**
	 * <p>
	 * Schedule container {@link com.scheduler.model.Container} to a Node
	 * {@link com.scheduler.model.Node}
	 * </p>
	 * 
	 * @param containerDto
	 *            ContainerDTO {@link com.scheduler.dto.ContainerDTO} the
	 *            container to be scheduled
	 * @return ContainerDTO {@link com.scheduler.dto.ContainerDTO} the response
	 *         entity
	 * @throws ContainerNotFoundException
	 * @throws NoSuitableNodeFoundException
	 * @throws Exception
	 *             the exception
	 */
	@PostMapping(path = "/container/schedule")
	public ResponseEntity<ContainerDTO> scheduleContainer(@RequestBody @Valid ContainerDTO containerDto)
			throws SchedulerAPIException {
		LOGGER.debug("Schedule container ....");

		Container scheduledContainer = scheduleManagerImpl.scheduleContainer(converter.convertToModel(containerDto));
		ContainerDTO responseDTO = converter.convertToDTO(scheduledContainer);
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}

}
