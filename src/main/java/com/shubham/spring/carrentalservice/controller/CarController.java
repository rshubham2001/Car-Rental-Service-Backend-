package com.shubham.spring.carrentalservice.controller;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.shubham.spring.carrentalservice.entity.Car;
import com.shubham.spring.carrentalservice.exception.CarNotFoundException;
import com.shubham.spring.carrentalservice.serviceImplementation.CarServiceImplementation;

@RestController
public class CarController {

	@Autowired
	CarServiceImplementation carService;
	
	Logger logger = LoggerFactory.getLogger(CarController.class);
	
	@GetMapping("/getallcar")
	public ResponseEntity<List<Car>> getAllCar(){
		logger.info("Getting all Car");
		List<Car> carList = carService.getAllCar();
		logger.info("All Car Fetched");
		return new ResponseEntity<List<Car>>(carList, HttpStatus.OK);
	}
	
	@GetMapping("/getallavailablecar/{startDate}/{endDate}")
	public ResponseEntity<List<Car>> getAllAvailableCar(@PathVariable("startDate") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate startDate, @PathVariable("endDate") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate endDate) throws CarNotFoundException{
		logger.info("Getting all available car from {} to {}", startDate, endDate);
		List<Car> carList = carService.getAllAvailableCar(startDate, endDate);
		logger.info("All Car Fetched");
		return new ResponseEntity<List<Car>>(carList, HttpStatus.OK);
	}
	@GetMapping("/getallavailablecarbycartypeandcarmodel/{startDate}/{endDate}/{carType}/{carModel}")
	public ResponseEntity<List<Car>> getAvailableCarByCarTypeAndCarModel(@PathVariable("startDate") @DateTimeFormat(pattern="yyyy-MM-dd")LocalDate startDate, @PathVariable("endDate") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate endDate, @PathVariable("carType") String carType, @PathVariable("carModel") String carModel) throws CarNotFoundException{
		logger.info("Getting all available car from {} to {} with type: {} and model: {}", startDate, endDate,carType,carModel);
		List<Car> carList = carService.getAvailableCarByCarTypeAndCarModel(startDate,endDate, carType, carModel);
		logger.info("All Car Fetched");
		return new ResponseEntity<List<Car>>(carList, HttpStatus.OK);
	}
	@GetMapping("/getallavailablecarbycartype/{startDate}/{endDate}/{carType}")
	public ResponseEntity<List<Car>> getAvailableCarByCarType(@PathVariable("startDate") @DateTimeFormat(pattern="yyyy-MM-dd")LocalDate startDate, @PathVariable("endDate") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate endDate, @PathVariable("carType") String carType) throws CarNotFoundException{
		logger.info("Getting all available car from {} to {} with type: {}", startDate, endDate,carType);
		List<Car> carList = carService.getAvailableCarByCarType(startDate, endDate, carType);
		logger.info("All Car Fetched");
		return new ResponseEntity<List<Car>>(carList, HttpStatus.OK);
	}
	@GetMapping("/getallavailablecarbycarmodel/{startDate}/{endDate}/{carModel}")
	public ResponseEntity<List<Car>> getAvailableCarByCarModel(@PathVariable("startDate") @DateTimeFormat(pattern="yyyy-MM-dd")LocalDate startDate, @PathVariable("endDate") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate endDate, @PathVariable("carModel") String carModel) throws CarNotFoundException{
		logger.info("Getting all available car from {} to {} with model: {}", startDate, endDate,carModel);
		List<Car> carList = carService.getAvailableCarByCarModel(startDate, endDate, carModel);
		logger.info("All Car Fetched");
		return new ResponseEntity<List<Car>>(carList, HttpStatus.OK);
	}
}
