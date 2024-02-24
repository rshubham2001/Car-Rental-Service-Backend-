package com.shubham.spring.carrentalservice.serviceImplementation;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shubham.spring.carrentalservice.entity.CancelledRental;
import com.shubham.spring.carrentalservice.entity.Rental;
import com.shubham.spring.carrentalservice.exception.InvalidCancellationException;
import com.shubham.spring.carrentalservice.exception.InvalidRentalIdException;
import com.shubham.spring.carrentalservice.model.CancelRentalOutputModel;
import com.shubham.spring.carrentalservice.repository.CancelRepository;
import com.shubham.spring.carrentalservice.repository.RentalRepository;
import com.shubham.spring.carrentalservice.serviceInterface.CancelService;
import com.shubham.spring.carrentalservice.util.MapCancelledRental;

@Service
public class CancelServiceImplementation implements CancelService{

	@Autowired
	CancelRepository cancelRepository;
	
	@Autowired
	RentalServiceImplementation rentalServiceImplementation;
	
	@Autowired
	RentalRepository rentalRepository;
	
	
	
	public CancelRentalOutputModel cancelRentals(Integer rentalId)
			throws InvalidCancellationException, InvalidRentalIdException {
		Rental rental = rentalServiceImplementation.getById(rentalId);
		double totalPrice = rental.getTotalCost();
		LocalDate reservationStartDate = rental.getReservationStartDate();
		LocalDate currentDate = LocalDate.now();

		if (rental.getReservationStatus().equalsIgnoreCase("Cancelled")) {
			throw new InvalidCancellationException("Reservation is Already Cancelled");
		}

		// Cancellation can be done by only two days before or earlier.
		double refundAmount = 0.0;
		LocalDate twoDaysBefore = reservationStartDate.minusDays(2);
		LocalDate sevenDaysBefore = reservationStartDate.minusDays(7);
		
		if (currentDate.isAfter(twoDaysBefore)) {
			refundAmount = 0.0;
		} else if (currentDate.isAfter(sevenDaysBefore) || currentDate.isEqual(twoDaysBefore)) {
			refundAmount = totalPrice * 0.5;
		} else if (currentDate.isBefore(sevenDaysBefore) || currentDate.isEqual(sevenDaysBefore)) {
			refundAmount = totalPrice;
		}
		
		CancelledRental cancelledRental = new CancelledRental();
		cancelledRental.setCancelledDate(currentDate);
		cancelledRental.setCustomer(rental.getCustomer());
		cancelledRental.setRefundAmount(refundAmount);
		cancelledRental.setRental(rental);
		
		cancelRepository.save(cancelledRental);

		rental.setReservationStatus("Cancelled");
		rentalRepository.save(rental);
		
		CancelRentalOutputModel cancelledRentalOutputModel = MapCancelledRental.mapCancelRental(cancelledRental);

		return cancelledRentalOutputModel;
	}
	
	public List<CancelRentalOutputModel> getCancelledRentalsByEmailId(String emailId) throws InvalidCancellationException {
		List<CancelledRental> cancelledRentalList = cancelRepository.getCancelledRentalsByEmailId(emailId);
		if(cancelledRentalList.isEmpty()) {
			throw new InvalidCancellationException("No Cancelled Rentals with this emailId");
		}
		List<CancelRentalOutputModel> cancelRentalOutputModelsList = MapCancelledRental.mapToCancelledRentalList(cancelledRentalList);
		
		return cancelRentalOutputModelsList;
	}
}
