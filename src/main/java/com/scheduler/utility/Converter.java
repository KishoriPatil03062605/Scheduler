package com.scheduler.utility;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.scheduler.dto.CapacityDTO;
import com.scheduler.dto.ContainerDTO;
import com.scheduler.dto.NodeDTO;
import com.scheduler.enums.Status;
import com.scheduler.model.Capacity;
import com.scheduler.model.Container;
import com.scheduler.model.Node;

/**
 * The Converter is a transformer class which converts Data Transfer Objects to
 * Domain Objects or Entity.
 *
 * @author Kishori Patil
 */
@Component
public class Converter {

	/**
	 * Convert to {@link ContainerDTO} model to {@link Container}
	 *
	 * @param containerDto
	 *            {@link ContainerDTO} the container dto
	 * @return the container {@link Container}
	 */
	public Container convertToModel(ContainerDTO containerDto) {
		Container container = new Container();
		container.setId(UUID.fromString(containerDto.getId()));
		container.setImage(containerDto.getImage());
		container.setName(containerDto.getName());
		container.setStatus(Status.valueOfStatus(containerDto.getStatus()));
		container.setSchedulerHints(containerDto.getSchedulerHints());
		return container;

	}

	/**
	 * Convert Container {@link Converter} to DTO {@link ContainerDTO}
	 *
	 * @param container
	 *            {@link Converter} the container
	 * @return the container DTO {@link ContainerDTO}
	 */
	public ContainerDTO convertToDTO(Container container) {
		ContainerDTO containerDTO = new ContainerDTO();
		containerDTO.setId(container.getId().toString());
		containerDTO.setImage(container.getImage());
		containerDTO.setName(container.getName());
		containerDTO.setStatus(container.getStatus().toString());
		containerDTO.setSchedulerHints(container.getSchedulerHints());
		NodeDTO nodeDto = convertToDTO(container.getNode());
		containerDTO.setNode(nodeDto);
		return containerDTO;

	}

	/**
	 * Convert {@link Node} to DTO {@link NodeDTO}
	 *
	 * @param node
	 *            the node {@link Node}
	 * @return the node DTO {@link NodeDTO}
	 */
	public NodeDTO convertToDTO(Node node) {
		NodeDTO nodeDto = new NodeDTO();
		CapacityDTO capacityDto = convertToDTO(node.getCapacity());
		nodeDto.setId(node.getId().toString());
		nodeDto.setCapacity(capacityDto);
		nodeDto.setName(node.getName());
		nodeDto.setSchedulerHint(node.getSchedulerHints());
		return nodeDto;
	}

	/**
	 * Convert {@link Capacity} to DTO {@link CapacityDTO}
	 *
	 * @param capacity
	 *            the capacity {@link Capacity}
	 * @return the capacity DTO {@link CapacityDTO}
	 */
	private CapacityDTO convertToDTO(Capacity capacity) {
		CapacityDTO capacityDto = new CapacityDTO();
		capacityDto.setTotal(capacity.getTotal());
		capacityDto.setUsed(capacity.getUsed());
		return capacityDto;
	}

	/**
	 * Convert containers {@link Set} of {@link Container} to DTOs {@link Set}
	 * of {@link ContainerDTO}.
	 *
	 * @param containers
	 *            {@link Set} of {@link Container}
	 * @return the set of ContainerDTO {@link Set} of {@link ContainerDTO}
	 */
	public Set<ContainerDTO> convertContainerListToDTO(Set<Container> containers) {
		return containers.stream().map(this::convertToDTO).collect(Collectors.toSet());
	}

	/**
	 * Convert nodes {@link Set} of {@link Node} to DTOs {@link Set} of
	 * {@link NodeDTO}.
	 *
	 * @param nodes
	 *            {@link Set} of {@link Node}
	 * @return the set of NodeDTO {@link Set} of {@link NodeDTO}
	 */
	public Set<NodeDTO> convertNodeListToDTO(Set<Node> nodes) {
		return nodes.stream().map(this::convertToDTO).collect(Collectors.toSet());
	}

}
