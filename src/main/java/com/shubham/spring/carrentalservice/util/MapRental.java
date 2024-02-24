package com.shubham.spring.carrentalservice.util;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import com.shubham.spring.carrentalservice.entity.Rental;
import com.shubham.spring.carrentalservice.model.RentalOutputModel;

public class MapRental {

	public static RentalOutputModel mapToRentalOutput(Rental rental) {
		RentalOutputModel rentalOutputModel = new RentalOutputModel();
		rentalOutputModel.setRentalId(rental.getRentalId());
		rentalOutputModel.setCustomerId(rental.getCustomer().getEmailId());
		rentalOutputModel.setCustomerName(rental.getCustomer().getCustomerName());
		rentalOutputModel.setCarId(rental.getCar().getCarId());
		rentalOutputModel.setCarMake(rental.getCar().getCarMake());
		rentalOutputModel.setCarModel(rental.getCar().getCarModel());
		rentalOutputModel.setCarType(rental.getCar().getCarType());
		rentalOutputModel.setBookingDate(rental.getBookingDate());
		Long duration = ChronoUnit.DAYS.between(rental.getReservationStartDate(),rental.getReservationEndDate());
		if(duration==0) {
			duration+=1;
		}
		rentalOutputModel.setResrvationDuration(duration);
		rentalOutputModel.setTotalCost(rental.getTotalCost());
		rentalOutputModel.setReservationStatus(rental.getReservationStatus());
		
		return rentalOutputModel;
	}
	
	public static List<RentalOutputModel> mapToRentalOutPutList(List<Rental> rentalList){
		List<RentalOutputModel> rentalOutputModelList = new ArrayList<>();
		for(Rental rental : rentalList) {
			RentalOutputModel rentalOutputModel = new RentalOutputModel();
			rentalOutputModel.setRentalId(rental.getRentalId());
			rentalOutputModel.setCustomerId(rental.getCustomer().getEmailId());
			rentalOutputModel.setCustomerName(rental.getCustomer().getCustomerName());
			rentalOutputModel.setCarId(rental.getCar().getCarId());
			rentalOutputModel.setCarMake(rental.getCar().getCarMake());
			rentalOutputModel.setCarModel(rental.getCar().getCarModel());
			rentalOutputModel.setCarType(rental.getCar().getCarType());
			rentalOutputModel.setBookingDate(rental.getBookingDate());
			Long duration = ChronoUnit.DAYS.between(rental.getReservationStartDate(),rental.getReservationEndDate());
			rentalOutputModel.setResrvationDuration(duration);
			rentalOutputModel.setTotalCost(rental.getTotalCost());
			rentalOutputModel.setReservationStatus(rental.getReservationStatus());
			
			rentalOutputModelList.add(rentalOutputModel);
		}
		return rentalOutputModelList;
	}
}
