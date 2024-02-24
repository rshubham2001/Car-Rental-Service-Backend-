package com.shubham.spring.carrentalservice.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shubham.spring.carrentalservice.entity.Customer;
import com.shubham.spring.carrentalservice.exception.CustomerException;
import com.shubham.spring.carrentalservice.exception.InvalidRegistrationException;
import com.shubham.spring.carrentalservice.model.CustomerInputModel;
import com.shubham.spring.carrentalservice.model.CustomerLoginInputModel;
import com.shubham.spring.carrentalservice.model.CustomerOutputModel;
import com.shubham.spring.carrentalservice.repository.CustomerRepository;
import com.shubham.spring.carrentalservice.serviceInterface.CustomerService;
import com.shubham.spring.carrentalservice.util.MapCustomer;
import com.shubham.spring.carrentalservice.validations.CustomerValidation;

import jakarta.transaction.Transactional;

@Service
public class CustomerServiceImplementation implements CustomerService{

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	CustomerValidation customerValidation;
	

	@Transactional
	public CustomerOutputModel addCustomer(CustomerInputModel customerInputModel) throws CustomerException, InvalidRegistrationException {
		if(!customerValidation.checkEmail(customerInputModel.getEmailId())) {
			throw new InvalidRegistrationException("Invalid Email");
		}
		if(!customerValidation.checkName(customerInputModel.getCustomerName())) {
			throw new InvalidRegistrationException("Name Should contain only alphabets");
		}
		if(!customerValidation.checkDrivingLicence(customerInputModel.getDrivingLicence())) {
			throw new InvalidRegistrationException("Invalid Driving Licence");
		}
		if(!customerValidation.checkPhone(customerInputModel.getPhone())) {
			throw new InvalidRegistrationException("Invalid Phone Number");
		}
		if(!customerValidation.validatePassword(customerInputModel.getPassword())) {
			throw new InvalidRegistrationException("Password should be 8 to 16 characters and should contains alphanumeric");
		}
		
		if(customerRepository.findByEmailId(customerInputModel.getEmailId())) {
			throw new CustomerException("This EmailId is already used");
		}
		else if(customerRepository.findByDL(customerInputModel.getDrivingLicence())) {
			throw new CustomerException("This Driving Licence is already used");
		}
		else if(customerRepository.findByPhone(customerInputModel.getPhone())) {
			throw new CustomerException("This Phone Number is already used");
		}
		else {

			Customer customer = new Customer();

			customer.setEmailId(customerInputModel.getEmailId());
			customer.setAddress(customerInputModel.getAddress());
			customer.setCustomerName(customerInputModel.getCustomerName());
			customer.setDrivingLicence(customerInputModel.getDrivingLicence());
			customer.setDateOfBirth(customerInputModel.getDateOfBirth());
			customer.setPassword(customerInputModel.getPassword());
			customer.setPhone(customerInputModel.getPhone());
			customerRepository.save(customer);
			
			CustomerOutputModel customerOutputModel = MapCustomer.mapToCustomerOutput(customer);
			
			return customerOutputModel;
		}
	}

	@Transactional
	public Customer getCustomerByEmailId(String emailId) throws CustomerException {
		Customer customer = customerRepository.findById(emailId).orElse(null);
		if (customer != null) {
			return customer;
		}
		throw new CustomerException("Coustomer Not Found");

	}

	@Transactional
	public boolean customerLogin(CustomerLoginInputModel customerLoginInputModel) {
		String emailId = customerLoginInputModel.getEmailId();
		String password = customerLoginInputModel.getPassword();
		Customer customer = customerRepository.customerLogin(emailId, password);
		return (customer == null ? false : true);
	}
}
