package com.zerobase.gurumesi.restaurant.domain.model;

import com.zerobase.gurumesi.restaurant.domain.review.AddReviewForm;
import com.zerobase.gurumesi.restaurant.type.Star;
import com.zerobase.gurumesi.user.domain.model.BaseEntity;
import lombok.*;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@AuditOverride(forClass = BaseEntity.class) // Customer 업데이트 될때마다 자동으로 BaseEntity 데이터도 변경됨
@Audited
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Star star;
    private Long restaurantId;
    private Long customerId;

    public static Review of(AddReviewForm form){
        return Review.builder()
                .restaurantId(form.getRestaurantID())
                .customerId(form.getCustomerID())
                .star(form.getStar())
                .build();
    }

}
