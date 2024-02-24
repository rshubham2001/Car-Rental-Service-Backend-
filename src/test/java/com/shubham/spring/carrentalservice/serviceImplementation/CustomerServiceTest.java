package com.shubham.spring.carrentalservice.serviceImplementation;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.shubham.spring.carrentalservice.entity.Customer;
import com.shubham.spring.carrentalservice.exception.CustomerException;
import com.shubham.spring.carrentalservice.exception.InvalidRegistrationException;
import com.shubham.spring.carrentalservice.model.CustomerInputModel;
import com.shubham.spring.carrentalservice.model.CustomerLoginInputModel;
import com.shubham.spring.carrentalservice.model.CustomerOutputModel;
import com.shubham.spring.carrentalservice.repository.CustomerRepository;
import com.shubham.spring.carrentalservice.validations.CustomerValidation;

@SpringBootTest
public class CustomerServiceTest {
	@Mock
	private CustomerRepository customerRepository;

	@Mock
	private CustomerValidation customerValidation;

	@InjectMocks
	private CustomerServiceImplementation customerService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void addCustomer_ValidInput_SuccessfulRegistration() throws CustomerException, InvalidRegistrationException {
		// Arrange
		CustomerInputModel customerInputModel = new CustomerInputModel();
		customerInputModel.setEmailId("test@example.com");
		customerInputModel.setAddress("Address");
		customerInputModel.setCustomerName("John Doe");
		customerInputModel.setDrivingLicence("DL13 20200034761");
		customerInputModel.setDateOfBirth(LocalDate.of(2000, 06, 06));
		customerInputModel.setPassword("password123");
		customerInputModel.setPhone("1234567890");

		when(customerValidation.checkEmail(customerInputModel.getEmailId())).thenReturn(true);
        when(customerValidation.checkName(customerInputModel.getCustomerName())).thenReturn(true);
        when(customerValidation.checkDrivingLicence(customerInputModel.getDrivingLicence())).thenReturn(true);
        when(customerValidation.checkPhone(customerInputModel.getPhone())).thenReturn(true);
        when(customerValidation.validatePassword(customerInputModel.getPassword())).thenReturn(true);
        when(customerRepository.findByEmailId(customerInputModel.getEmailId())).thenReturn(false);
        when(customerRepository.findByDL(customerInputModel.getDrivingLicence())).thenReturn(false);
        when(customerRepository.findByPhone(customerInputModel.getPhone())).thenReturn(false);

		// Act
        CustomerOutputModel result = customerService.addCustomer(customerInputModel);

		// Assert
		assertNotNull(result);
		
		verify(customerRepository, times(1)).save(any(Customer.class));
	}

	@Test
	void addCustomer_InvalidEmail_ThrowsInvalidRegistrationException()
			throws CustomerException, InvalidRegistrationException {
		// Arrange
		CustomerInputModel customerInputModel = new CustomerInputModel();
		customerInputModel.setEmailId("invalid-email");
		// Set other properties as required for testing other scenarios

		when(customerValidation.checkEmail(customerInputModel.getEmailId())).thenReturn(false);

		// Act and Assert
		assertThrows(InvalidRegistrationException.class, () -> customerService.addCustomer(customerInputModel));
	}


	@Test
	void getCustomerByEmailId_ExistingEmail_ReturnsCustomer() throws CustomerException {
		// Arrange
		String emailId = "test@example.com";
		when(customerRepository.findById(emailId)).thenReturn(java.util.Optional.of(new Customer()));

		// Act
		Customer result = customerService.getCustomerByEmailId(emailId);

		// Assert
		assertNotNull(result);
	}


	@Test
	void customerLogin_ValidCredentials_ReturnsTrue() {
		// Arrange
		CustomerLoginInputModel customerLoginInputModel = new CustomerLoginInputModel();
		customerLoginInputModel.setEmailId("test@example.com");
		customerLoginInputModel.setPassword("password123");

		when(customerRepository.customerLogin(customerLoginInputModel.getEmailId(),
				customerLoginInputModel.getPassword())).thenReturn(new Customer());

		// Act
		boolean result = customerService.customerLogin(customerLoginInputModel);

		// Assert
		assertTrue(result);
	}
}
