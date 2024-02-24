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

import com.shubham.spring.carrentalservice.exception.CustomerException;
import com.shubham.spring.carrentalservice.exception.FeedBackNotFoundException;
import com.shubham.spring.carrentalservice.exception.InvalidRentalIdException;
import com.shubham.spring.carrentalservice.model.FeedbackInputModel;
import com.shubham.spring.carrentalservice.model.FeedbackOutputModel;
import com.shubham.spring.carrentalservice.serviceImplementation.FeedbackServiceImplementation;

class FeedbackControllerTest {

    @Mock
    private FeedbackServiceImplementation feedbackService;

    @Mock
    private Logger logger;

    @InjectMocks
    private FeedbackController feedbackController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddFeedback() throws CustomerException, InvalidRentalIdException {
        // Prepare input
        FeedbackInputModel feedbackInputModel = new FeedbackInputModel();
        // ... set properties of feedbackInputModel

        // Prepare expected output
        FeedbackOutputModel expectedOutput = new FeedbackOutputModel();
        // ... set properties of expectedOutput

        // Configure the mock behavior
        when(feedbackService.addFeedback(feedbackInputModel)).thenReturn(expectedOutput);

        // Invoke the method being tested
        ResponseEntity<FeedbackOutputModel> response = feedbackController.addFeedback(feedbackInputModel);

        // Verify interactions and assertions
        verify(logger).info("Adding Feedback to Database");
        verify(logger).info("Feedback Added");
        verify(feedbackService).addFeedback(feedbackInputModel);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedOutput, response.getBody());
    }

    @Test
    void testGetFeedbackByCustomerId() throws FeedBackNotFoundException {
        // Prepare input
        String emailId = "test@example.com";

        // Prepare expected output
        List<FeedbackOutputModel> expectedOutput = new ArrayList<>();
        // ... add feedbackOutputModels to expectedOutput

        // Configure the mock behavior
        when(feedbackService.getFeedbackByCustomerId(emailId)).thenReturn(expectedOutput);

        // Invoke the method being tested
        ResponseEntity<List<FeedbackOutputModel>> response = feedbackController.getFeedbackByCustomerId(emailId);

        // Verify interactions and assertions
        verify(logger).info("Getting all feedback of a Customer");
        verify(logger).info("Feedback Fetched");
        verify(feedbackService).getFeedbackByCustomerId(emailId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedOutput, response.getBody());
    }

    @Test
    void testEditFeedback() throws FeedBackNotFoundException {
        // Prepare input
        FeedbackInputModel feedbackInputModel = new FeedbackInputModel();
        // ... set properties of feedbackInputModel

        // Prepare expected output
        FeedbackOutputModel expectedOutput = new FeedbackOutputModel();
        // ... set properties of expectedOutput

        // Configure the mock behavior
        when(feedbackService.editFeedback(feedbackInputModel)).thenReturn(expectedOutput);

        // Invoke the method being tested
        ResponseEntity<FeedbackOutputModel> response = feedbackController.editFeedback(feedbackInputModel);

        // Verify interactions and assertions
        verify(logger).info("Editing Feedback");
        verify(logger).info("Feedback Updated");
        verify(feedbackService).editFeedback(feedbackInputModel);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedOutput, response.getBody());
    }
}

