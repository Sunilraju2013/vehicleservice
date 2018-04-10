package com.ford.vehicle.domain;

import static org.junit.Assume.assumeFalse;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
@Entity
@Table(name = "vehicle")
public class Vehicle {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "vehicle_id",nullable=false)
	@NotNull
	private Long vehicleId;
	@NotNull
	@Column(name = "model")
	private String model;
	
	@Column(name = "year")
	private int year;
	
	@Column(name = "price")
	private long price;
	
	@Column(name = "vehicle_status")
	private String vehicleStatus;
	
	@Column(name = "dealer_name")
	private String dealerName;
	

	public Vehicle(long vehicleId, String model, int year, long price, String vehicleStatus, String dealerName) {
		super();
		this.vehicleId = vehicleId;
		this.model = model;
		this.year = year;
		this.price = price;
		this.vehicleStatus = vehicleStatus;
		this.dealerName = dealerName;
	}
public Vehicle() {
	// TODO Auto-generated constructor stub
}
	public Long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(long vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public String getVehicleStatus() {
		return vehicleStatus;
	}

	public void setVehicleStatus(String vehicleStatus) {
		this.vehicleStatus = vehicleStatus;
	}

	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	@Override
	public String toString() {
		return "Vehicle [vehicleId=" + vehicleId + ", model=" + model + ", year=" + year + ", price=" + price
				+ ", vehicleStatus=" + vehicleStatus + ", dealerName=" + dealerName + "]";
	}
	
}
