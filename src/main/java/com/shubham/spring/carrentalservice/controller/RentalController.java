package com.shubham.spring.carrentalservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shubham.spring.carrentalservice.exception.CarNotFoundException;
import com.shubham.spring.carrentalservice.exception.CustomerException;
import com.shubham.spring.carrentalservice.exception.InvalidRentalIdException;
import com.shubham.spring.carrentalservice.exception.WrongDateException;
import com.shubham.spring.carrentalservice.model.RentalInputModel;
import com.shubham.spring.carrentalservice.model.RentalOutputModel;
import com.shubham.spring.carrentalservice.serviceImplementation.RentalServiceImplementation;

@RestController
public class RentalController {

	@Autowired
	RentalServiceImplementation rentalService;
	
	Logger logger = LoggerFactory.getLogger(RentalController.class);
	
	@GetMapping("/getrentalbyid/{id}")
	public ResponseEntity<RentalOutputModel> getRentalById(@PathVariable("id") Integer id) throws InvalidRentalIdException {
		logger.info("Fetching Rental history by RentalId: {}", id);
		RentalOutputModel rentalOutputModel = rentalService.getRentalById(id);
		logger.info("Fetched");
		return new ResponseEntity<RentalOutputModel>(rentalOutputModel, HttpStatus.OK);
	}
	
	@GetMapping("/getrentalsbycustomerid/{emailId}")
	public ResponseEntity<List<RentalOutputModel>> getRentalsByCustomerId(@PathVariable("emailId") String emailId) throws InvalidRentalIdException{
		logger.info("Getting all Rental history for Customer with EmailId: {}",emailId);
		List<RentalOutputModel> rentalList = rentalService.getRentalsByCustomerId(emailId);
		logger.info("Rental History Fetched");
		return new ResponseEntity<List<RentalOutputModel>>(rentalList, HttpStatus.OK);
	}
	
	@PostMapping("/rentcar")
	public ResponseEntity<RentalOutputModel> rentCar(@RequestBody RentalInputModel rentalInputModel) throws CustomerException, CarNotFoundException, WrongDateException{
		logger.info("Renting car");
		RentalOutputModel rental = rentalService.rentCar(rentalInputModel);
		logger.info("Car Rented Successfully");
		return new ResponseEntity<RentalOutputModel>(rental, HttpStatus.ACCEPTED);
	}
	
}
