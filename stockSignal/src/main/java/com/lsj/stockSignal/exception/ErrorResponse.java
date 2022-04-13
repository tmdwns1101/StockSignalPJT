package com.lsj.stockSignal.exception;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ErrorResponse {
	
	private String errorMessage;
	
	private String timestamp;
	
	private String servicePath;

	private List<FieldError> errors;
	
	
	@Data
	@AllArgsConstructor
	public static class FieldError {
		
		private String field;
		
		private String message;
		
		private Object rejectValue;
		
		
	}
}
