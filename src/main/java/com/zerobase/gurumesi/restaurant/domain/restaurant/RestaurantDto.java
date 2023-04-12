package com.zerobase.gurumesi.restaurant.domain.restaurant;

import com.zerobase.gurumesi.restaurant.domain.model.Restaurant;
import com.zerobase.gurumesi.restaurant.service.RestaurantService;
import lombok.*;
import org.springframework.data.jpa.repository.Query;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RestaurantDto {
    private Long id;
    private Long ownerId;
    private String restaurantName;
    private String address;
    private int free;


    public static RestaurantDto from(Restaurant restaurant){

        return RestaurantDto.builder()
                .id(restaurant.getId())
                .ownerId(restaurant.getOwnerId())
                .restaurantName(restaurant.getRestaurantName())
                .address(restaurant.getAddress())
                .free(restaurant.getFree())
                .build();
    }


}

