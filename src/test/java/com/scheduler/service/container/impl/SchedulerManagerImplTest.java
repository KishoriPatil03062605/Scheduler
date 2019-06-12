package com.scheduler.service.container.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.scheduler.model.Capacity;
import com.scheduler.model.Container;
import com.scheduler.model.Node;
import com.scheduler.service.container.ContainerManager;
import com.scheduler.service.node.NodeManager;
import com.scheduler.utility.NodeUtils;

public class SchedulerManagerImplTest {

	@Mock
	ContainerManager containerManagerImpl;

	@Mock
	NodeManager nodeManagerImpl;

	@Mock
	NodeUtils nodeUtils;

	@InjectMocks
	ScheduleManagerImpl schedulerManagerImpl;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testScheduleContainer() throws Exception {
		Container container = new Container();
		container.setId(UUID.fromString("EC83E8E1-2355-4F86-AA5E-B9B743BBCBAE"));
		Map<String, String> hints = new HashMap<>();
		hints.put("cpu", "AMD");
		hints.put("os", "linux");
		container.setSchedulerHints(hints);
		Set<Node> nodes = new HashSet<>();
		Node node1 = new Node();
		Capacity cap = new Capacity();
		cap.setTotal(10);
		cap.setUsed(0);
		node1.setCapacity(cap);
		Map<String, String> hints1 = new HashMap<>();
		hints1.put("cpu", "intel");
		hints1.put("os", "windows");
		node1.setId(UUID.fromString("EC83E8E1-2355-4F86-AA5E-B9B743BBCBAE"));
		node1.setSchedulerHints(hints1);
		nodes.add(node1);
		Node node2 = new Node();
		node2.setCapacity(cap);
		Map<String, String> hints2 = new HashMap<>();
		hints2.put("cpu", "AMD");
		hints2.put("os", "linux");
		node2.setId(UUID.fromString("EC83E8E1-2355-5F86-AA6E-B9B743BBCBAE"));
		node2.setSchedulerHints(hints2);
		node2.setCapacity(cap);
		nodes.add(node2);
		Mockito.when(nodeUtils.findNodeByCriteria(Mockito.any(), Mockito.any())).thenReturn(nodes);
		Mockito.when(containerManagerImpl.addContainer(Mockito.any())).thenReturn(true);

		schedulerManagerImpl.scheduleContainer(container);
	}

}
