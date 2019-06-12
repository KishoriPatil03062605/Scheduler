package com.scheduler.service.container.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
import com.scheduler.exceptionhandler.ContainerNotFoundException;
import com.scheduler.exceptionhandler.NodeNotFoundException;
import com.scheduler.model.Container;
import com.scheduler.model.Node;
import com.scheduler.service.node.NodeManager;

public class ContainerManagerImplTest {

	@Mock
	ContainerDAO containerDAOImpl;

	@Mock
	NodeManager nodeManagerImpl;

	@InjectMocks
	ContainerManagerImpl containerManagerImpl;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testDeleteContainerById() throws NodeNotFoundException, ContainerNotFoundException {
		Mockito.when(containerDAOImpl.deleteContainer(Mockito.anyString())).thenReturn(true);
		Mockito.doNothing().when(nodeManagerImpl).updateNodeCapacity(Mockito.any(), Mockito.anyInt());
		Container container = new Container();
		Node node = new Node();
		node.setId(UUID.fromString("EC83E8E1-2355-4F86-AA5E-B9B743BBCBAE"));
		container.setNode(node);
		Mockito.when(containerDAOImpl.getContainerById(Mockito.anyString())).thenReturn(container);
		assertTrue(containerManagerImpl.deleteContainerById("id"));

	}

	@Test
	public void testGetAllContainers() {
		Set<Container> containers = new HashSet<>();
		Container container = new Container();
		container.setId(UUID.fromString("EC83E8E1-2355-4F86-AA5E-B9B743BBCBAE"));
		Node node = new Node();
		node.setId(UUID.fromString("EC83E8E1-2355-4F86-AA5E-B9B743BBCBAE"));
		container.setNode(node);
		containers.add(container);
		Mockito.when(containerDAOImpl.getContainers()).thenReturn(containers);
		assertTrue(containerManagerImpl.getAllContainers().size() == 1);
	}

	@Test
	public void testGetContainerByID() throws ContainerNotFoundException {
		Container container = new Container();
		Node node = new Node();
		node.setId(UUID.fromString("EC83E8E1-2355-4F86-AA5E-B9B743BBCBAE"));
		container.setNode(node);
		Mockito.when(containerDAOImpl.getContainerById(Mockito.anyString())).thenReturn(container);
		assertNotNull(containerManagerImpl.getContainerByID("id"));

	}

}
