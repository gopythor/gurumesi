package com.zerobase.gurumesi.user.controller;

import com.zerobase.gurumesi.domain.common.UserVo;
import com.zerobase.gurumesi.domain.config.JwtAuthenticationProvider;
import com.zerobase.gurumesi.exception.CustomException;
import com.zerobase.gurumesi.user.domain.customer.CustomerDto;
import com.zerobase.gurumesi.user.domain.model.Customer;
import com.zerobase.gurumesi.user.service.customer.CustomerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.zerobase.gurumesi.exception.ErrorCode.NOT_FOUND_USER;
@Tag(name = "Token check for Customer", description = "Get information from the token")
@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    // 암호화 되어있으므로 프로바이더 가지고 옴
    private final JwtAuthenticationProvider provider;
    private final CustomerService customerService;


    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @GetMapping("/getinfo")
    public ResponseEntity<CustomerDto> getInfo(@RequestHeader(
            name = "X-AUTH-TOKEN") String token){
        UserVo vo = provider.getUserVo(token);
        Customer c = customerService.findByIdAndEmail(vo.getId(), vo.getEmail())
                .orElseThrow(()->new CustomException(NOT_FOUND_USER));
        return ResponseEntity.ok(CustomerDto.from(c));
    }
}
