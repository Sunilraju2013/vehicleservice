package com.ford.vehicle.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ford.vehicle.dao.VehicleRepository;
import com.ford.vehicle.domain.Vehicle;

@Service
public class VehicleService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private VehicleRepository vehicleRepository;
	public List<Vehicle> getVehicles() {
		
		return vehicleRepository.findAll();
	}
	
	public String createVechicle(Vehicle vehicle) {
		System.out.println("inside createVechicle"+vehicle.toString());
		Vehicle temp=vehicleRepository.save(vehicle);
		System.out.println("valure for it"+Long.toString(temp.getVehicleId()));
		return Long.toString(temp.getVehicleId());
		
	}
	public String updateVechicle(Vehicle vehicle) {
		System.out.println("inside updateVechicle");
		Vehicle temp=vehicleRepository.save(vehicle);
		System.out.println("valure for it"+Long.toString(temp.getVehicleId()));
		return Long.toString(temp.getVehicleId());
		
	}
	
	public String deleteVehicleData(Long id) {
		vehicleRepository.deleteById(id);
		return Long.toString(id);
	}
	
	
	
	public Optional<Vehicle> getVehicleById(long id){
		
		return vehicleRepository.findById(id);
	}
	
	

}
