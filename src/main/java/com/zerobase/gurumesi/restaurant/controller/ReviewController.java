package com.zerobase.gurumesi.restaurant.controller;

import com.zerobase.gurumesi.domain.common.UserVo;
import com.zerobase.gurumesi.domain.config.JwtAuthenticationProvider;
import com.zerobase.gurumesi.restaurant.domain.model.Restaurant;
import com.zerobase.gurumesi.restaurant.domain.restaurant.RestaurantDto;
import com.zerobase.gurumesi.restaurant.domain.review.AddReviewForm;
import com.zerobase.gurumesi.restaurant.domain.review.ReviewDto;
import com.zerobase.gurumesi.restaurant.service.ReviewService;
import com.zerobase.gurumesi.restaurant.type.Star;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
@Tag(name = "Review Control", description = "Write review and " +
        "possibility to write it.")
public class ReviewController {
    private final JwtAuthenticationProvider provider;
    private final ReviewService reviewService;


    //리뷰 가능한 이용 목록 보기
    @GetMapping
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @Operation(summary = "Waiting for reviews")
    public ResponseEntity<?> viewAvailableReviewByCustomer(
            @RequestHeader(name = "X-AUTH-TOKEN") String token){
        UserVo vo = provider.getUserVo(token);
        return ResponseEntity.ok(reviewService.viewAvailableReview(vo.getId()));
    }

    //리뷰 쓰기(별점)
    @PostMapping
    @Operation(summary = "put a review")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<?> giveReview(@RequestHeader(name = "X-AUTH-TOKEN") String token,
            @RequestBody AddReviewForm form){
        UserVo vo = provider.getUserVo(token);
        return ResponseEntity.ok(
                ReviewDto.from( reviewService.giveStar(vo.getId(),form)));
    }
}
