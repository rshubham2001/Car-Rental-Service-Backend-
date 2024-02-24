package com.shubham.spring.carrentalservice.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "customers")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	
	@Id
	@Column(length = 25)
	private String emailId;
	
	@Column(nullable = false,length=50)
	private String customerName;
	
	@Column(nullable = false,unique = true,length=10)
	private String phone;
	
	@Column(nullable = false,length=100)
	private String address;
	
	@Column(nullable = false,unique = true,length=16)
	private String drivingLicence;
	
	@Column(nullable = false)
	private LocalDate dateOfBirth;
	
	@Column(nullable = false,length=16)
	private String password;
}
