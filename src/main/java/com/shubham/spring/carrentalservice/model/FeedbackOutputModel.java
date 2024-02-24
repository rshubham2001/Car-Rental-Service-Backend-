package com.shubham.spring.carrentalservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackOutputModel {

	private Integer feedbackId;
	private String customerName;
	private Integer rentalId;
	private String feedback;
}
