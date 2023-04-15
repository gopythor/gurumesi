package com.zerobase.gurumesi.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    ALREADY_REGISTERED_ACCOUNT(HttpStatus.BAD_REQUEST, "이미 가입된 회원입니다."),
    NOT_FOUND_RESTAURANT(HttpStatus.BAD_REQUEST, "식당을 찾을 수 없습니다."),
    NOT_FOUND_BOOKING(HttpStatus.BAD_REQUEST, "승인 가능한 예약 이력을 찾을 수 없습니다."),
    PAST_BOOKING(HttpStatus.BAD_REQUEST, "날짜를 확인해주세요(과거 불가)"),
    FULLY_BOOKED(HttpStatus.BAD_REQUEST, "해당 시간대의 테이블 예약이 초과 하였습니다."),
    NOT_BOOKED(HttpStatus.BAD_REQUEST, "예약 이력이 없습니다.");
    private final HttpStatus httpStatus;
    private final String detail;
}
