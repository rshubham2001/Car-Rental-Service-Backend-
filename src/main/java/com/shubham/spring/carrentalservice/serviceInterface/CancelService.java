package com.shubham.spring.carrentalservice.serviceInterface;

import org.springframework.stereotype.Service;

import com.shubham.spring.carrentalservice.exception.InvalidCancellationException;
import com.shubham.spring.carrentalservice.exception.InvalidRentalIdException;
import com.shubham.spring.carrentalservice.model.CancelRentalOutputModel;
@Service
public interface CancelService {
	public CancelRentalOutputModel cancelRentals(Integer rentalId) throws InvalidCancellationException, InvalidRentalIdException;
}
