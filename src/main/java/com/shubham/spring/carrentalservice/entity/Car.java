package com.shubham.spring.carrentalservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="cars")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {

	@Id
	@Column(length=5)
	private String carId;
	
	@Column(nullable = false,length=50)
	private String carMake;
	
	@Column(nullable = false,length=50)
	private String carModel;
	
	@Column(nullable = false,length=50)
	private String carType;
	
	@Column(nullable = false)
	private Integer capacity;
	
	@Column(nullable = false)
	private Double carRentalRate;
	
	
}
