package com.zerobase.gurumesi.user.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpForm {
    @Schema(example="food@food.co.kr")
    private String email;
    @Schema(example="James Bond")
    private String name;
    @Schema(example="00700")
    private String password;
    @Schema(example="1977-07-07")
    private LocalDate birth;
    @Schema(example="070-007-0070")
    private String phone;
}
