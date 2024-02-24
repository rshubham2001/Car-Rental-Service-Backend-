package com.shubham.spring.carrentalservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackInputModel {

	private String emailId;
	private Integer rentalId;
	private String message;
}
