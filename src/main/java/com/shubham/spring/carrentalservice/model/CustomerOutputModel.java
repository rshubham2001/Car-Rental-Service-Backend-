package com.shubham.spring.carrentalservice.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CustomerOutputModel {
	
	private String emailId;
	private String customerName;
	private String phone;
	private String drivingLicence;
	private LocalDate dateOfBirth;
	private String address;

}
