package com.zerobase.gurumesi.restaurant.domain.review;


import com.zerobase.gurumesi.restaurant.domain.model.Restaurant;
import com.zerobase.gurumesi.restaurant.type.Star;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddReviewForm {
    @Schema(example = "FIVE")
    private Star star;
    @Schema(example = "1")
    private Long customerID;
    @Schema(example = "1")
    private Long restaurantID;

}
