package com.logistic.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.logistic.services.exception.DataIntegrityException;
import com.logistic.services.exception.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis()));
	}
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e, HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis()));
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> vaLIDATION(MethodArgumentNotValidException e, HttpServletRequest request) {
		ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de validação", System.currentTimeMillis());
		for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
			err.addError(fieldError.getField(), fieldError.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

}


