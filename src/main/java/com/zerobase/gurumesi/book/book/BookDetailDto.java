package com.zerobase.gurumesi.book.book;

import com.zerobase.gurumesi.book.type.Status;

import java.time.LocalDateTime;

public interface BookDetailDto {
    Long getId();
    LocalDateTime getBooking_time();
    Long getCustomer_Id();
    Long getRestaurant_Id();
    String getName();
    String getPhone();
    Status getStatus();
}
