package com.lsj.stockSignal.exception;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ErrorResponse {

	private List<FieldError> errors;
	
	
	@Data
	@AllArgsConstructor
	public static class FieldError {
		
		private String field;
		
		private String message;
		
		private Object rejectValue;
		
		
	}
}
