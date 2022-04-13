package com.lsj.stockSignal.exception;

public class ArticleNotFound extends RuntimeException {
	
	public ArticleNotFound() {
		super("해당 개체를 찾을 수 없습니다.");
	}
}
