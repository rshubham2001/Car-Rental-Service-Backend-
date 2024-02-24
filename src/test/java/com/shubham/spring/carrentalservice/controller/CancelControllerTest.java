package com.shubham.spring.carrentalservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.shubham.spring.carrentalservice.exception.InvalidCancellationException;
import com.shubham.spring.carrentalservice.exception.InvalidRentalIdException;
import com.shubham.spring.carrentalservice.model.CancelRentalOutputModel;
import com.shubham.spring.carrentalservice.serviceImplementation.CancelServiceImplementation;

class CancelControllerTest {

    @Mock
    private CancelServiceImplementation cancelService;

    @Mock
    private Logger logger;

    @InjectMocks
    private CancelController cancelController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCancelRentals() throws InvalidCancellationException, InvalidRentalIdException {
        // Prepare input
        Integer rentalId = 123;

        // Prepare expected output
        CancelRentalOutputModel expectedOutput = new CancelRentalOutputModel();
        // ... set properties of expectedOutput

        // Configure the mock behavior
        when(cancelService.cancelRentals(rentalId)).thenReturn(expectedOutput);

        // Invoke the method being tested
        ResponseEntity<CancelRentalOutputModel> response = cancelController.cancelRentals(rentalId);

        // Verify interactions and assertions
        verify(logger).info("Cancelling Rented Car");
        verify(logger).info("Cancelled");
        verify(cancelService).cancelRentals(rentalId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedOutput, response.getBody());
    }

    @Test
    void testGetCancelledRentalsByEmailId() throws InvalidCancellationException {
        // Prepare input
        String emailId = "test@example.com";

        // Prepare expected output
        List<CancelRentalOutputModel> expectedOutput = new ArrayList<>();
        // ... add cancelRentalOutputModels to expectedOutput

        // Configure the mock behavior
        when(cancelService.getCancelledRentalsByEmailId(emailId)).thenReturn(expectedOutput);

        // Invoke the method being tested
        ResponseEntity<List<CancelRentalOutputModel>> response = cancelController.getCancelledRentalsByEmailId(emailId);

        // Verify interactions and assertions
        verify(logger).info("Getting all Cancelled Rental for customer with emailId {}", emailId);
        verify(logger).info("Fetched");
        verify(cancelService).getCancelledRentalsByEmailId(emailId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedOutput, response.getBody());
    }
}

