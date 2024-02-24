package com.shubham.spring.carrentalservice.serviceInterface;

import org.springframework.stereotype.Service;

import com.shubham.spring.carrentalservice.entity.Customer;
import com.shubham.spring.carrentalservice.exception.CustomerException;
import com.shubham.spring.carrentalservice.exception.InvalidRegistrationException;
import com.shubham.spring.carrentalservice.model.CustomerInputModel;
import com.shubham.spring.carrentalservice.model.CustomerLoginInputModel;
import com.shubham.spring.carrentalservice.model.CustomerOutputModel;
@Service
public interface CustomerService {
	public CustomerOutputModel addCustomer(CustomerInputModel customerInputModel)
			throws CustomerException, InvalidRegistrationException;

	public Customer getCustomerByEmailId(String emailId) throws CustomerException;

	public boolean customerLogin(CustomerLoginInputModel customerLonginInputModel);
}
