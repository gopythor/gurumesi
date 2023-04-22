package com.zerobase.gurumesi.user.application;

import com.zerobase.gurumesi.user.service.customer.SignUpCustomerService;
import com.zerobase.gurumesi.user.domain.SignUpForm;
import com.zerobase.gurumesi.exception.CustomException;
import com.zerobase.gurumesi.exception.ErrorCode;
import com.zerobase.gurumesi.user.service.owner.SignUpOwnerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class SignUpApplication {
    private final SignUpCustomerService signUpCustomerService;
    private final SignUpOwnerService signUpOwnerService;

    public String customerSignUp(SignUpForm form) {
        if (signUpCustomerService.isEmailExist(form.getEmail())) {
            throw new CustomException(ErrorCode.ALREADY_REGISTERED_ACCOUNT);
        } else {
            signUpCustomerService.signUp(form);
            return "회원 가입에 성공하였습니다.";
        }
    }

    public String ownerSignUp(SignUpForm form) {
        if (signUpOwnerService.isEmailExist(form.getEmail())) {
            throw new CustomException(ErrorCode.ALREADY_REGISTERED_ACCOUNT);
        } else {
            signUpOwnerService.signUp(form);
            return "회원 가입에 성공하였습니다.";
        }
    }

}
