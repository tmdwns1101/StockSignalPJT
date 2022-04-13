package com.lsj.stockSignal.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class BoardDTO {
	
	private Integer id;

	@NotBlank
	private String boardName;
}
