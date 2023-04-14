package com.zerobase.gurumesi.book.book;


import com.zerobase.gurumesi.book.model.Book;
import com.zerobase.gurumesi.restaurant.domain.model.Restaurant;
import com.zerobase.gurumesi.restaurant.domain.restaurant.RestaurantDto;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BookDto {
    private Long id;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private long customerId;
    private long restaurantId;

    public static BookDto from(Book book){

        return BookDto.builder()
                .id(book.getId())
                .year(book.getYear())
                .month(book.getMonth())
                .day(book.getDay())
                .hour(book.getHour())
                .minute(book.getMinute())
                .customerId(book.getCustomerId())
                .restaurantId(book.getRestaurantId())
                .build();
    }
}
