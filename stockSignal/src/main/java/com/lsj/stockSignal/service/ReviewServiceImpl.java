package com.lsj.stockSignal.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lsj.stockSignal.dao.ReviewDAO;
import com.lsj.stockSignal.dto.ReviewDTO;

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
	public void deleteReivew(int reviewId) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
