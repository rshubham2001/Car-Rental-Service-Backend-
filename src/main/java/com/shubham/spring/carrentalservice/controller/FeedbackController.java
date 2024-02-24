package com.shubham.spring.carrentalservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shubham.spring.carrentalservice.exception.CustomerException;
import com.shubham.spring.carrentalservice.exception.FeedBackNotFoundException;
import com.shubham.spring.carrentalservice.exception.InvalidRentalIdException;
import com.shubham.spring.carrentalservice.model.FeedbackInputModel;
import com.shubham.spring.carrentalservice.model.FeedbackOutputModel;
import com.shubham.spring.carrentalservice.serviceImplementation.FeedbackServiceImplementation;

@RestController
public class FeedbackController {

	@Autowired
	FeedbackServiceImplementation feedbackService;
	
	Logger logger = LoggerFactory.getLogger(FeedbackController.class);
	
	@PostMapping("/addfeedback")
	public ResponseEntity<FeedbackOutputModel> addFeedback(@RequestBody FeedbackInputModel feedbackModel) throws CustomerException, InvalidRentalIdException {
		logger.info("Adding Feedback to Database");
		FeedbackOutputModel feedbackOutputModel = feedbackService.addFeedback(feedbackModel);
		logger.info("Feedback Added");
		return new ResponseEntity<FeedbackOutputModel>(feedbackOutputModel,HttpStatus.OK);
	}
	
	@GetMapping("/getfeedbackbycustomerid/{emailId}")
	public ResponseEntity<List<FeedbackOutputModel>> getFeedbackByCustomerId(@PathVariable("emailId") String emailId) throws FeedBackNotFoundException{
		logger.info("Getting all feedback of a Customer");
		List<FeedbackOutputModel> feedbackOutputList = feedbackService.getFeedbackByCustomerId(emailId);
		logger.info("Feedback Fetched");
		return new ResponseEntity<List<FeedbackOutputModel>>(feedbackOutputList, HttpStatus.OK);
	}
	
	@PutMapping("/editfeedback")
	public ResponseEntity<FeedbackOutputModel> editFeedback(@RequestBody FeedbackInputModel feedbackInputModel) throws FeedBackNotFoundException{
		logger.info("Editing Feedback");
		FeedbackOutputModel feedbackOutputList = feedbackService.editFeedback(feedbackInputModel);
		logger.info("Feedback Updated");
		return new ResponseEntity<FeedbackOutputModel>(feedbackOutputList, HttpStatus.OK);
	}
}
