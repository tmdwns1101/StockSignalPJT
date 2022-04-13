package com.lsj.stockSignal.exception;

public class NotFoundEntity extends RuntimeException {
	
	public NotFoundEntity() {
		super("해당 개체를 찾을 수 없습니다.");
	}
}
