package com.shubham.spring.carrentalservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shubham.spring.carrentalservice.entity.Rental;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer>{

	@Query(value="select r from Rental r where r.customer.emailId = ?1")
	public List<Rental> getRentalsByCustomerId(String emailId);

}
