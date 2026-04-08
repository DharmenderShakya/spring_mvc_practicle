package com.srping_mvc_object_mapper_practicle.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	
	 @Override
	    protected ResponseEntity<Object> handleMethodArgumentNotValid(
	            MethodArgumentNotValidException ex,
	            HttpHeaders headers,
	            HttpStatusCode status,
	            WebRequest request) {

		 Map<String, Object> body=new LinkedHashMap<String,Object>();
			
			body.put("timeStamp", LocalDateTime.now());
			 
	        FieldError error=ex.getBindingResult().getFieldErrors().get(0);
	        

	        body.put("message", error);
	        
	        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	    }
	 
	    @ExceptionHandler(ResourceNotFoundException.class)
	    public ResponseEntity<?> handleNotFound(ResourceNotFoundException ex,HttpServletRequest request,WebRequest weRequest) {
			
	    	Map<String, Object> body=new LinkedHashMap<String,Object>();
	    	
			body.put("timeStamp", LocalDateTime.now());
			 
	        body.put("message", ex.getMessage());
	    	
			return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	    }

	    
	    @ExceptionHandler(AuthorizationDeniedException.class)
	    public ResponseEntity<String> handleAccessDenied(AuthorizationDeniedException ex,HttpServletRequest request,WebRequest weRequest) {
	    	
	    	Map<String, Object> body=new LinkedHashMap<String,Object>();
	    	
			body.put("timeStamp", LocalDateTime.now());
			 
	        body.put("message", ex.getMessage());
	    	
	        return ResponseEntity.status(403).body("Forbidden");
	    }
	    
	    @ExceptionHandler(BadRequestException.class)
	    public ResponseEntity<?> handleBadRequestException(BadRequestException ex,HttpServletRequest request,WebRequest weRequest) {
	    	
	    	Map<String, Object> body=new LinkedHashMap<String,Object>();
	    	
			body.put("timeStamp", LocalDateTime.now());
			 
	        body.put("message", ex.getMessage());
	    	
	        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	    }
}
