package com.lsj.stockSignal.exception;

public class NotAllowdEditArticle extends RuntimeException {

	public NotAllowdEditArticle() {
		super("해당 게시글이 없거나 비밀번호가 일치하지 않아 수정 할 수 없습니다.");
	}
}
