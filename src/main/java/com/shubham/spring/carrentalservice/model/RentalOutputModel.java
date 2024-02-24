package com.shubham.spring.carrentalservice.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalOutputModel {

	private Integer rentalId;
	private String customerId;
	private String customerName;
	private String carId;
	private String carMake;
	private String carModel;
	private String carType;
	private LocalDate bookingDate;
	private Long resrvationDuration;
	private Double totalCost;
	private String reservationStatus;
}
