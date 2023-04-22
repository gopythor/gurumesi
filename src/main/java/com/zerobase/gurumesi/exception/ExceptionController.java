package com.zerobase.gurumesi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.ServletException;

@ControllerAdvice
@Slf4j
public class ExceptionController {
    @ExceptionHandler({
            CustomException.class
    })
    public ResponseEntity<ExceptionResponse> customRequestException(final CustomException c) {
        log.warn("api Exception : {}", c.getErrorCode());
        return ResponseEntity.badRequest().body(new ExceptionResponse(c.getMessage(), c.getErrorCode()));
    }

    //컨트롤러 들어가기 전에 발생해서 접근 안됨.
//    @ExceptionHandler({
//            ServletException.class
//    })
//    public ResponseEntity<String> ServletException(final CustomException c) {
//        log.warn("api Exception : {}", c.getErrorCode());
//        return ResponseEntity.badRequest().body("잘못된 인증 시도.");
//    }

    @Getter
    @ToString
    @AllArgsConstructor
    public static class ExceptionResponse {
        private String message;
        private ErrorCode errorCode;
    }
}