package com.zerobase.gurumesi.book.model;

import com.zerobase.gurumesi.book.book.MakeBookingForm;
import com.zerobase.gurumesi.book.type.Status;
import com.zerobase.gurumesi.user.domain.model.BaseEntity;
import lombok.*;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@AuditOverride(forClass = BaseEntity.class) // Customer 업데이트 될때마다 자동으로 BaseEntity 데이터도 변경됨
@Audited
public class Book extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private long customerId;
    private long restaurantId;

    @Builder.Default
    private Status status = Status.Requested;

    public static Book of(MakeBookingForm form){
        return Book.builder()
                .customerId(form.getCustomerId())
                .restaurantId(form.getRestaurantId())
                .year(form.getYear())
                .month(form.getMonth())
                .day(form.getDay())
                .hour(form.getHour())
                .minute(form.getMinute())
                .build();
    }
}


