package com.scheduler.service.node.impl;

import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.scheduler.dao.ContainerDAO;
import com.scheduler.dao.NodeDAO;
import com.scheduler.exceptionhandler.NodeNotFoundException;
import com.scheduler.model.Capacity;
import com.scheduler.model.Node;
import com.scheduler.utility.NodeUtils;

public class NodeManagerImplTest {
	@Mock
	ContainerDAO containerDAOImpl;

	@Mock
	NodeDAO nodeDAOImpl;

	@Mock
	NodeUtils scheduler;

	@InjectMocks
	NodeManagerImpl nodeManagerImpl;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetAllNodes() {
		Set<Node> nodes = new HashSet<>();
		Node node = new Node();
		node.setId(UUID.fromString("EC83E8E1-2355-4F86-AA5E-B9B743BBCBAE"));
		nodes.add(node);
		Mockito.when(nodeDAOImpl.getNodes()).thenReturn(nodes);
		assertNotNull(nodeManagerImpl.getAllNodes());
	}

	@Test
	public void testGetNodeById() throws NodeNotFoundException {
		Node node = new Node();
		node.setId(UUID.fromString("EC83E8E1-2355-4F86-AA5E-B9B743BBCBAE"));
		Mockito.when(nodeDAOImpl.getNodeById(Mockito.any())).thenReturn(node);
		assertNotNull(nodeManagerImpl.getNodeById("EC83E8E1-2355-4F86-AA5E-B9B743BBCBAE"));
	}

	@Test
	public void testUpdateNode() {
		Set<Node> nodes = new HashSet<>();
		Node node = new Node();
		node.setId(UUID.fromString("EC83E8E1-2355-4F86-AA5E-B9B743BBCBAE"));
		nodes.add(node);
		Capacity capacity = new Capacity();
		capacity.setTotal(10);
		capacity.setUsed(9);
		node.setCapacity(capacity);
		Node selectedNode = new Node();
		selectedNode.setName("Node");
		selectedNode.setId(UUID.fromString("EC83E8E1-2355-4F86-AA5E-B9B743BBCBAE"));
		Mockito.when(nodeDAOImpl.getNodes()).thenReturn(nodes);
		nodeManagerImpl.updateNodeCapacity(selectedNode, 1);
		assertNotNull(selectedNode);
	}
}
