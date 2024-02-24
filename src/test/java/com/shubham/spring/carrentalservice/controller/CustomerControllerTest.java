package com.shubham.spring.carrentalservice.controller;

import com.shubham.spring.carrentalservice.entity.Customer;
import com.shubham.spring.carrentalservice.exception.CustomerException;
import com.shubham.spring.carrentalservice.exception.InvalidRegistrationException;
import com.shubham.spring.carrentalservice.model.CustomerInputModel;
import com.shubham.spring.carrentalservice.model.CustomerLoginInputModel;
import com.shubham.spring.carrentalservice.model.CustomerOutputModel;
import com.shubham.spring.carrentalservice.serviceImplementation.CustomerServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class CustomerControllerTest {

    @Mock
    private CustomerServiceImplementation customerService;

    @Mock
    private Logger logger;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddCustomer() throws CustomerException, InvalidRegistrationException {
        // Arrange
        CustomerInputModel customerInputModel = new CustomerInputModel();
        CustomerOutputModel expectedCustomerOutputModel = new CustomerOutputModel();
        when(customerService.addCustomer(any())).thenReturn(expectedCustomerOutputModel);

        // Act
        ResponseEntity<CustomerOutputModel> response = customerController.addCustomer(customerInputModel);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedCustomerOutputModel, response.getBody());
        verify(logger).info(eq("Adding Customer to Database"));
        verify(logger).info(eq("Added"));
        verify(customerService).addCustomer(eq(customerInputModel));
    }

    @Test
    void testGetCustomerById() throws CustomerException {
        // Arrange
        String emailId = "test@example.com";
        Customer expectedCustomer = new Customer();
        when(customerService.getCustomerByEmailId(eq(emailId))).thenReturn(expectedCustomer);

        // Act
        ResponseEntity<Customer> response = customerController.getCustomerById(emailId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedCustomer, response.getBody());
        verify(logger).info("Fetching Customer with emailId: {}", emailId);
        verify(logger).info("Fetched");
        verify(customerService).getCustomerByEmailId(eq(emailId));
    }

    @Test
    void testCustomerLogin() {
        // Arrange
        CustomerLoginInputModel loginInputModel = new CustomerLoginInputModel();
        boolean expectedResult = true;
        when(customerService.customerLogin(any())).thenReturn(expectedResult);

        // Act
        ResponseEntity<Boolean> response = customerController.customerLogin(loginInputModel);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResult, response.getBody());
        verify(logger).info("Verifying Customer");
        verify(logger).info("Verifying Status: {}", expectedResult);
        verify(customerService).customerLogin(eq(loginInputModel));
    }
}
