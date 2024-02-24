package com.shubham.spring.carrentalservice.exception;

public class InvalidRentalIdException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidRentalIdException() {
		// TODO Auto-generated constructor stub
	}

	public InvalidRentalIdException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public InvalidRentalIdException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidRentalIdException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidRentalIdException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
