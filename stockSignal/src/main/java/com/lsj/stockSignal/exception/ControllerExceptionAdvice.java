package com.lsj.stockSignal.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.log4j.Log4j;

@ControllerAdvice
@Log4j
public class ControllerExceptionAdvice {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public ResponseEntity<ErrorResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
		BindingResult br = e.getBindingResult();

		List<ErrorResponse.FieldError> errors = new ArrayList<>();
		for (FieldError fieldError : br.getFieldErrors()) {
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

	@ExceptionHandler(ArticleNotFound.class)
	public String NotFoundEntityExceptionHandler(ArticleNotFound e) {

		log.warn(e.getMessage());
		return "error404";
	}
	
	@ExceptionHandler(ReviewNotFound.class)
	@ResponseBody
	public ResponseEntity<ErrorResponse> ReviewNotFoundExceptionHandler(ReviewNotFound e, HttpServletRequest request) {

		String requestPath = request.getRequestURL().toString();

		ErrorResponse errorResponse = new ErrorResponse();

		errorResponse.setErrorMessage(e.getMessage());
		errorResponse.setServicePath(requestPath);
		errorResponse.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));


		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
	}
	

	@ExceptionHandler(PasswordNotCorrect.class)
	@ResponseBody
	public ResponseEntity<ErrorResponse> passwordNotCorrectxceptionHandler(PasswordNotCorrect e,
			HttpServletRequest request) {

		String requestPath = request.getRequestURL().toString();

		ErrorResponse errorResponse = new ErrorResponse();

		errorResponse.setErrorMessage(e.getMessage());
		errorResponse.setServicePath(requestPath);
		errorResponse.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.FORBIDDEN);
	}

}
