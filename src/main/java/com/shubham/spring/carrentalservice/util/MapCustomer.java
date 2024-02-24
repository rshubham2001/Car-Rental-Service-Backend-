package com.shubham.spring.carrentalservice.util;

import com.shubham.spring.carrentalservice.entity.Customer;
import com.shubham.spring.carrentalservice.model.CustomerOutputModel;

public class MapCustomer {
	
	public static CustomerOutputModel mapToCustomerOutput(Customer customer) {
		CustomerOutputModel customerOutputModel = new CustomerOutputModel();
		customerOutputModel.setAddress(customer.getAddress());
		customerOutputModel.setCustomerName(customer.getCustomerName());
		customerOutputModel.setDateOfBirth(customer.getDateOfBirth());
		customerOutputModel.setDrivingLicence(customer.getDrivingLicence());
		customerOutputModel.setEmailId(customer.getEmailId());
		customerOutputModel.setPhone(customer.getPhone());
		
		return customerOutputModel;
	}

}
