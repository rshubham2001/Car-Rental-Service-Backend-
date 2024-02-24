package com.shubham.spring.carrentalservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shubham.spring.carrentalservice.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String>{

	@Query(value="select c from Customer c where c.emailId=?1 and c.password=?2")
	public Customer customerLogin(String emailId, String password);
	
	@Query(value="select case when count(c)>0 then true else false end from Customer c where c.emailId=?1")
	public boolean findByEmailId(String emailId);

	@Query(value="select case when count(c)>0 then true else false end from Customer c where c.drivingLicence=?1")
	public boolean findByDL(String drivingLicence);
	
	@Query(value="select case when count(c)>0 then true else false end from Customer c where c.phone=?1")
	public boolean findByPhone(String phone);

	
}
