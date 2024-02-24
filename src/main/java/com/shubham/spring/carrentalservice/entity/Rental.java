package com.shubham.spring.carrentalservice.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="rentals")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rental {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer rentalId;
	
	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name = "car_id", nullable = false)
	private Car car;
	
	private LocalDate bookingDate;
	
	@Column(nullable = false)
	private LocalDate reservationStartDate;
	
	@Column(nullable = false)
	private LocalDate reservationEndDate;
	
	
	private Double totalCost;
	
	@Column(nullable = false)
	private String reservationStatus;
}
