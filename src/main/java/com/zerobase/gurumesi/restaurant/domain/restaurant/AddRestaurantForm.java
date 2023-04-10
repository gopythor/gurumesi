package com.zerobase.gurumesi.restaurant.domain.restaurant;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddRestaurantForm {
    @Schema(example = "북경반점")
    private String name;
    @Schema(example = "강동구")
    private String address;
    @Schema(example = "10")
    private int free;
}
