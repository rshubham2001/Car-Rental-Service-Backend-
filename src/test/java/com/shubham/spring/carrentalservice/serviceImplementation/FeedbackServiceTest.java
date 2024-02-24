package com.shubham.spring.carrentalservice.serviceImplementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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

import com.shubham.spring.carrentalservice.entity.Car;
import com.shubham.spring.carrentalservice.entity.Customer;
import com.shubham.spring.carrentalservice.entity.Feedback;
import com.shubham.spring.carrentalservice.entity.Rental;
import com.shubham.spring.carrentalservice.exception.CustomerException;
import com.shubham.spring.carrentalservice.exception.FeedBackNotFoundException;
import com.shubham.spring.carrentalservice.exception.InvalidRentalIdException;
import com.shubham.spring.carrentalservice.model.FeedbackInputModel;
import com.shubham.spring.carrentalservice.model.FeedbackOutputModel;
import com.shubham.spring.carrentalservice.repository.FeedbackRepository;

public class FeedbackServiceTest {

	 @Mock
	    private FeedbackRepository feedbackRepository;

	    @Mock
	    private CustomerServiceImplementation customerService;

	    @Mock
	    private RentalServiceImplementation rentalService;

	    @InjectMocks
	    private FeedbackServiceImplementation feedbackService;
	    
	    Car car = new Car();
	    Customer customer = new Customer();
	    String customerId = "abc@gmail.com";

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	        
	        car.setCapacity(4);
			car.setCarId("C001");
			car.setCarMake("Tata");
			car.setCarModel("Thar");
			car.setCarRentalRate(100.0);
			car.setCarType("SUV");
			
			customer.setAddress("Kolkata");
			customer.setCustomerName("Shubham");
			customer.setEmailId(customerId);
			customer.setDateOfBirth(LocalDate.of(2000, 06, 06));
			customer.setDrivingLicence("WB13 20229876123");
			customer.setPassword("password123");
			customer.setPhone("9876543210");
	    }

	    @Test
	    void testAddFeedback() throws CustomerException, InvalidRentalIdException {
	        // Mock data
	        FeedbackInputModel feedbackModel = new FeedbackInputModel();
	        feedbackModel.setEmailId("customer@example.com");
	        feedbackModel.setRentalId(1);
	        feedbackModel.setMessage("Great service!");

	        Rental rental = new Rental();
	        rental.setCar(car);
	        rental.setCustomer(customer);
	        Feedback feedback = new Feedback();
	        feedback.setCustomer(customer);
	        feedback.setRental(rental);
	        feedback.setFeedback(feedbackModel.getMessage());

	        // Mock dependencies
	        when(customerService.getCustomerByEmailId(feedbackModel.getEmailId())).thenReturn(customer);
	        when(rentalService.getById(feedbackModel.getRentalId())).thenReturn(rental);
	        when(feedbackRepository.save(any(Feedback.class))).thenReturn(feedback);

	        // Invoke the method
	        FeedbackOutputModel result = feedbackService.addFeedback(feedbackModel);

	        // Verify interactions and assertions
	        verify(customerService).getCustomerByEmailId(feedbackModel.getEmailId());
	        verify(rentalService).getById(feedbackModel.getRentalId());
	        verify(feedbackRepository).save(any(Feedback.class));
	        assertEquals(feedbackModel.getMessage(), result.getFeedback());
	    }

	    @Test
	    void testGetFeedbackByCustomerId() throws FeedBackNotFoundException {
	        // Mock data
	        String emailId = "customer@example.com";
	        
	        Rental rental = new Rental();
	        rental.setCar(car);
	        rental.setCustomer(customer);
	        
	        Feedback feedback = new Feedback();
	        
	        feedback.setFeedbackId(1);
	        feedback.setRental(rental);
	        
	        List<Feedback> feedbackList = new ArrayList<>();
	        feedbackList.add(feedback);

	        // Mock dependencies
	        when(feedbackRepository.getFeedbackByCustomerId(emailId)).thenReturn(feedbackList);

	        // Invoke the method
	        List<FeedbackOutputModel> result = feedbackService.getFeedbackByCustomerId(emailId);

	        // Verify interactions and assertions
	        verify(feedbackRepository).getFeedbackByCustomerId(emailId);
	        assertEquals(1, result.size());
	        assertEquals(feedback.getFeedbackId(), result.get(0).getFeedbackId());
	    }

	    @Test
	    void testGetFeedbackByCustomerId_NoFeedbackFound() {
	        // Mock data
	        String emailId = "customer@example.com";

	        // Mock dependencies
	        when(feedbackRepository.getFeedbackByCustomerId(emailId)).thenReturn(new ArrayList<>());

	        // Invoke the method and assert exception
	        assertThrows(FeedBackNotFoundException.class, () -> {
	            feedbackService.getFeedbackByCustomerId(emailId);
	        });

	        // Verify interactions
	        verify(feedbackRepository).getFeedbackByCustomerId(emailId);
	    }

	    @Test
	    void testEditFeedback() throws FeedBackNotFoundException {
	        // Mock data
	        FeedbackInputModel feedbackInputModel = new FeedbackInputModel();
	        feedbackInputModel.setEmailId("customer@example.com");
	        feedbackInputModel.setRentalId(1);
	        feedbackInputModel.setMessage("Updated feedback");
	        Rental rental = new Rental();
	        rental.setCar(car);
	        rental.setCustomer(customer);

	        Feedback feedback = new Feedback();
	        feedback.setFeedback("Old feedback");
	        feedback.setRental(rental);

	        // Mock dependencies
	        when(feedbackRepository.getFeedbackByRentalIdAndCustomerId(
	                feedbackInputModel.getRentalId(), feedbackInputModel.getEmailId())).thenReturn(feedback);
	        when(feedbackRepository.save(any(Feedback.class))).thenReturn(feedback);

	        // Invoke the method
	        FeedbackOutputModel result = feedbackService.editFeedback(feedbackInputModel);

	        // Verify interactions and assertions
	        verify(feedbackRepository).getFeedbackByRentalIdAndCustomerId(
	                feedbackInputModel.getRentalId(), feedbackInputModel.getEmailId());
	        verify(feedbackRepository).save(any(Feedback.class));
	        assertEquals(feedbackInputModel.getMessage(), result.getFeedback());
	    }

	    @Test
	    void testEditFeedback_NoFeedbackFound() {
	        // Mock data
	        FeedbackInputModel feedbackInputModel = new FeedbackInputModel();
	        feedbackInputModel.setEmailId("customer@example.com");
	        feedbackInputModel.setRentalId(1);
	        feedbackInputModel.setMessage("Updated feedback");

	        // Mock dependencies
	        when(feedbackRepository.getFeedbackByRentalIdAndCustomerId(
	                feedbackInputModel.getRentalId(), feedbackInputModel.getEmailId())).thenReturn(null);

	        // Invoke the method and assert exception
	        assertThrows(FeedBackNotFoundException.class, () -> {
	            feedbackService.editFeedback(feedbackInputModel);
	        });

	        // Verify interactions
	        verify(feedbackRepository).getFeedbackByRentalIdAndCustomerId(
	                feedbackInputModel.getRentalId(), feedbackInputModel.getEmailId());
	    }
}
