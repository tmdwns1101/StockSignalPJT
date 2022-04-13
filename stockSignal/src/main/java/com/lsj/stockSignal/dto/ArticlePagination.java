package com.lsj.stockSignal.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArticlePagination {
	
	private List<ArticleDTO> data;
	
	private int page;
	
	private int limit;
	
	private boolean isFirst;
	
	private boolean isLast;
	
	private int startPageNumber;
	
	private int endPageNumber;
	
	private int prevPage;
	
	private int nextPage;
	
	private int totalPage;


}
