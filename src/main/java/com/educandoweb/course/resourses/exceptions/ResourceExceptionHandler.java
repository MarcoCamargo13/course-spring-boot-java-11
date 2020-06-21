package com.educandoweb.course.resourses.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

@ControllerAdvice //esta anotation captura a exception para tratamento
public class ResourceExceptionHandler {

	
	//este moto ira interceptar qq exceção ResourceNotFoundException
	@ExceptionHandler(ResourceNotFoundException.class)//com esta anotation ela vai interceptar a exception
	public ResponseEntity<StandardError> resourseNotFound(ResourceNotFoundException e, HttpServletRequest request){
		String error  = "Resouce not Found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
		
	}
}
