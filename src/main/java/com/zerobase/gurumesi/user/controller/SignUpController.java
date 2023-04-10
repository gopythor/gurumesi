package com.zerobase.gurumesi.user.controller;

import com.zerobase.gurumesi.user.application.SignUpApplication;
import com.zerobase.gurumesi.user.domain.SignUpForm;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Sign up", description = "Sign up demo")
@RestController
@RequestMapping("/signup")
@RequiredArgsConstructor
public class SignUpController {
    private final SignUpApplication signUpApplication;

    @PostMapping("/customer")
    public ResponseEntity<String> customerSignUp(@RequestBody SignUpForm form) {
        return ResponseEntity.ok(signUpApplication.customerSignUp(form));
    }

    @PostMapping("/owner")
    public ResponseEntity<String> sellerSignUp(@RequestBody SignUpForm form) {
        return ResponseEntity.ok(signUpApplication.ownerSignUp(form));
    }

}
