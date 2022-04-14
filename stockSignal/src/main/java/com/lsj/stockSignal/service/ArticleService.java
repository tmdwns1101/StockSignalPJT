package com.lsj.stockSignal.service;


import com.lsj.stockSignal.dto.ArticleDTO;
import com.lsj.stockSignal.dto.ArticlePagination;

public interface ArticleService {

	ArticlePagination getArticles(int boardId, int page);
	
	ArticleDTO getArticle(int articleId);
	
	void createArticle(ArticleDTO articleDTO);
	
	void updateArticle(ArticleDTO articleDTO);
}
