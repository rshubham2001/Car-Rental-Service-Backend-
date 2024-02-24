package com.shubham.spring.carrentalservice.serviceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shubham.spring.carrentalservice.entity.Customer;
import com.shubham.spring.carrentalservice.entity.Feedback;
import com.shubham.spring.carrentalservice.entity.Rental;
import com.shubham.spring.carrentalservice.exception.CustomerException;
import com.shubham.spring.carrentalservice.exception.FeedBackNotFoundException;
import com.shubham.spring.carrentalservice.exception.InvalidRentalIdException;
import com.shubham.spring.carrentalservice.model.FeedbackInputModel;
import com.shubham.spring.carrentalservice.model.FeedbackOutputModel;
import com.shubham.spring.carrentalservice.repository.FeedbackRepository;
import com.shubham.spring.carrentalservice.serviceInterface.FeedbackService;
import com.shubham.spring.carrentalservice.util.MapFeedback;

import jakarta.transaction.Transactional;

@Service
public class FeedbackServiceImplementation implements FeedbackService{

	@Autowired
	FeedbackRepository feedbackRepository;
	
	@Autowired
	CustomerServiceImplementation customerService;
	
	@Autowired
	RentalServiceImplementation rentalService;
	
	
	@Transactional
	public FeedbackOutputModel addFeedback(FeedbackInputModel feedbackModel) throws CustomerException, InvalidRentalIdException {
		Feedback feedback = new Feedback();
		
		Customer customer = customerService.getCustomerByEmailId(feedbackModel.getEmailId());
		
		Rental rental = rentalService.getById(feedbackModel.getRentalId());
		
		
		feedback.setCustomer(customer);
		feedback.setRental(rental);
		feedback.setFeedback(feedbackModel.getMessage());
		
		feedbackRepository.save(feedback);
		
		FeedbackOutputModel feedbackOutputModel = MapFeedback.mapToFeedbackOutput(feedback);
		
		return feedbackOutputModel;
	}
	
	@Transactional
	public List<FeedbackOutputModel> getFeedbackByCustomerId(String emailId) throws FeedBackNotFoundException{
		List<Feedback> feedbackList = feedbackRepository.getFeedbackByCustomerId(emailId);
	    if(!feedbackList.isEmpty()) {
	    	List<FeedbackOutputModel> feedbackOutputList = MapFeedback.mapToFeedbackOutputList(feedbackList);
	    	return feedbackOutputList;
	    }
	    throw new FeedBackNotFoundException("No Feedback By this user");
	}
	
	@Transactional
	public FeedbackOutputModel editFeedback(FeedbackInputModel feedbackInputModel) throws FeedBackNotFoundException {
		Integer rentalId = feedbackInputModel.getRentalId();
		String emailId = feedbackInputModel.getEmailId();
		Feedback feedback = feedbackRepository.getFeedbackByRentalIdAndCustomerId(rentalId, emailId);
		if(feedback!=null) {
			feedback.setFeedback(feedbackInputModel.getMessage());
			feedbackRepository.save(feedback);
			
			FeedbackOutputModel feedbackOutputModel = MapFeedback.mapToFeedbackOutput(feedback);
			return feedbackOutputModel;
		}
		throw new FeedBackNotFoundException("No Feedback Found");
		
	}
}
