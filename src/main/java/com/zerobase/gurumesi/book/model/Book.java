package com.zerobase.gurumesi.book.model;

import com.zerobase.gurumesi.book.book.MakeBookingForm;
import com.zerobase.gurumesi.book.type.Status;
import com.zerobase.gurumesi.user.domain.model.BaseEntity;
import lombok.*;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@AuditOverride(forClass = BaseEntity.class) // 업데이트 될때마다 자동으로 BaseEntity 데이터도 변경됨
@Audited
public class Book extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime bookingTime;

    private Long customerId;
    private Long restaurantId;

    @Builder.Default
    @Enumerated(value = EnumType.STRING)
    private Status status = Status.Requested;

    public void setStatus(Status status){
        this.status=status;
    }

    public static Book of(MakeBookingForm form){
        return Book.builder()
                .customerId(form.getCustomerId())
                .restaurantId(form.getRestaurantId())
                .bookingTime(LocalDateTime.of(
                        form.getYear(), form.getMonth(), form.getDay(),
                                form.getHour(), form.getMinute()))
                .build();
    }
}


