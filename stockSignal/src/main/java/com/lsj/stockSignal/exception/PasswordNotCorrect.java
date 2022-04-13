package com.lsj.stockSignal.exception;

public class PasswordNotCorrect extends RuntimeException {

	public PasswordNotCorrect() {
		super("패스워드가 일치하지 않습니다.");
	}
}
