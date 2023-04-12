package com.zerobase.gurumesi.restaurant.domain.model.repository;

import com.zerobase.gurumesi.restaurant.domain.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
}
