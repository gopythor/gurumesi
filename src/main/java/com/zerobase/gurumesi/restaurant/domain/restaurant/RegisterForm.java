package com.zerobase.gurumesi.restaurant.domain.restaurant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterForm {
    private Long sellerId;
    private String restaurantName;
    private String address;
    private int free;
}
