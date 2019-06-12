package com.scheduler.utility;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.scheduler.dao.ContainerDAO;
import com.scheduler.model.Capacity;
import com.scheduler.model.Node;

public class NodeUtilsTest {

	@Mock
	ContainerDAO containerDAOImpl;

	@InjectMocks
	NodeUtils nodeUtils;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testSelectNodeByCriteria() {
		Set<Node> nodes = new HashSet<>();
		Node node1 = new Node();
		Map<String, String> hints1 = new HashMap<>();
		hints1.put("cpu", "intel");
		hints1.put("os", "windows");
		node1.setId(UUID.fromString("EC83E8E1-2355-4F86-AA5E-B9B743BBCBAE"));
		node1.setSchedulerHints(hints1);
		Capacity capacity1 = new Capacity();
		capacity1.setTotal(10);
		capacity1.setUsed(8);
		node1.setCapacity(capacity1);
		nodes.add(node1);
		Node node2 = new Node();
		Map<String, String> hints2 = new HashMap<>();
		hints2.put("cpu", "AMD");
		hints2.put("os", "linux");
		node2.setId(UUID.fromString("EC83E8E1-2355-5F86-AA5E-B9B743BBCBAE"));
		node2.setSchedulerHints(hints2);
		node2.setCapacity(capacity1);
		nodes.add(node2);
		Map<String, String> schedulerHints = hints1;

		Set<Node> eligibleNodes = nodeUtils.findNodeByCriteria(schedulerHints, nodes);
		assertTrue(eligibleNodes.size() == 1);
	}

	@Test
	public void testSelectLeastBusyNode() throws Exception {
		Set<Node> nodes = new HashSet<>();
		Node node1 = new Node();
		Map<String, String> hints1 = new HashMap<>();
		hints1.put("cpu", "intel");
		hints1.put("os", "windows");
		node1.setId(UUID.fromString("EC83E8E1-2355-4F86-AA5E-B9B743BBCBAE"));
		node1.setSchedulerHints(hints1);
		Capacity capacity1 = new Capacity();
		capacity1.setTotal(10);
		capacity1.setUsed(8);
		node1.setCapacity(capacity1);
		nodes.add(node1);
		Node node2 = new Node();
		Map<String, String> hints2 = new HashMap<>();
		hints1.put("cpu", "AMD");
		hints1.put("os", "linux");
		node2.setId(UUID.fromString("EC83E8E1-2355-5F86-AA5E-B9B743BBCBA8"));
		Capacity capacity2 = new Capacity();
		capacity2.setTotal(10);
		capacity2.setUsed(0);
		node2.setCapacity(capacity2);
		node2.setSchedulerHints(hints2);
		nodes.add(node2);
		Node node = nodeUtils.findLeastBusyNode(nodes);
		assertTrue(node.getCapacity().getUsed() == 0);
	}

}
