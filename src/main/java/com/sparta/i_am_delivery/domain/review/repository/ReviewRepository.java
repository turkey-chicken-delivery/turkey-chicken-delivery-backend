package com.sparta.i_am_delivery.domain.review.repository;

import com.sparta.i_am_delivery.domain.review.entity.Review;
import com.sparta.i_am_delivery.domain.store.entity.Store;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

  // 리뷰와 작성자 확인
  Optional<Review> findByIdAndUserId(Long reviewId, Long userId);

  Page<Review> findByStore(Store store, Pageable pageable);

  boolean existsByOrderId(Long orderId);

  void deleteByStoreId(Long storeId);

  Optional<Review> findByOrderId(Long orderId);
}

