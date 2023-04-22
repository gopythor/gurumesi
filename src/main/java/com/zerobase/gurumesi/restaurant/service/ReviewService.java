package com.zerobase.gurumesi.restaurant.service;

import com.zerobase.gurumesi.book.book.BookDetailDto;
import com.zerobase.gurumesi.book.model.Book;
import com.zerobase.gurumesi.book.model.repository.BookRepository;
import com.zerobase.gurumesi.book.type.Status;
import com.zerobase.gurumesi.exception.CustomException;
import com.zerobase.gurumesi.exception.ErrorCode;
import com.zerobase.gurumesi.restaurant.domain.model.Restaurant;
import com.zerobase.gurumesi.restaurant.domain.model.Review;
import com.zerobase.gurumesi.restaurant.domain.model.repository.RestaurantRepository;
import com.zerobase.gurumesi.restaurant.domain.model.repository.ReviewRepository;
import com.zerobase.gurumesi.restaurant.domain.review.AddReviewForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.zerobase.gurumesi.exception.ErrorCode.NOT_FOUND_AVAILABLE_BOOKING;
import static com.zerobase.gurumesi.exception.ErrorCode.NOT_FOUND_RESTAURANT;

@RequiredArgsConstructor
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;
    private final BookRepository bookRepository;

    @Transactional
    public Review giveStar(Long customerId, AddReviewForm form){
        Review review = reviewRepository.save(Review.of(customerId, form));
        Restaurant restaurant = restaurantRepository.findById(review.getRestaurantId())
               .orElseThrow(() -> new CustomException(NOT_FOUND_RESTAURANT));
        Book book = bookRepository.findById(form.getBookingID())
                .orElseThrow(() -> new CustomException(NOT_FOUND_AVAILABLE_BOOKING));

        // 사용 완료되었고, 고객 ID가 일치하는가?
        if(book.getStatus()==Status.Complete && book.getCustomerId() == customerId){
            Double avg = reviewRepository.getAverage(restaurant.getId());
            restaurant.setStar(avg);
            book.setStatus(Status.Reviewed);
        } else {
            throw new CustomException(ErrorCode.NOT_FOUND_AVAILABLE_BOOKING);
        }
        return review;
    }

    //고객 ID로 리뷰 가능한 리스트 보기
    public List<Book> viewAvailableReview(Long customerId){
        return bookRepository.findAllByCustomerIdAndStatus(customerId, Status.Complete);
    }


}
