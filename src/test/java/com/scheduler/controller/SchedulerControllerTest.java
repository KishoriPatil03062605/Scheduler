package com.scheduler.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.scheduler.service.container.ContainerManager;
import com.scheduler.service.container.ScheduleManager;
import com.scheduler.utility.Converter;
@WebMvcTest(SchedulerController.class)
@RunWith(SpringRunner.class)
public class SchedulerControllerTest {

	@Autowired
	private MockMvc mockmvc;

	@MockBean
	ScheduleManager scheduleManagerImpl;

	@MockBean
	ContainerManager containerManagerImpl;

	@MockBean
	private Converter converter;

	@Test
	public void testScheduleContainer() throws Exception {
		String json = "[{'id': '0259464E-FFCD-44FB-AAAF-F69752E9EC6C', " + "'name': 'node-017-ukwest',"
				+ "'capacity': {" + "'total': 10," + "'used': 0" + "},'scheduler-hints': {";

		MvcResult mvcResult = mockmvc.perform(MockMvcRequestBuilders.post("/container/schedule")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(json)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(400, status);
	}

}
