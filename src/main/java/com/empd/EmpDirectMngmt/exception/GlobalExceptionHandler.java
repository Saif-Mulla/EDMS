package com.empd.EmpDirectMngmt.exception;

import java.net.BindException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler({IllegalArgumentException.class})
	public ResponseEntity<Map<String, Object>> handleIllegalArgument(IllegalArgumentException ex) {
	    return problem(HttpStatus.BAD_REQUEST, ex.getMessage());
	  
	}

	@ExceptionHandler({DataIntegrityViolationException.class})
	public ResponseEntity<Map<String, Object>> handleConstraint(DataIntegrityViolationException ex) {
	    String msg = ex.getMostSpecificCause() != null ? ex.getMostSpecificCause().getMessage() : ex.getMessage();
	    return problem(HttpStatus.CONFLICT, msg);
	  
	}

	@ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
	public ResponseEntity<Map<String, Object>> handleValidation(Exception ex) {
	    return problem(HttpStatus.BAD_REQUEST, "Validation error: " + ex.getMessage());
	  
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, Object>> handleOther(Exception ex) {
	    return problem(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
	  
	}

	  
	private ResponseEntity<Map<String, Object>> problem(HttpStatus status, String detail) {
	    Map<String, Object> body = new HashMap<>();
	    body.put("status", status.value());
	    body.put("error", status.getReasonPhrase());
	    body.put("detail", detail);
	    return ResponseEntity.status(status).body(body);
	 
	}
}
