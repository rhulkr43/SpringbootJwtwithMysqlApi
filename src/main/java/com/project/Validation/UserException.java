package com.project.Validation;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.project.entity.Validation;

@ControllerAdvice
public class UserException extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	  public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		 LocalDate date = LocalDate.now();  
	    Validation errorDetails = new Validation(date, ex.getMessage(),
	        request.getDescription(false));
	    return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
	  }
	
	 @ExceptionHandler(UserNotFoundException.class)
	  public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
		 LocalDate date = LocalDate.now();  
		    Validation errorDetails = new Validation(date, ex.getMessage(),
		        request.getDescription(false));
	    return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
	  }

}
