package com.zerobase.gurumesi.book.book;


import com.zerobase.gurumesi.book.model.Book;
import com.zerobase.gurumesi.restaurant.domain.model.Restaurant;
import com.zerobase.gurumesi.restaurant.domain.restaurant.RestaurantDto;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BookDto {
    private Long id;
    private LocalDateTime localDateTime;
    private Long customerId;
    private Long restaurantId;

    public static BookDto from(Book book){

        return BookDto.builder()
                .id(book.getId())
                .localDateTime(book.getBookingTime())
                .customerId(book.getCustomerId())
                .restaurantId(book.getRestaurantId())
                .build();
    }
}
