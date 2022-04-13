package com.lsj.stockSignal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleImageDTO {

	private Integer id;
	
	private String imageName;
	
	private boolean isThumbnail;
	
	private int articleId;
}
