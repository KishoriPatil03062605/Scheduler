package com.scheduler.controller;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.scheduler.dto.NodeDTO;
import com.scheduler.model.Node;
import com.scheduler.service.node.NodeManager;
import com.scheduler.utility.Converter;

@WebMvcTest(NodeController.class)
@RunWith(SpringRunner.class)
public class NodeControllerTest {

	@Autowired
	private MockMvc mockmvc;

	@MockBean
	NodeManager nodeManagerImpl;

	@MockBean
	private Converter converter;

	@Test
	public void testGetAllNodes() throws Exception {
		Set<Node> nodes = new HashSet<>();
		Node node = new Node();
		node.setId(UUID.fromString("EC83E8E1-2355-4F86-AA5E-B9B743BBCBAE"));
		nodes.add(node);
		Mockito.when(nodeManagerImpl.getAllNodes()).thenReturn(nodes);
		Mockito.when(converter.convertNodeListToDTO(Mockito.any())).thenReturn(null);
		MvcResult mvcResult = mockmvc
				.perform(MockMvcRequestBuilders.get("/nodes").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

	}

	@Test
	public void testGetNodeById() throws Exception {
		Node node = new Node();
		node.setId(UUID.fromString("EC83E8E1-2355-4F86-AA5E-B9B743BBCBAE"));
		Mockito.when(nodeManagerImpl.getNodeById(Mockito.anyString())).thenReturn(node);
		NodeDTO responseDTO = new NodeDTO();
		Mockito.when(converter.convertToDTO(node)).thenReturn(responseDTO);
		MvcResult mvcResult = mockmvc
				.perform(MockMvcRequestBuilders.get("/node/1").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

	}

}
