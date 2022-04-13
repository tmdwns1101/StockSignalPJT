package com.lsj.stockSignal.service;

import java.util.List;

import com.lsj.stockSignal.dto.ReviewDTO;

public interface ReviewService {

	List<ReviewDTO> getReivews(int articleId);
	
	ReviewDTO createReivew(ReviewDTO review);
	
	void deleteReivew(ReviewDTO reviewDTO);
}
