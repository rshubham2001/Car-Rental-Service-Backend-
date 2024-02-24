package com.shubham.spring.carrentalservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shubham.spring.carrentalservice.entity.CancelledRental;

@Repository
public interface CancelRepository extends JpaRepository<CancelledRental, Integer> {

	@Query(value="select c from CancelledRental c where c.customer.emailId=?1")
	public List<CancelledRental> getCancelledRentalsByEmailId(String emailId);

}
