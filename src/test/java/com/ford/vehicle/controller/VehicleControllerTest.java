package com.ford.vehicle.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ford.vehicle.domain.Vehicle;
import com.ford.vehicle.service.ServiceException;
import com.ford.vehicle.service.VehicleService;
@RunWith(SpringRunner.class)
@WebMvcTest(value=VehicleController.class, secure=false)
public class VehicleControllerTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(VehicleControllerTest.class);
	@Autowired
	private MockMvc mockMvc;
	private Vehicle validVehicle=new Vehicle(1, "Ford Focus", 2018, 250000, "NEW", "FORD");
	/*private Vehicle invalidVehicle=new Vehicle(2, null, 2018, 250000, "NEW", "FORD");*/
	private String vehicleJson = "{" + 
			"\"model\":\"FORD Focus\"," + 
			"\"year\":2018," + 
			"\"price\":250000," + 
			"\"vehicleStatus\":\"NEW\"," + 
			"\"dealerName\":\"FORD\"" + 
			"}";
	
	private String InvalidVehicleJson ="{" + 
			"\"year\":2018," + 
			"\"price\":250000," + 
			"\"vehicleStatus\":\"NEW\"," + 
			"\"dealerName\":\"FORD\"" + 
			"}";
	private String updateVehicleJson = "{" + 
			"\"vehicleId\":1,"+
			"\"model\":\"FORD Focus\"," + 
			"\"year\":2018," + 
			"\"price\":250000," + 
			"\"vehicleStatus\":\"NEW\"," + 
			"\"dealerName\":\"FORD\"" + 
			"}";
	private String InvalidupdateVehicleJson = "{" + 
			"\"model\":\"FORD Focus\"," +
			"\"year\":2018," + 
			"\"price\":250000," + 
			"\"vehicleStatus\":\"NEW\"," + 
			"\"dealerName\":\"FORD\"" + 
			"}";
	@MockBean
	private VehicleService vehicleService;
	
	
	@Test
	public void givenValidVehicleJson_whenCreateVehicle_thenReturnJson() throws Exception {
		String expectedJson = "1";
		LOGGER.debug("inside givenValidVehicleJson_whenCreateVehicle_thenReturnJson");
		when(vehicleService.createVechicle(Mockito.any(Vehicle.class))).thenReturn("1");
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/vehicles/").accept(MediaType.APPLICATION_JSON)
				.content(vehicleJson).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println("response status"+response.getContentAsString());
		//Mockito.verify(vehicleService).create(Matchers.refEq(vehicle));
		assertEquals("Expected code did not match", 200, response.getStatus());
		JSONAssert.assertEquals(expectedJson, response.getContentAsString(), false);
	}
	@Test
	public void givenValidVehicleJson_whenUpdateVehicle_thenReturnJson() throws Exception {
		String expectedJson = "1";
		LOGGER.debug("inside givenValidVehicleJson_whenUpdateVehicle_thenReturnJson");
		when(vehicleService.createVechicle(Mockito.any(Vehicle.class))).thenReturn("1");
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/vehicles/").accept(MediaType.APPLICATION_JSON)
				.content(updateVehicleJson).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println("response status"+response.getContentAsString());
		//Mockito.verify(vehicleService).create(Matchers.refEq(vehicle));
		assertEquals("Expected code did not match", 200, response.getStatus());
		JSONAssert.assertEquals(expectedJson, response.getContentAsString(), false);
	}
	@Test
	public void givenInValidVehicleJson_whenCreateVehicle_thenReturnJson() throws Exception {
		String expectedJson =  " {" + 
				"\"errorCode\": \"400\"," + 
				"\"message\": \"Bad Request\"" + 
				"}";
		LOGGER.debug("inside givenInValidVehicleJson_whenCreateVehicle_thenReturnJson");
		when(vehicleService.createVechicle(org.mockito.hamcrest.MockitoHamcrest.<Vehicle>argThat(
			 org.hamcrest.CoreMatchers.allOf(
			org.hamcrest.Matchers.<Vehicle>
			hasProperty("model", org.hamcrest.CoreMatchers.nullValue()))))).
			thenReturn(expectedJson);
//			thenThrow(new ServiceException(HttpStatus.BAD_REQUEST, "Bad Request"));
			RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/vehicles/").accept(MediaType.APPLICATION_JSON)
					.content(InvalidVehicleJson).contentType(MediaType.APPLICATION_JSON);
			MvcResult result = (MvcResult) mockMvc.perform(requestBuilder).andReturn();
			System.out.println("Result Response:"+result.getResponse().getStatus());
			System.out.println("Result Response getContentAsString:"+result.getResponse().getContentAsString());
			MockHttpServletResponse response = result.getResponse();
			assertEquals("Expected code did not match", 400, response.getStatus());
			JSONAssert.assertEquals(expectedJson, response.getContentAsString(), false);
	}
	
	@Test
	public void givenInValidVehicleJson_whenUpdateVehicle_thenReturnJson() throws Exception {
		String expectedJson =  " {" + 
				"\"errorCode\": \"400\"," + 
				"\"message\": \"Bad Request\"" + 
				"}";
		LOGGER.debug("inside givenInValidVehicleJson_whenUpdateVehicle_thenReturnJson");
		when(vehicleService.createVechicle(org.mockito.hamcrest.MockitoHamcrest.<Vehicle>argThat(
						 org.hamcrest.CoreMatchers.allOf(
						org.hamcrest.Matchers.<Vehicle>
						hasProperty("vehicleId", org.hamcrest.CoreMatchers.nullValue()))))).
		thenReturn(expectedJson);
//		thenThrow(new ServiceException("Invalid Vehicle Model", "INVALID_VEHICLE"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/vehicles/").accept(MediaType.APPLICATION_JSON)
		.content(InvalidupdateVehicleJson).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = (MvcResult) mockMvc.perform(requestBuilder).andReturn();
		System.out.println("Result Response:"+result.getResponse().getStatus());	
		System.out.println("Result Response getContentAsString:"+result.getResponse().getContentAsString());
		MockHttpServletResponse response = result.getResponse();
		assertEquals("Expected code did not match", 400, response.getStatus());
		JSONAssert.assertEquals(expectedJson, response.getContentAsString(), false);
	}
	@Test
	public void givenValidVehicleJson_whenGetVehicle_thenReturnJson() throws Exception {
		List<Vehicle> vehicleList=new ArrayList<Vehicle>();
		vehicleList.add(validVehicle);
		String expectedJson = "[{" + 
				"\"vehicleId\":1,"+
				"\"model\":\"Ford Focus\"," + 
				"\"year\":2018," + 
				"\"price\":250000," + 
				"\"vehicleStatus\":\"NEW\"," + 
				"\"dealerName\":\"FORD\"" + 
				"}]";
		LOGGER.debug("inside givenValidVehicleJson_whenGetVehicle_thenReturnJson");
		when(vehicleService.getVehicles()).thenReturn(vehicleList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/vehicles/").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println("response status"+response.getContentAsString());
		//Mockito.verify(vehicleService).create(Matchers.refEq(vehicle));
		assertEquals("Expected code did not match", 200, response.getStatus());
		JSONAssert.assertEquals(expectedJson, response.getContentAsString(), false);
	}
	@Test
	public void givenValidVehicleJson_whenGetVehicleById_thenReturnJson() throws Exception {
		long id=1;
		String expectedJson = "{" + 
				"\"vehicleId\":1,"+
				"\"model\":\"Ford Focus\"," + 
				"\"year\":2018," + 
				"\"price\":250000," + 
				"\"vehicleStatus\":\"NEW\"," + 
				"\"dealerName\":\"FORD\"" + 
				"}";
		LOGGER.debug("inside givenValidVehicleJson_whenGetVehicleById_thenReturnJson");
		when(vehicleService.getVehicleById(Mockito.any(Long.class))).thenReturn(Optional.of(validVehicle));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/vehicles/"+id).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println("response status"+response.getContentAsString());
		//Mockito.verify(vehicleService).create(Matchers.refEq(vehicle));
		assertEquals("Expected code did not match", 200, response.getStatus());
		JSONAssert.assertEquals(expectedJson, response.getContentAsString(), false);
	}
	@Test
	public void givenInValidVehicleJson_whenGetVehicleById_thenReturnJson() throws Exception {
		LOGGER.debug("inside givenInValidVehicleJson_whenGetVehicleById_thenReturnJson");
		when(vehicleService.getVehicleById(Mockito.any(Long.class))).thenReturn(Optional.of(validVehicle));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/vehicles/"+"a").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println("response status"+response.getContentAsString());
		//Mockito.verify(vehicleService).create(Matchers.refEq(vehicle));
		assertEquals("Expected code did not match", 400, response.getStatus());
//		JSONAssert.assertEquals(expectedJson, response.getContentAsString(), false);
	}
	@Test
	public void givenValidVehicleJson_whenDeleteVehicle_thenReturnJson() throws Exception {
		String expectedJson = "1";
		long id=1;
		
		LOGGER.debug("inside givenValidVehicleJson_whenDeleteVehicle_thenReturnJson");
		when(vehicleService.deleteVehicleData(Mockito.any(Long.class))).thenReturn("1");
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/vehicles/remove/"+id);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println("response status"+response.getContentAsString());
		//Mockito.verify(vehicleService).create(Matchers.refEq(vehicle));
		assertEquals("Expected code did not match", 200, response.getStatus());
		JSONAssert.assertEquals(expectedJson, response.getContentAsString(), false);
	}
	
	
	
}
