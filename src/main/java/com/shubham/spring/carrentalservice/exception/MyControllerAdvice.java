package com.shubham.spring.carrentalservice.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.shubham.spring.carrentalservice.model.ErrorResponse;

@RestControllerAdvice
public class MyControllerAdvice {

	
	Logger logger = LoggerFactory.getLogger(MyControllerAdvice.class);
	
	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<ErrorResponse> handleCustomerNotFoundException(CustomerException e, WebRequest wr){
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorCode(404);
		errorResponse.setErrorMessage(e.getMessage());
		logger.error(e.getMessage());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CarNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleCarNotFoundException(CarNotFoundException e, WebRequest wr){
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorCode(404);
		errorResponse.setErrorMessage(e.getMessage());
		logger.error(e.getMessage());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(FeedBackNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleFeedBackNotFoundException(FeedBackNotFoundException e, WebRequest wr){
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorCode(404);
		errorResponse.setErrorMessage(e.getMessage());
		logger.error(e.getMessage());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidCancellationException.class)
	public ResponseEntity<ErrorResponse> handleInvalidCancellationException(InvalidCancellationException e, WebRequest wr){
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorCode(404);
		errorResponse.setErrorMessage(e.getMessage());
		logger.error(e.getMessage());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(WrongDateException.class)
	public ResponseEntity<ErrorResponse> handleWrongDateException(WrongDateException e, WebRequest wr){
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorCode(404);
		errorResponse.setErrorMessage(e.getMessage());
		logger.error(e.getMessage());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidRegistrationException.class)
	public ResponseEntity<ErrorResponse> handeleInvalidRegistration(InvalidRegistrationException e, WebRequest wr){
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorCode(400);
		errorResponse.setErrorMessage(e.getMessage());
		logger.error(e.getMessage());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(InvalidRentalIdException.class)
	public ResponseEntity<ErrorResponse> handeleInvalidRegistration(InvalidRentalIdException e, WebRequest wr){
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorCode(400);
		errorResponse.setErrorMessage(e.getMessage());
		logger.error(e.getMessage());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
	}
	
	 @ExceptionHandler(MethodArgumentTypeMismatchException.class)
	    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException e, WebRequest wr) {
		 ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setErrorCode(400);
			errorResponse.setErrorMessage("Invalid parameter type. Please provide a valid value.");
	        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
	    }
	 
	 @ExceptionHandler(HttpMessageNotReadableException.class)
	    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException e, WebRequest wr) {
		 ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setErrorCode(400);
			errorResponse.setErrorMessage("Invalid JSON payload. Please provide a valid value.");
	        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
	    }
}
