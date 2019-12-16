package com.class6.exercise.exception;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<CustomErrorResponse> handleAllExceptions(Exception ex, HttpServletRequest request){
		CustomErrorResponse errors = new CustomErrorResponse();
		errors.setTimestamp(LocalDateTime.now());
		errors.setError("Internal server error");
		errors.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errors.setMessage(ex.getMessage());
		errors.setPath(request.getRequestURI());
		return new ResponseEntity<CustomErrorResponse>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ModelNotFoundException.class)
	public ResponseEntity<CustomErrorResponse> handleModelNotFoundException(ModelNotFoundException ex, HttpServletRequest request) {
	
		CustomErrorResponse errors = new CustomErrorResponse();
		
		errors.setTimestamp(LocalDateTime.now());
		errors.setError("Not found");
		errors.setStatus(HttpStatus.NOT_FOUND.value());
		errors.setMessage(ex.getMessage());
		errors.setPath(request.getRequestURI());
		
		return new ResponseEntity<CustomErrorResponse>(errors, HttpStatus.NOT_FOUND);
	}
	
	//For Spring @valid validation errors, it will throw handleMethodArgumentNotValid
	// @Validate For Validating Path Variables and Request Parameters
	
	@ExceptionHandler(ConstraintViolationException.class)
	public void constraintViolationException(HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.BAD_REQUEST.value());		
	}
	
	//Error handle for @Valid
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", new Date());
		body.put("status", status.value());
		
		//Get all fields errors
		List<String> errors = ex.getBindingResult().
				getFieldErrors().
				stream().map(x -> x.getDefaultMessage()).
				collect(Collectors.toList());
		body.put("errors", errors);
		
		return new ResponseEntity<>(body, headers, status);
	}
}
