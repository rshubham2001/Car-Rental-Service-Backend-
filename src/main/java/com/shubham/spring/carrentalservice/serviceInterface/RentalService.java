package com.shubham.spring.carrentalservice.serviceInterface;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shubham.spring.carrentalservice.entity.Rental;
import com.shubham.spring.carrentalservice.exception.CarNotFoundException;
import com.shubham.spring.carrentalservice.exception.CustomerException;
import com.shubham.spring.carrentalservice.exception.InvalidRentalIdException;
import com.shubham.spring.carrentalservice.exception.WrongDateException;
import com.shubham.spring.carrentalservice.model.RentalInputModel;
import com.shubham.spring.carrentalservice.model.RentalOutputModel;

@Service
public interface RentalService {
	public Rental getById(Integer id) throws InvalidRentalIdException;

	public RentalOutputModel getRentalById(Integer id) throws InvalidRentalIdException;

	public List<RentalOutputModel> getRentalsByCustomerId(String emailId) throws InvalidRentalIdException;

	public RentalOutputModel rentCar(RentalInputModel rentalModel)
			throws CustomerException, CarNotFoundException, WrongDateException;
}
