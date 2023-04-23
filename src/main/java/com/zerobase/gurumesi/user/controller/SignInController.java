package com.zerobase.gurumesi.user.controller;

import com.zerobase.gurumesi.user.application.SignInApplication;
import com.zerobase.gurumesi.user.domain.SignInForm;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Sign In controller", description = "Sign in both customer and Owner")
@RestController
@RequestMapping(value = "/signin")
@RequiredArgsConstructor
public class SignInController {

    private final SignInApplication signInApplication;

    @PostMapping("/customer")
    public ResponseEntity<String> signInCustomer(@RequestBody SignInForm form){
        return ResponseEntity.ok(signInApplication.customerLoginToken(form));
    }
    @PostMapping("/owner")
    public ResponseEntity<String> signInOwner(@RequestBody SignInForm form){
        return ResponseEntity.ok(signInApplication.ownerLoginToken(form));
    }
}
