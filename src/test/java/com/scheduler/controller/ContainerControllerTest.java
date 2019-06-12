package com.scheduler.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.scheduler.model.Container;
import com.scheduler.service.container.ContainerManager;
import com.scheduler.utility.Converter;

@WebMvcTest(ContainerController.class)
@RunWith(SpringRunner.class)
public class ContainerControllerTest {

	@Autowired
	private MockMvc mockmvc;

	@MockBean
	private ContainerManager containerManagerImpl;
	
	@MockBean
	private Converter converter;

	@Test
	public void testDeleteContainer() throws Exception {
		given(containerManagerImpl.deleteContainerById("1")).willReturn(true);
		mockmvc.perform(delete("/container/" + 1)).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE));
	}

	@Test
	public void testGetAllContainers() throws Exception {
		Set<Container> containers = new HashSet<>();
		Container container = new Container();
		container.setId(UUID.fromString("EC83E8E1-2355-4F86-AA5E-B9B743BBCBAE"));
		containers.add(container);
		given(containerManagerImpl.getAllContainers()).willReturn(containers);
		mockmvc.perform(get("/containers/")).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE));
	}

}
