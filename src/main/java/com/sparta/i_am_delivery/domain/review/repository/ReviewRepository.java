package com.sparta.i_am_delivery.domain.review.repository;

import com.sparta.i_am_delivery.domain.review.entity.Review;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

  @Query("SELECT r FROM Review r JOIN FETCH r.user WHERE r.id = :reviewId AND r.user.id = :userId")
  Optional<Review> findByIdAndUserId(@Param("reviewId") Long reviewId,
      @Param("userId") Long userId);

  Optional<Review> findByOrderId(Long orderId);
}

