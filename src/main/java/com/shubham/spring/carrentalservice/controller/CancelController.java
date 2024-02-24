package com.shubham.spring.carrentalservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.shubham.spring.carrentalservice.exception.InvalidCancellationException;
import com.shubham.spring.carrentalservice.exception.InvalidRentalIdException;
import com.shubham.spring.carrentalservice.model.CancelRentalOutputModel;
import com.shubham.spring.carrentalservice.serviceImplementation.CancelServiceImplementation;

@RestController
public class CancelController {
	
	@Autowired
	CancelServiceImplementation cancelService;
	
	Logger logger = LoggerFactory.getLogger(CancelController.class);

	@GetMapping("/cancelrentals/{rentalId}")
	public ResponseEntity<CancelRentalOutputModel> cancelRentals(@PathVariable("rentalId") Integer rentalId) throws InvalidCancellationException, InvalidRentalIdException {
		logger.info("Cancelling Rented Car");
		CancelRentalOutputModel cancelledRental = cancelService.cancelRentals(rentalId);
		logger.info("Cancelled");
		return new ResponseEntity<CancelRentalOutputModel>(cancelledRental, HttpStatus.OK);
	}
	
	@GetMapping("/getcancelledrentalsbyemailid/{emailId}")
	public ResponseEntity<List<CancelRentalOutputModel>> getCancelledRentalsByEmailId(@PathVariable String emailId) throws InvalidCancellationException{
		logger.info("Getting all Cancelled Rental for customer with emailId {}", emailId);
		List<CancelRentalOutputModel> list = cancelService.getCancelledRentalsByEmailId(emailId);
		logger.info("Fetched");
		return new ResponseEntity<List<CancelRentalOutputModel>>(list, HttpStatus.OK);
	}
}
