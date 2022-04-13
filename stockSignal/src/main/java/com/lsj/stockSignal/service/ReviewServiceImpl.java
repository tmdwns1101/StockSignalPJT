package com.lsj.stockSignal.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lsj.stockSignal.dao.ReviewDAO;
import com.lsj.stockSignal.dto.ReviewDTO;
import com.lsj.stockSignal.exception.ArticleNotFound;
import com.lsj.stockSignal.exception.PasswordNotCorrect;
import com.lsj.stockSignal.exception.ReviewNotFound;

import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

	private final ReviewDAO reivewDAO;

	@Override
	public List<ReviewDTO> getReivews(int articleId) {
		return this.reivewDAO.findReviews(articleId);
	}

	@Override
	public ReviewDTO createReivew(ReviewDTO review) {
		this.reivewDAO.createReview(review);
		return review;
	}

	@Override
	public void deleteReivew(ReviewDTO reviewDTO) {
		
		ReviewDTO review = this.reivewDAO.findReview(reviewDTO.getId()).orElseThrow(ReviewNotFound::new);
		
		if(!review.getPassword().equals(reviewDTO.getPassword())) {
			throw new PasswordNotCorrect();
		}
		
		this.reivewDAO.deleteReview(review.getId());
		
	}
	
	
	
}
