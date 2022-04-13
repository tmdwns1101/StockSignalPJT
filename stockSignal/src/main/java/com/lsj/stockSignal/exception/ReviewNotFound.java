package com.lsj.stockSignal.exception;

public class ReviewNotFound extends RuntimeException {

	public ReviewNotFound() {
		super("해당 리뷰를 찾을 수 없습니다.");
		
	}
}
