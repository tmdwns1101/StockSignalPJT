package com.lsj.stockSignal.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class ReviewDTO {

	private Integer id;
	
	@NotBlank
	private String writer;
	
	@NotBlank
	private String password;
	
	@NotBlank
	private String content;
	
	@NotNull
	private Integer articleId;
	
	private Date createdAt;
}
