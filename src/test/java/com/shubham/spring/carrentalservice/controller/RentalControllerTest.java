package com.shubham.spring.carrentalservice.controller;

import com.shubham.spring.carrentalservice.exception.CarNotFoundException;
import com.shubham.spring.carrentalservice.exception.CustomerException;
import com.shubham.spring.carrentalservice.exception.InvalidRentalIdException;
import com.shubham.spring.carrentalservice.exception.WrongDateException;
import com.shubham.spring.carrentalservice.model.RentalInputModel;
import com.shubham.spring.carrentalservice.model.RentalOutputModel;
import com.shubham.spring.carrentalservice.serviceImplementation.RentalServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RentalControllerTest {

	@Mock
	private RentalServiceImplementation rentalService;

	@Mock
	private Logger logger;

	@InjectMocks
	private RentalController rentalController;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetRentalById() throws InvalidRentalIdException {
		// Mock data
		int rentalId = 1;
		RentalOutputModel rentalOutputModel = new RentalOutputModel();

		// Mock dependencies
		when(rentalService.getRentalById(rentalId)).thenReturn(rentalOutputModel);

		// Invoke the method
		ResponseEntity<RentalOutputModel> response = rentalController.getRentalById(rentalId);

		// Verify interactions and assertions
		verify(logger).info("Fetching Rental history by RentalId: {}", rentalId);
		verify(logger).info("Fetched");
		verify(rentalService).getRentalById(rentalId);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(rentalOutputModel, response.getBody());
	}

	@Test
	void testGetRentalsByCustomerId() throws InvalidRentalIdException {
		// Mock data
		String emailId = "test@example.com";
		List<RentalOutputModel> rentalList = new ArrayList<>();

		// Mock dependencies
		when(rentalService.getRentalsByCustomerId(emailId)).thenReturn(rentalList);

		// Invoke the method
		ResponseEntity<List<RentalOutputModel>> response = rentalController.getRentalsByCustomerId(emailId);

		// Verify interactions and assertions
		verify(logger).info("Getting all Rental history for Customer with EmailId: {}", emailId);
		verify(logger).info("Rental History Fetched");
		verify(rentalService).getRentalsByCustomerId(emailId);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(rentalList, response.getBody());
	}

	@Test
	void testRentCar() throws CustomerException, CarNotFoundException, WrongDateException {
		// Mock data
		RentalInputModel rentalInputModel = new RentalInputModel();
		RentalOutputModel rentalOutputModel = new RentalOutputModel();

		// Mock dependencies
		when(rentalService.rentCar(rentalInputModel)).thenReturn(rentalOutputModel);

		// Invoke the method
		ResponseEntity<RentalOutputModel> response = rentalController.rentCar(rentalInputModel);

		// Verify interactions and assertions
		verify(logger).info("Renting car");
		verify(logger).info("Car Rented Successfully");
		verify(rentalService).rentCar(rentalInputModel);
		assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
		assertEquals(rentalOutputModel, response.getBody());
	}
}
