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
public class CustomerInputModel {
	
	private String emailId;
	private String customerName;
	private String Phone;
	private String address;
	private String drivingLicence;
	private LocalDate dateOfBirth;
	private String password;
}
