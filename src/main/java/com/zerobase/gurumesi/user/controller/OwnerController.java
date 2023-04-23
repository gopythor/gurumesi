package com.zerobase.gurumesi.user.controller;

import com.zerobase.gurumesi.domain.common.UserVo;
import com.zerobase.gurumesi.domain.config.JwtAuthenticationProvider;
import com.zerobase.gurumesi.exception.CustomException;
import com.zerobase.gurumesi.user.domain.model.Owner;
import com.zerobase.gurumesi.user.domain.owner.OwnerDto;
import com.zerobase.gurumesi.user.service.owner.OwnerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.zerobase.gurumesi.exception.ErrorCode.NOT_FOUND_USER;

@Tag(name = "Token check for Owner", description = "Get information from the token")
@RestController
@RequestMapping("/owner")
@RequiredArgsConstructor
public class OwnerController {

    // 암호화 되어있으므로 프로바이더 가지고 옴
    private final JwtAuthenticationProvider provider;
    private final OwnerService ownerService;


    @PreAuthorize("hasRole('ROLE_OWNER')")
    @GetMapping("/getinfo")
    public ResponseEntity<OwnerDto> getInfo(@RequestHeader(
            name = "X-AUTH-TOKEN") String token){
        UserVo vo = provider.getUserVo(token);
        Owner o = ownerService.findByIdAndEmail(vo.getId(), vo.getEmail())
                .orElseThrow(()->new CustomException(NOT_FOUND_USER));
        return ResponseEntity.ok(OwnerDto.from(o));
    }
}
