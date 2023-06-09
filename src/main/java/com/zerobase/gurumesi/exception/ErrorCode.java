package com.zerobase.gurumesi.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    ALREADY_REGISTERED_ACCOUNT(HttpStatus.BAD_REQUEST, "이미 가입된 회원입니다."),
    NOT_FOUND_AVAILABLE_BOOKING(HttpStatus.BAD_REQUEST, "이용 가능한 예약 이력을 찾을 수 없습니다."),
    NOT_FOUND_BOOKING(HttpStatus.BAD_REQUEST, "승인 가능한 예약 이력을 찾을 수 없습니다."),
    NOT_FOUND_USER(HttpStatus.BAD_REQUEST, "사용자를 찾을 수 없습니다."),
    NOT_FOUND_RESTAURANT(HttpStatus.BAD_REQUEST, "식당을 찾을 수 없습니다."),
    NOT_AUTHORIZED_OWNER(HttpStatus.BAD_REQUEST, "해당 식당의 관리자/주인이 아닙니다."),
    PAST_BOOKING(HttpStatus.BAD_REQUEST, "날짜를 확인해주세요(과거 불가)"),
    FULLY_BOOKED(HttpStatus.BAD_REQUEST, "해당 시간대의 테이블 예약이 초과 하였습니다."),
    NOT_BOOKED(HttpStatus.BAD_REQUEST, "예약 이력이 없습니다."),

    //Login
    LOGIN_CHECK_FAIL(HttpStatus.BAD_REQUEST, "아이디나 패스워드를 확인해 주세요.");
    private final HttpStatus httpStatus;
    private final String detail;
}
