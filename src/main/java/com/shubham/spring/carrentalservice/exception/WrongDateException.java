package com.shubham.spring.carrentalservice.exception;

public class WrongDateException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WrongDateException() {
		// TODO Auto-generated constructor stub
	}

	public WrongDateException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public WrongDateException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public WrongDateException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public WrongDateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
