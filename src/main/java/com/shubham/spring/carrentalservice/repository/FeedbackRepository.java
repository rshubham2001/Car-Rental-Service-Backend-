package com.shubham.spring.carrentalservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shubham.spring.carrentalservice.entity.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer>{

	@Query(value="select f from Feedback f where f.customer.emailId=?1")
	public List<Feedback> getFeedbackByCustomerId(String emailId);
	
	@Query(value="select f from Feedback f where f.customer.emailId = ?2 and f.rental.rentalId=?1")
	public Feedback getFeedbackByRentalIdAndCustomerId(Integer rentalId, String emailId);
}
