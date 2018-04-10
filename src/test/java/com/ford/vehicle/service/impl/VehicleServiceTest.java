/*package com.ford.vehicle.service.impl;

import org.hibernate.service.spi.ServiceException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.AdditionalMatchers;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.ford.vehicle.dao.VehicleRepository;
import com.ford.vehicle.domain.Vehicle;
import com.ford.vehicle.service.VehicleService;
@RunWith(SpringRunner.class)
public class VehicleServiceTest {

	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(VehicleServiceTest.class);
	
	@MockBean
	VehicleRepository vehicleReporsitory;

	@Autowired
	VehicleService vehicleService;
	
	@TestConfiguration
	static class VehicleServiceImplTestsConfiguration {
		
		@Bean
		public VehicleService vehicleService() {
			return new VehicleService();
		}
	}
	@Before
	public void setup() {
		Vehicle vehicle=new Vehicle(1, "Ford Focus", 2018, 250000, "NEW", "FORD");
		Mockito.when(vehicleReporsitory.findAll(Matchers.matches("1"))).thenReturn(vehicle);
		Mockito.when(vehicleReporsitory.findOne(AdditionalMatchers.not(Matchers.matches("VIN1")))).thenReturn(null);
	}

	@Test
	public void whenValidVin_thenReturnVehicle() {
		Vehicle vehicle = vehicleService.get("VIN1");
		assertEquals("Did not find vehicle", "VIN1", vehicle.getVin());		
	}
	
	
	@Test(expected=ServiceException.class)
	public void whenInvalidVin_thenThrowServiceException() {
		Vehicle vehicle = vehicleService.get("VIN2");
	}


}
*/