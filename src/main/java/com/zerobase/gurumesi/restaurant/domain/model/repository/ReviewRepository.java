package com.zerobase.gurumesi.restaurant.domain.model.repository;

import com.zerobase.gurumesi.restaurant.domain.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("select avg(coalesce(r.star,0)) from Review r where r.restaurantId =?1")
    Double getAverage(@Param("restaurantId") Long restaurantId);
}
