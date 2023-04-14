package com.zerobase.gurumesi.restaurant.domain.model;

import com.zerobase.gurumesi.restaurant.domain.restaurant.AddRestaurantForm;
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
public class Restaurant extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long ownerId;
    private String restaurantName;
    private String address;
    private int free;
    private Double star;

    public void setStar(Double star){
        this.star = star;
    }

    public static Restaurant of(Long ownerId, AddRestaurantForm form){
        return Restaurant.builder()
                .ownerId(ownerId)
                .restaurantName(form.getName())
                .address(form.getAddress())
                .free(form.getFree())
                .star(0D)
                .build();
        }


}
