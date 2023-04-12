package com.zerobase.gurumesi.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    ALREADY_REGISTERED_ACCOUNT(HttpStatus.BAD_REQUEST, "이미 가입된 회원입니다."),
    NOT_FOUND_RESTAURANT(HttpStatus.BAD_REQUEST, "식당을 찾을 수 없습니다.");
    private final HttpStatus httpStatus;
    private final String detail;
}
