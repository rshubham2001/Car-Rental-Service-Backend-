package com.shubham.spring.carrentalservice.validations;

import org.springframework.stereotype.Component;

import com.shubham.spring.carrentalservice.exception.InvalidRegistrationException;

@Component
public class CustomerValidation {

	public boolean checkName(String name) {
		if (name.matches("^(?!.*\\s{4})[A-Za-z\\s.]+$")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkEmail(String emailId) {
		if (emailId.matches("^[A-Za-z0-9+_.]+@(.+)$")) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkDrivingLicence(String drivingLicense) {
		if(drivingLicense.matches("^(([A-Z]{2}[0-9]{2})( )|([A-Z]{2}-[0-9]{2}))((19|20)[0-9][0-9])[0-9]{7}$")) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean checkPhone(String phone) {
		if(phone.matches("^[6-9]\\d{9}$")) {
			return true;
		}else {
			return false;
		}
	}

	public boolean validatePassword(String password) throws InvalidRegistrationException {
		if (password.length() < 8 || password.length() > 16) {
			return false;
		} else if (password.matches("[A-Za-z]+")) {
			return false;
		} else if (password.matches("[0-9]+")) {
			return false;
		} else {
			return true;
		}
	}
}
