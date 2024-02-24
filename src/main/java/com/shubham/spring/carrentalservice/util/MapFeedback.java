package com.shubham.spring.carrentalservice.util;

import java.util.ArrayList;
import java.util.List;

import com.shubham.spring.carrentalservice.entity.Feedback;
import com.shubham.spring.carrentalservice.model.FeedbackOutputModel;

public class MapFeedback {

	public static FeedbackOutputModel mapToFeedbackOutput(Feedback feedback) {
		FeedbackOutputModel feedbackOutputModel = new FeedbackOutputModel();
		feedbackOutputModel.setFeedbackId(feedback.getFeedbackId());
		feedbackOutputModel.setRentalId(feedback.getRental().getRentalId());
		feedbackOutputModel.setCustomerName(feedback.getRental().getCustomer().getCustomerName());
		feedbackOutputModel.setFeedback(feedback.getFeedback());
		
		return feedbackOutputModel;
	}
	
	public static List<FeedbackOutputModel> mapToFeedbackOutputList(List<Feedback> feedbackList){
		List<FeedbackOutputModel> feedbackOutputList = new ArrayList<>();
		for(Feedback f:feedbackList) {
    		FeedbackOutputModel feedbackOutputModel = new FeedbackOutputModel();
    		feedbackOutputModel.setFeedbackId(f.getFeedbackId());
    		feedbackOutputModel.setRentalId(f.getRental().getRentalId());
    		feedbackOutputModel.setCustomerName(f.getRental().getCustomer().getCustomerName());
    		feedbackOutputModel.setFeedback(f.getFeedback());
    		feedbackOutputList.add(feedbackOutputModel);
    	}
    	return feedbackOutputList;
		
	}
}
