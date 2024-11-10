package com.cf.CFProject.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cf.CFProject.response.ResponseStructure;

@RestControllerAdvice
public class AppExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<?> catchUserNotFoundException(UserNotFoundException exception) {
		ResponseStructure<String> rs = new ResponseStructure<>();
		rs.setStatus(HttpStatus.NOT_FOUND.value());
		rs.setMessage("User is Not found/registered");
		rs.setData(null);
		return new ResponseEntity<>(rs, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(AdminNotFoundException.class)
	public ResponseEntity<?> catchAdminNotFoundException(AdminNotFoundException exception) {
		ResponseStructure<String> rs = new ResponseStructure<>();
		rs.setStatus(HttpStatus.NOT_FOUND.value());
		rs.setMessage("Admin is Not found/registered");
		rs.setData(null);
		return new ResponseEntity<>(rs, HttpStatus.BAD_REQUEST);
	}

}
