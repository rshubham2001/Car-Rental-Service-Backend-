package com.shubham.spring.carrentalservice.serviceInterface;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shubham.spring.carrentalservice.exception.CustomerException;
import com.shubham.spring.carrentalservice.exception.FeedBackNotFoundException;
import com.shubham.spring.carrentalservice.exception.InvalidRentalIdException;
import com.shubham.spring.carrentalservice.model.FeedbackInputModel;
import com.shubham.spring.carrentalservice.model.FeedbackOutputModel;
@Service
public interface FeedbackService {
	public FeedbackOutputModel addFeedback(FeedbackInputModel feedbackModel)
			throws CustomerException, InvalidRentalIdException;

	public List<FeedbackOutputModel> getFeedbackByCustomerId(String emailId) throws FeedBackNotFoundException;

	public FeedbackOutputModel editFeedback(FeedbackInputModel feedbackInputModel) throws FeedBackNotFoundException;
}
