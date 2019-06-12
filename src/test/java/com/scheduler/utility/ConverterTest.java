package com.scheduler.utility;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.scheduler.dto.ContainerDTO;
import com.scheduler.dto.NodeDTO;
import com.scheduler.enums.Status;
import com.scheduler.model.Capacity;
import com.scheduler.model.Container;
import com.scheduler.model.Node;

@RunWith(SpringRunner.class)
public class ConverterTest {

	@Test
	public void testConvertToModelContainerDTO() {
		Converter converter = new Converter();
		ContainerDTO containerDto = new ContainerDTO();
		containerDto.setId("EC83E8E1-2355-4F86-AA5E-B9B743BBCBAE");
		containerDto.setImage("haproxy:latest");
		containerDto.setName("web-l7-lb");
		containerDto.setStatus("pending");
		Map<String, String> schedulerHints = new HashMap<>();
		schedulerHints.put("network", "10G");
		schedulerHints.put("os", "Linux");
		containerDto.setSchedulerHints(schedulerHints);
		Container container = converter.convertToModel(containerDto);
		assertTrue(container.getId().toString().equalsIgnoreCase(containerDto.getId()));
	}

	@Test
	public void testConvertToDTOContainer() {
		Converter converter = new Converter();
		Container container = new Container();
		container.setId(UUID.fromString("EC83E8E1-2355-4F86-AA5E-B9B743BBCBAE"));
		container.setImage("image");
		container.setStatus(Status.PENDING);
		Node node = new Node();
		container.setNode(node);
		Capacity capacity = new Capacity();
		capacity.setTotal(10);
		capacity.setUsed(4);
		node.setCapacity(capacity);
		node.setId(UUID.fromString("EC83E8E1-2355-4F86-AA5E-B9B743BBCBAE"));
		container.setNode(node);
		ContainerDTO dto = converter.convertToDTO(container);
		assertTrue(dto.getId().equalsIgnoreCase(container.getId().toString()));

	}

	@Test
	public void testConvertToDTONode() {
		Converter converter = new Converter();
		Node node = new Node();
		node.setId(UUID.fromString("EC83E8E1-2355-4F86-AA5E-B9B743BBCBAE"));
		node.setName("Node1");
		node.setThresholdReached(false);
		Capacity capacity = new Capacity();
		capacity.setTotal(10);
		capacity.setUsed(4);
		node.setCapacity(capacity);
		NodeDTO dto = converter.convertToDTO(node);
		assertTrue(dto.getName().equalsIgnoreCase(node.getName()));
	}

	@Test
	public void testConvertContainerListToDTO() {
		Converter converter = new Converter();
		Set<Container> containers = new HashSet<>();
		Container cont = new Container();
		cont.setId(UUID.fromString("EC83E8E1-2355-4F86-AA5E-B9B743BBCBAE"));
		cont.setImage("Image");
		cont.setName("name");
		cont.setStatus(Status.PENDING);
		Node node = new Node();
		Capacity capacity = new Capacity();
		capacity.setTotal(10);
		capacity.setUsed(4);
		node.setCapacity(capacity);
		node.setId(UUID.fromString("EC83E8E1-2355-4F86-AA5E-B9B743BBCBAE"));
		cont.setNode(node);
		containers.add(cont);
		Set<ContainerDTO> contDTO = converter.convertContainerListToDTO(containers);
		assertTrue(contDTO.size() == 1);
	}

	@Test
	public void testConvertNodeListToDTO() {
		Converter converter = new Converter();
		Set<Node> nodes = new HashSet<>();
		Node node = new Node();
		node.setName("name");
		node.setId(UUID.fromString("EC83E8E1-2355-4F86-AA5E-B9B743BBCBAE"));
		Capacity capacity = new Capacity();
		capacity.setTotal(10);
		capacity.setUsed(4);
		node.setCapacity(capacity);
		nodes.add(node);
		Set<NodeDTO> nodeDtos = converter.convertNodeListToDTO(nodes);
		assertTrue(nodeDtos.size() == 1);
	}

}
