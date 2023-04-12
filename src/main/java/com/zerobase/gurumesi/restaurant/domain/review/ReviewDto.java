package com.zerobase.gurumesi.restaurant.domain.review;

import com.zerobase.gurumesi.restaurant.domain.model.Restaurant;
import com.zerobase.gurumesi.restaurant.domain.model.Review;
import com.zerobase.gurumesi.restaurant.domain.restaurant.RestaurantDto;
import com.zerobase.gurumesi.restaurant.type.Star;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ReviewDto {
    private Long id;
    private Star star;
    private Long restaurantId;
    private Long customerId;

    public static ReviewDto from(Review review){

        return ReviewDto.builder()
                .id(review.getId())
                .star(review.getStar())
                .restaurantId(review.getRestaurantId())
                .customerId(review.getCustomerId())
                .build();
    }
}
