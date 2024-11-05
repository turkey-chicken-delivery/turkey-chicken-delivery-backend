package com.sparta.i_am_delivery.domain.review.repository;

import com.sparta.i_am_delivery.domain.review.entity.Review;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

  Optional<Review> findByOrderId(Long orderId);
}

