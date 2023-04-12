package com.zerobase.gurumesi.restaurant.domain.model;

import org.springframework.data.jpa.repository.Query;

public interface RestaurantMapping {
    Long getId();
    String getRestaurantName();
    String getAddress();
    Double getStar();
}
