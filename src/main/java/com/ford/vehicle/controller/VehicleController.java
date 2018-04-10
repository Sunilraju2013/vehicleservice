package com.ford.vehicle.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ford.vehicle.domain.Vehicle;
import com.ford.vehicle.service.ServiceException;
import com.ford.vehicle.service.VehicleService;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private VehicleService vehicleService;
	
	@GetMapping("/")
	public List<Vehicle> vehicles(){
		System.out.println("inside vehicles method");
		return vehicleService.getVehicles();
	}
	@PostMapping("/")
	//@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Object> createVehicle(@RequestBody Vehicle vehicle) {
		System.out.println("inside createVechicle model value"+vehicle.getModel());
		System.out.println("inside createVechicle VehicleId value"+vehicle.getVehicleId());
		if(vehicle.getModel()==null)
//			throw new ServiceException(HttpStatus.BAD_REQUEST,"Bad Request");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ServiceException("Bad Request","400")); 
		
//		return vehicleService.createVechicle(vehicle);
		return ResponseEntity.ok(vehicleService.createVechicle(vehicle));
	}
	
	@PutMapping("/")
	public ResponseEntity<Object> updateVehicle(@RequestBody Vehicle vehicle) {
		System.out.println("inside updateVehicle");
		if(vehicle.getVehicleId()==null)
//			throw new ServiceException(HttpStatus.BAD_REQUEST,"Bad Request");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ServiceException("Bad Request","400"));
		return ResponseEntity.ok(vehicleService.updateVechicle(vehicle));	
	}
	
	@GetMapping("/{id}")
	public Optional<Vehicle> vehicleById(@PathVariable long id){
		
		return vehicleService.getVehicleById(id);
	}
	@DeleteMapping("/remove/{id}")
	public String deleteVehicle(@PathVariable long id) {
		System.out.println("inside vehiclecontroller deleteVehicle");
		return vehicleService.deleteVehicleData(id);

	}
	
	

	

}
