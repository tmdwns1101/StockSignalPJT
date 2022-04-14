package com.lsj.stockSignal.service;


import com.lsj.stockSignal.dto.ArticleDTO;


public interface ArticleImageService {

	void createArticleImages(ArticleDTO article);
	
	void deleteArticleImages(ArticleDTO article);
	
}
