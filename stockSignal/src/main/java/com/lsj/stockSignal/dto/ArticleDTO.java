package com.lsj.stockSignal.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ArticleDTO {

	private Integer id;
	
	@NotNull
	private Integer boardId;
	
	@NotBlank
	private String title;
	
	@NotBlank
	private String writer;
	
	@NotBlank
	private String password;
	
	@NotBlank
	private String content;
	
	private int viewCount;
	
	private Date createdAt;
	
	private ArticleImageDTO thumbnail;
	
}
