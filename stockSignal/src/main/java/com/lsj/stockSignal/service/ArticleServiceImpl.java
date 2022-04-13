package com.lsj.stockSignal.service;

import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lsj.stockSignal.dao.ArticleDAO;
import com.lsj.stockSignal.dto.ArticleDTO;
import com.lsj.stockSignal.dto.ArticlePagination;
import com.lsj.stockSignal.exception.NotFoundEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;


@Service
@RequiredArgsConstructor
@Log4j
public class ArticleServiceImpl implements ArticleService {

	private final ArticleDAO articleDAO;
	
	private final ArticleImageService articleImageService;
	
	private int PAGE_SIZE = 10;

	private int BLOCK_SIZE = 10;
	
	
	@Override
	public ArticlePagination getArticles(int boardId, int page) {
		

		int offset = (page - 1) * PAGE_SIZE;

		List<ArticleDTO> articles = this.articleDAO.findArticles(boardId, offset, PAGE_SIZE);
		
		

		int totalCount = this.articleDAO.countTotalArticleByBoardId(boardId);

		// 전체 페이지 수 구하기
		int totalPage = (int) Math.ceil((double) totalCount / PAGE_SIZE);

		// 페이지 블록 당 시작 페이지 번호
		int startPageNumber = (page - 1) / BLOCK_SIZE * BLOCK_SIZE + 1;
		
		if(startPageNumber < 1) {
			startPageNumber = 1;
		}

		// 페이지 블록 당 끝 페이지 번호
		int endPageNumber = startPageNumber + BLOCK_SIZE - 1;
		
		

		// 끝 번호가 총 페이지 수 보다 많다면, 끝 번호를 총 페이지 수로 수정
		if (endPageNumber > totalPage) {
			endPageNumber = totalPage;
		}
		
		int prevPage = startPageNumber - BLOCK_SIZE;
		if(prevPage < 1) {
			prevPage = 1;
		}

		int nextPage = endPageNumber + 1;
		if(nextPage > totalPage) {
			nextPage = totalPage;
		}
		boolean isFirst = startPageNumber == 1 ? true : false;
		boolean isLast = endPageNumber == totalPage ? true : false;
		
		ArticlePagination pagination = ArticlePagination.builder()
				.data(articles)
				.page(page)
				.limit(PAGE_SIZE)
				.isFirst(isFirst)
				.isLast(isLast)
				.totalPage(totalPage)
				.startPageNumber(startPageNumber)
				.endPageNumber(endPageNumber)
				.prevPage(prevPage)
				.nextPage(nextPage)
				.build();
		
		
		return pagination;
	}

	@Override
	public ArticleDTO getArticle(int articleId) {
		ArticleDTO article = this.articleDAO.findArticleById(articleId).orElseThrow(NotFoundEntity::new);
		
		
		return article;
	}

	@Transactional(rollbackFor = {Exception.class})
	@Override
	public void createArticle(ArticleDTO articleDTO) {
		
		this.articleDAO.saveArticle(articleDTO);
		
		this.articleImageService.createArticleImages(articleDTO);
	}

}
