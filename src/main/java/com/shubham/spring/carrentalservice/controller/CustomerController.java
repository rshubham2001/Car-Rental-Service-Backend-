package com.shubham.spring.carrentalservice.controller;

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

import com.shubham.spring.carrentalservice.entity.Customer;
import com.shubham.spring.carrentalservice.exception.CustomerException;
import com.shubham.spring.carrentalservice.exception.InvalidRegistrationException;
import com.shubham.spring.carrentalservice.model.CustomerInputModel;
import com.shubham.spring.carrentalservice.model.CustomerLoginInputModel;
import com.shubham.spring.carrentalservice.model.CustomerOutputModel;
import com.shubham.spring.carrentalservice.serviceImplementation.CustomerServiceImplementation;


@RestController
public class CustomerController {

	@Autowired
	CustomerServiceImplementation customerService;
	
	Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	@PostMapping("/addcustomer")
	public ResponseEntity<CustomerOutputModel> addCustomer(@RequestBody CustomerInputModel customerModel) throws CustomerException, InvalidRegistrationException{
		logger.info("Adding Customer to Database");
		CustomerOutputModel customer = customerService.addCustomer(customerModel);
		logger.info("Added");
		return new ResponseEntity<CustomerOutputModel>(customer, HttpStatus.OK);
	}
	@GetMapping("/getcustomerbyid/{emailId}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable("emailId") String emailId) throws CustomerException{
		logger.info("Fetching Customer with emailId: {}", emailId);
		Customer customer = customerService.getCustomerByEmailId(emailId);
		logger.info("Fetched");
		return new ResponseEntity<Customer>(customer,HttpStatus.OK);
	}
	@PostMapping("/customerlogin")
	public ResponseEntity<Boolean> customerLogin(@RequestBody CustomerLoginInputModel loginInputModel) {
		logger.info("Verifying Customer");
		boolean result = customerService.customerLogin(loginInputModel);
		logger.info("Verifying Status: {}", result);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}
}
