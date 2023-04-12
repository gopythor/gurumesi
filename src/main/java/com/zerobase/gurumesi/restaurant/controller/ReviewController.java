package com.zerobase.gurumesi.restaurant.controller;

import com.zerobase.gurumesi.restaurant.domain.model.Restaurant;
import com.zerobase.gurumesi.restaurant.domain.restaurant.RestaurantDto;
import com.zerobase.gurumesi.restaurant.domain.review.AddReviewForm;
import com.zerobase.gurumesi.restaurant.domain.review.ReviewDto;
import com.zerobase.gurumesi.restaurant.service.ReviewService;
import com.zerobase.gurumesi.restaurant.type.Star;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<?> giveReview(@RequestBody AddReviewForm form){

        return ResponseEntity.ok(
                ReviewDto.from( reviewService.giveStar(form)));
    }
}
