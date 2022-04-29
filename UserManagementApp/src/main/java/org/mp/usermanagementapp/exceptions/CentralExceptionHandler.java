package org.mp.usermanagementapp.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CentralExceptionHandler {

	Logger LOGGER = LoggerFactory.getLogger("CentralExceptionHandler.class");
	
	@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
	@ExceptionHandler(InvalidEmailIdException.class)
	public String invalidEmailId(InvalidEmailIdException e) {
		LOGGER.error("Email Id is not in correct format.");
		return e.getMessage();
	}
	
	@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
	@ExceptionHandler(InvalidPhoneNoException.class)
	public String invalidPhoneNo(InvalidPhoneNoException e) {
		LOGGER.error("Phone Number is not in correct format.");
		return e.getMessage();
	}
	
	@ResponseStatus(value = HttpStatus.CONFLICT)
	@ExceptionHandler(MinimumAgeException.class)
	public String minimumAge(MinimumAgeException e) {
		LOGGER.error("Age is less than 18 years.");
		return e.getMessage();
	}
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(UserNotAvailableException.class)
	public String userNotAvailable(UserNotAvailableException e) {
		LOGGER.error("User Details not Available.");
		return e.getMessage();
	}
}
