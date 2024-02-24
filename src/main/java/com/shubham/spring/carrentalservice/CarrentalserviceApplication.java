package com.shubham.spring.carrentalservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.shubham.spring.carrentalservice.exception.CarNotFoundException;
import com.shubham.spring.carrentalservice.exception.CustomerException;
import com.shubham.spring.carrentalservice.exception.WrongDateException;

@SpringBootApplication
public class CarrentalserviceApplication {

	public static void main(String[] args) throws CustomerException, CarNotFoundException, WrongDateException {
		SpringApplication.run(CarrentalserviceApplication.class, args);

	}

}
