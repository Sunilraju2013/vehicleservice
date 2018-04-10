package com.ford.vehicle.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ford.vehicle.domain.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
	

}
