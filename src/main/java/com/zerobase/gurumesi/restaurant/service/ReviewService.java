package com.zerobase.gurumesi.restaurant.service;

import com.zerobase.gurumesi.exception.CustomException;
import com.zerobase.gurumesi.restaurant.domain.model.Restaurant;
import com.zerobase.gurumesi.restaurant.domain.model.Review;
import com.zerobase.gurumesi.restaurant.domain.model.repository.RestaurantRepository;
import com.zerobase.gurumesi.restaurant.domain.model.repository.ReviewRepository;
import com.zerobase.gurumesi.restaurant.domain.review.AddReviewForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.zerobase.gurumesi.exception.ErrorCode.NOT_FOUND_RESTAURANT;

@RequiredArgsConstructor
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;

    @Transactional
    public Review giveStar(AddReviewForm form){
        Review review = reviewRepository.save(Review.of(form));
        Restaurant restaurant = restaurantRepository.findById(review.getRestaurantId())
               .orElseThrow(() -> new CustomException(NOT_FOUND_RESTAURANT));
       Double avg = reviewRepository.getAverage(restaurant.getId());
       restaurant.setStar(avg);
       return review;
    }
}
