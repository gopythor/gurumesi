package com.zerobase.gurumesi.restaurant.domain.model.repository;

import com.zerobase.gurumesi.restaurant.domain.model.Restaurant;
import com.zerobase.gurumesi.restaurant.domain.model.RestaurantMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RestaurantRepository  extends JpaRepository<Restaurant, Long> {
    List<RestaurantMapping> findAllByOrderByRestaurantNameAsc();
    List<RestaurantMapping> findAllByOrderByStarAsc();
    List<RestaurantMapping> findAllByOrderByAddressAsc();


}
