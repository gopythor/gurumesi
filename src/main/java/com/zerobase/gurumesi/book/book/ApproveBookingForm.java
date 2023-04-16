package com.zerobase.gurumesi.book.book;

import com.zerobase.gurumesi.book.type.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApproveBookingForm {
    Long ownerId;
    Long restaurantId;
    Long bookingId;
}
