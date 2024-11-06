package com.sparta.i_am_delivery.domain.review.repository;

import com.sparta.i_am_delivery.domain.review.entity.Review;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

  // JOIN FETCH를 사용해서 리뷰 ID와 사용자 ID로 리뷰를 조회하여 해당 리뷰가 특정 사용자에 의해 작성된 것인지 확인
  @Query("SELECT r FROM Review r JOIN FETCH r.user WHERE r.id = :reviewId AND r.user.id = :userId")
  Optional<Review> findByIdAndUserId(@Param("reviewId") Long reviewId,
      @Param("userId") Long userId);

  Optional<Review> findByOrderId(Long orderId);

  boolean existsByOrderId(Long orderId);
}

