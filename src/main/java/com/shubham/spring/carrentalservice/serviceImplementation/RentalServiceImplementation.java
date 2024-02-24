package com.shubham.spring.carrentalservice.serviceImplementation;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shubham.spring.carrentalservice.entity.Car;
import com.shubham.spring.carrentalservice.entity.Customer;
import com.shubham.spring.carrentalservice.entity.Rental;
import com.shubham.spring.carrentalservice.exception.CarNotFoundException;
import com.shubham.spring.carrentalservice.exception.CustomerException;
import com.shubham.spring.carrentalservice.exception.InvalidRentalIdException;
import com.shubham.spring.carrentalservice.exception.WrongDateException;
import com.shubham.spring.carrentalservice.model.RentalInputModel;
import com.shubham.spring.carrentalservice.model.RentalOutputModel;
import com.shubham.spring.carrentalservice.repository.RentalRepository;
import com.shubham.spring.carrentalservice.serviceInterface.RentalService;
import com.shubham.spring.carrentalservice.util.MapRental;

@Service
public class RentalServiceImplementation implements RentalService{

	@Autowired
	CustomerServiceImplementation customerService;

	@Autowired
	CarServiceImplementation carService;

	@Autowired
	RentalRepository rentalRepository;


	// For internal use only(This method is used in FeedbackService)
	public Rental getById(Integer rentalId) throws InvalidRentalIdException {
		Rental rental = rentalRepository.findById(rentalId).orElse(null);
		if (rental != null) {
			return rental;
		}
		throw new InvalidRentalIdException("Rental Id is Invalid.");
	}

	public RentalOutputModel getRentalById(Integer rentalId) throws InvalidRentalIdException {
		Rental rental = rentalRepository.findById(rentalId).orElse(null);
		if (rental != null) {
			RentalOutputModel rentalOutputModel = MapRental.mapToRentalOutput(rental);
			return rentalOutputModel;
		}
		throw new InvalidRentalIdException("Rental Id is Invalid.");
	}

	public List<RentalOutputModel> getRentalsByCustomerId(String emailId) throws InvalidRentalIdException {
		List<Rental> rentalList = rentalRepository.getRentalsByCustomerId(emailId);
		if (!rentalList.isEmpty()) {
			List<RentalOutputModel> rentalOutputModelList = MapRental.mapToRentalOutPutList(rentalList);
			return rentalOutputModelList;
		}
		throw new InvalidRentalIdException("No rental history with this emailId");
	}

	public RentalOutputModel rentCar(RentalInputModel rentalModel)
			throws CustomerException, CarNotFoundException, WrongDateException {
		Rental rental = new Rental();

		Customer cutomer = customerService.getCustomerByEmailId(rentalModel.getEmailId());
		Car car = carService.getCarById(rentalModel.getCarId());

		LocalDate bookingDate = LocalDate.now();
		LocalDate reservationStartDate = rentalModel.getReservationStartDate();
		LocalDate reservationEndDate = rentalModel.getReservationEndDate();

		if (bookingDate.isAfter(reservationStartDate)) {
			throw new WrongDateException("Reservation Date Cannot Be Before Current Date");
		} else if (reservationEndDate.isBefore(reservationStartDate)) {
			throw new WrongDateException("Reservation End Date Cannot Be Before Reservation Start Date");
		} else if (car.getCarRentalRate() == null) {
			throw new CarNotFoundException("Car Rental Rate is not specified for the given car");
		}else if(!carService.getAllAvailableCar(reservationStartDate, reservationEndDate).contains(car)) {
			throw new CarNotFoundException("Car is Already booked");
		}
		else {
			rental.setCustomer(cutomer);
			rental.setCar(car);
			rental.setBookingDate(bookingDate);
			rental.setReservationStartDate(reservationStartDate);
			rental.setReservationEndDate(reservationEndDate);

			long days = ChronoUnit.DAYS.between(reservationStartDate, reservationEndDate)+1;
			double cost = (double) days * car.getCarRentalRate();

			rental.setTotalCost(cost);

			rental.setReservationStatus("Confirm");
		}
		rentalRepository.save(rental);

		RentalOutputModel rentalOutputModel = MapRental.mapToRentalOutput(rental);

		return rentalOutputModel;
	}
}
