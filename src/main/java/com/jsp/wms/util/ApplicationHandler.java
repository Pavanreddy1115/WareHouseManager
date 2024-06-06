package com.jsp.wms.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.wms.exception.IllegalOperationException;
@RestControllerAdvice
public class ApplicationHandler {
	
	private ResponseEntity<ErrorStructure>errorResponse(HttpStatus status,String message , String rootCause){
		return ResponseEntity.status(status).body(new ErrorStructure()
				.setStatuscode(status.value())
				.setErrorMessage(message)
				.setRootCause(rootCause));
	}
	
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure> adminHandler(IllegalOperationException illegalOperationException){
		return errorResponse(HttpStatus.FORBIDDEN, illegalOperationException.getMessage(), "Already exists");
	}
}
