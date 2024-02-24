package com.shubham.spring.carrentalservice.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="cancelled_rentals")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CancelledRental {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cancelledId;
	
	@OneToOne
	@JoinColumn(name="rental_id")
	private Rental rental;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@Column(nullable = false)
	private LocalDate cancelledDate;
	
	@Column(nullable = false)
	private double refundAmount;
	
	
}
