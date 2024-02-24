package com.shubham.spring.carrentalservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.shubham.spring.carrentalservice.entity.Car;
import com.shubham.spring.carrentalservice.exception.CarNotFoundException;
import com.shubham.spring.carrentalservice.serviceImplementation.CarServiceImplementation;

import ch.qos.logback.classic.Logger;

public class CarControllerTest {
	@Mock
	private CarServiceImplementation carService;

	@Mock
	private Logger logger;

	@InjectMocks
	private CarController carController;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetAllCar() {
		// Prepare expected output
		List<Car> expectedOutput = new ArrayList<>();
		// ... add cars to expectedOutput

		// Configure the mock behavior
		when(carService.getAllCar()).thenReturn(expectedOutput);

		// Invoke the method being tested
		ResponseEntity<List<Car>> response = carController.getAllCar();

		// Verify interactions and assertions
		verify(logger).info("Getting all Car");
		verify(logger).info("All Car Fetched");
		verify(carService).getAllCar();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(expectedOutput, response.getBody());
	}

	@Test
	void testGetAllAvailableCar() throws CarNotFoundException {
		// Prepare input       
		LocalDate startDate = LocalDate.of(2023, 6, 1);
		LocalDate endDate = LocalDate.of(2023, 6, 10);
		// Prepare expected output       
		List<Car> expectedOutput = new ArrayList<>();
		// ... add available cars to expectedOutput
		// Configure the mock behavior       
		when(carService.getAllAvailableCar(startDate, endDate)).thenReturn(expectedOutput);
		// Invoke the method being tested       
		ResponseEntity<List<Car>> response = carController.getAllAvailableCar(startDate, endDate);
		// Verify interactions and assertions       
		verify(logger).info("Getting all available car from {} to {}", startDate, endDate);
		verify(logger).info("All Car Fetched");
		verify(carService).getAllAvailableCar(startDate, endDate);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(expectedOutput, response.getBody());
	}

	@Test
	void testGetAvailableCarByCarTypeAndCarModel() throws CarNotFoundException {
		// Prepare input       
		LocalDate startDate = LocalDate.of(2023, 6, 1);
		LocalDate endDate = LocalDate.of(2023, 6, 10);
		String carType = "SUV";
		String carModel = "X5";
		// Prepare expected output       
		List<Car> expectedOutput = new ArrayList<>();
		// ... add available cars of the specified type and model to expectedOutput
		// Configure the mock behavior       
		when(carService.getAvailableCarByCarTypeAndCarModel(startDate, endDate, carType, carModel))
				.thenReturn(expectedOutput);
		// Invoke the method being tested       
		ResponseEntity<List<Car>> response = carController.getAvailableCarByCarTypeAndCarModel(startDate, endDate,
				carType, carModel);
		// Verify interactions and assertions       
		verify(logger).info("Getting all available car from {} to {} with type: {} and model: {}", startDate, endDate,
				carType, carModel);
		verify(logger).info("All Car Fetched");
		verify(carService).getAvailableCarByCarTypeAndCarModel(startDate, endDate, carType, carModel);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(expectedOutput, response.getBody());
	}
	
	@Test
	void testGetAvailableCarByCarType() throws CarNotFoundException { 
		// Prepare input        
		LocalDate startDate = LocalDate.of(2023, 6, 1); 
		LocalDate endDate = LocalDate.of(2023, 6, 10);
		String carType = "SUV";

		// Prepare expected output        
		List<Car> expectedOutput = new ArrayList<>();
		// ... add available cars of the specified type to expectedOutput
// Configure the mock behavior        
		when(carService.getAvailableCarByCarType(startDate, endDate, carType)).thenReturn(expectedOutput);
		 // Invoke the method being tested        
		ResponseEntity<List<Car>> response = carController.getAvailableCarByCarType(startDate, endDate, carType);
		// Verify interactions and assertions        
		verify(logger).info("Getting all available car from {} to {} with type: {}", startDate, endDate, carType); 
		verify(logger).info("All Car Fetched");
		verify(carService).getAvailableCarByCarType(startDate, endDate, carType); 
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(expectedOutput, response.getBody());
		}
	
	@Test 
	void testGetAvailableCarByCarModel() throws CarNotFoundException {
		// Prepare input        
		LocalDate startDate = LocalDate.of(2023, 6, 1); 
		LocalDate endDate = LocalDate.of(2023, 6, 10); 
		String carModel = "X5";

		 // Prepare expected output        
		List<Car> expectedOutput = new ArrayList<>();
		// ... add available cars of the specified model to expectedOutput
		// Configure the mock behavior        
		when(carService.getAvailableCarByCarModel(startDate, endDate, carModel)).thenReturn(expectedOutput);
		 // Invoke the method being tested        
		ResponseEntity<List<Car>> response = carController.getAvailableCarByCarModel(startDate, endDate, carModel);
		 // Verify interactions and assertions        
		verify(logger).info("Getting all available car from {} to {} with model: {}", startDate, endDate, carModel);
		verify(logger).info("All Car Fetched"); 
		verify(carService).getAvailableCarByCarModel(startDate, endDate, carModel);
		assertEquals(HttpStatus.OK, response.getStatusCode()); 
		assertEquals(expectedOutput, response.getBody()); 
		}
}
