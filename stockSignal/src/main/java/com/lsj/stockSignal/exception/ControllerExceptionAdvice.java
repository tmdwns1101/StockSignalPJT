package com.lsj.stockSignal.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ControllerExceptionAdvice {
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public ResponseEntity<ErrorResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
		BindingResult br = e.getBindingResult();
		
		List<ErrorResponse.FieldError> errors = new ArrayList<>();
		for(FieldError fieldError: br.getFieldErrors()) {
			String field = fieldError.getField();
			String message = fieldError.getDefaultMessage();
			Object rejectValue = fieldError.getRejectedValue();
			
			ErrorResponse.FieldError item = new ErrorResponse.FieldError(field, message, rejectValue);
			errors.add(item);
		
		}
		
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrors(errors);
		
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NotFoundEntity.class)
	public String NotFoundEntityExceptionHandler(NotFoundEntity e) {
		
		return "error404";
	}

}
