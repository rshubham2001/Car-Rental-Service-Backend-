package com.shubham.spring.carrentalservice.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CancelRentalOutputModel {

	private Integer cancelledId;
	private Integer rentalId;
	private String customerName;
	private String carId;
	private LocalDate bookingDate;
	private LocalDate cancelletionDate;
	private Double totalCost;
	private Double refundAmount;
	private String reservationStatus;
}
