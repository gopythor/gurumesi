package com.zerobase.gurumesi.restaurant.domain.model.repository;

import com.zerobase.gurumesi.restaurant.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository  extends JpaRepository<Restaurant, Long> {

}
