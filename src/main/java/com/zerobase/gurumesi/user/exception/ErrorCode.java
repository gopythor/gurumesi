package com.zerobase.gurumesi.user.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    ALREADY_REGISTERED_ACCOUNT(HttpStatus.BAD_REQUEST, "이미 가입된 회원입니다.");
    private final HttpStatus httpStatus;
    private final String detail;
}
