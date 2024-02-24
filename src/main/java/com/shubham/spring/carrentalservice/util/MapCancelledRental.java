package com.shubham.spring.carrentalservice.util;

import java.util.ArrayList;
import java.util.List;

import com.shubham.spring.carrentalservice.entity.CancelledRental;
import com.shubham.spring.carrentalservice.model.CancelRentalOutputModel;

public class MapCancelledRental {
	

	public static CancelRentalOutputModel mapCancelRental(CancelledRental cancelledRental) {
		CancelRentalOutputModel cancelledRentalOutputModel = new CancelRentalOutputModel();
		cancelledRentalOutputModel.setCancelledId(cancelledRental.getCancelledId());
		cancelledRentalOutputModel.setRentalId(cancelledRental.getRental().getRentalId());
		cancelledRentalOutputModel.setBookingDate(cancelledRental.getRental().getBookingDate());
		cancelledRentalOutputModel.setCancelletionDate(cancelledRental.getCancelledDate());
		cancelledRentalOutputModel.setCarId(cancelledRental.getRental().getCar().getCarId());
		cancelledRentalOutputModel.setCustomerName(cancelledRental.getCustomer().getCustomerName());
		cancelledRentalOutputModel.setRefundAmount(cancelledRental.getRefundAmount());
		cancelledRentalOutputModel.setReservationStatus(cancelledRental.getRental().getReservationStatus());
		cancelledRentalOutputModel.setTotalCost(cancelledRental.getRental().getTotalCost());
		
		return cancelledRentalOutputModel;
	}
	
	public static List<CancelRentalOutputModel> mapToCancelledRentalList(List<CancelledRental> cancelledRentalList){
		List<CancelRentalOutputModel> list = new ArrayList<>();
		for(CancelledRental c : cancelledRentalList) {
			CancelRentalOutputModel cancelledRentalOutputModel = new CancelRentalOutputModel();
			cancelledRentalOutputModel.setCancelledId(c.getCancelledId());
			cancelledRentalOutputModel.setRentalId(c.getRental().getRentalId());
			cancelledRentalOutputModel.setBookingDate(c.getRental().getBookingDate());
			cancelledRentalOutputModel.setCancelletionDate(c.getCancelledDate());
			cancelledRentalOutputModel.setCarId(c.getRental().getCar().getCarId());
			cancelledRentalOutputModel.setCustomerName(c.getCustomer().getCustomerName());
			cancelledRentalOutputModel.setRefundAmount(c.getRefundAmount());
			cancelledRentalOutputModel.setReservationStatus(c.getRental().getReservationStatus());
			cancelledRentalOutputModel.setTotalCost(c.getRental().getTotalCost());
			
			list.add(cancelledRentalOutputModel);
		}
		
		return list;
	}
}
