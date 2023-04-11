package com.zerobase.gurumesi.restaurant.domain.restaurant;

import com.zerobase.gurumesi.restaurant.domain.model.Restaurant;
import com.zerobase.gurumesi.restaurant.service.RestaurantService;
import lombok.*;

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
    private double star;



    public static RestaurantDto search(Restaurant restaurant){
        return RestaurantDto.builder()
                .restaurantName(restaurant.getRestaurantName())
                .address(restaurant.getAddress())
                .star(restaurant.getStar())
                .build();
    }
    public static RestaurantDto from(Restaurant restaurant){
        return RestaurantDto.builder()
                .id(restaurant.getId())
                .ownerId(restaurant.getOwnerId())
                .restaurantName(restaurant.getRestaurantName())
                .address(restaurant.getAddress())
                .free(restaurant.getFree())
                .star(restaurant.getStar())
                .build();
    }


}

