package com.shubham.spring.carrentalservice.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalInputModel {

	private String emailId;
	private LocalDate reservationStartDate;
	private LocalDate reservationEndDate;
	private String carId;
}
