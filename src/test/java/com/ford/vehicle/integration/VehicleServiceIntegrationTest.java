package com.ford.vehicle.integration;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ford.vehicle.VehicleServiceApplication;
import com.ford.vehicle.domain.Vehicle;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;



@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT,
		classes=VehicleServiceApplication.class
		)
@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:application-test.properties")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VehicleServiceIntegrationTest {
	@Autowired
	private MockMvc mockMvc;
	
	private String vehicleJson = "{" + 
			"\"model\":\"Ford Focus\"," + 
			"\"year\":2018," + 
			"\"price\":250000," + 
			"\"vehicleStatus\":\"NEW\"," + 
			"\"dealerName\":\"FORD\"" + 
			"}";
	private String updateVehicleJsonvalue = "{" + 
			"\"vehicleId\":1,"+
			"\"model\":\"Ford Focusupdated\"," + 
			"\"year\":2018," + 
			"\"price\":250000," + 
			"\"vehicleStatus\":\"NEW\"," + 
			"\"dealerName\":\"FORD\"" + 
			"}";
	
	@Test
	public void whenCreateVehicle_thenReturnJson() throws Exception {
		String expectedJson = "1";
		MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/vehicles/").accept(MediaType.APPLICATION_JSON)
				            .content(vehicleJson).contentType(MediaType.APPLICATION_JSON)).andReturn();
		System.out.println("response status"+mvcResult.getResponse().getContentAsString());
		//Mockito.verify(vehicleService).create(Matchers.refEq(vehicle));
		assertEquals("Expected code did not match", 200, mvcResult.getResponse().getStatus());
		JSONAssert.assertEquals(expectedJson, mvcResult.getResponse().getContentAsString(), false);
	}
	@Test
	public void whenGetVehicle_thenReturnJson() throws Exception {
		String expectedJson = "[{" + 
				"\"vehicleId\":1,"+
				"\"model\":\"Ford Focus\"," + 
				"\"year\":2018," + 
				"\"price\":250000," + 
				"\"vehicleStatus\":\"NEW\"," + 
				"\"dealerName\":\"FORD\"" + 
				"}]";
		MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.get("/vehicles/").accept(MediaType.APPLICATION_JSON)).andReturn();
		System.out.println("mvcResult.getResponse() is:"+mvcResult.getResponse().getContentAsString());
		assertEquals("Expected code did not match", 200, mvcResult.getResponse().getStatus());
		JSONAssert.assertEquals(expectedJson, mvcResult.getResponse().getContentAsString(), false);
	}
	@Test
	public void whenUpdateVehicle_thenReturnJson() throws Exception {
		String expectedJson = "1";
		MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.put("/vehicles/").accept(MediaType.APPLICATION_JSON)
				            .content(updateVehicleJsonvalue).contentType(MediaType.APPLICATION_JSON)).andReturn();
		System.out.println("when UpdateVehicle_thenReturnJson response string"+mvcResult.getResponse().getContentAsString());
		//Mockito.verify(vehicleService).create(Matchers.refEq(vehicle));
		assertEquals("Expected code did not match", 200, mvcResult.getResponse().getStatus());
		JSONAssert.assertEquals(expectedJson, mvcResult.getResponse().getContentAsString(), false);
	}
	@Test
	public void whenVGetVehicleId_thenReturnJson() throws Exception {
		String expectedJson = "{" + 
				"\"vehicleId\":1,"+
				"\"model\":\"Ford Focusupdated\"," + 
				"\"year\":2018," + 
				"\"price\":250000," + 
				"\"vehicleStatus\":\"NEW\"," + 
				"\"dealerName\":\"FORD\"" + 
				"}";
		long id=1;
		MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.get("/vehicles/"+id).accept(MediaType.APPLICATION_JSON)).andReturn();
		System.out.println("mvcResult.getResponse() is:"+mvcResult.getResponse().getContentAsString());
		assertEquals("Expected code did not match", 200, mvcResult.getResponse().getStatus());
		JSONAssert.assertEquals(expectedJson, mvcResult.getResponse().getContentAsString(), false);
	}
	@Test
	public void whenXDeleteVehicle_thenReturnJson() throws Exception {
		String expectedJson = "1";
		long id=1;
		MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.delete("/vehicles/remove/"+id).accept(MediaType.APPLICATION_JSON)
				            ).andReturn();
		System.out.println("when whenXDeleteVehicle_thenReturnJson response string"+mvcResult.getResponse().getContentAsString());
		//Mockito.verify(vehicleService).create(Matchers.refEq(vehicle));
		assertEquals("Expected code did not match", 200, mvcResult.getResponse().getStatus());
		JSONAssert.assertEquals(expectedJson, mvcResult.getResponse().getContentAsString(), false);
	}
	

}
