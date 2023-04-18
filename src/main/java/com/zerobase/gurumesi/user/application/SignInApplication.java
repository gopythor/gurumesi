package com.zerobase.gurumesi.user.application;

import com.zerobase.gurumesi.exception.CustomException;
import com.zerobase.gurumesi.exception.ErrorCode;
import com.zerobase.gurumesi.user.domain.SignInForm;
import com.zerobase.gurumesi.user.domain.model.Customer;
import com.zerobase.gurumesi.user.service.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignInApplication {

    private final CustomerService customerService;

    public String customerLoginToken(SignInForm form){
        // 1. 로그인 가능 여부
        Customer c = customerService.findValidCustomer(form.getEmail(),
                form.getPassword())
                .orElseThrow(()->new CustomException(ErrorCode.LOGIN_CHECK_FAIL));
        // 2. 토큰을 발행하고
        // 3. 토큰을 response한다.

        return "";
    }
}
