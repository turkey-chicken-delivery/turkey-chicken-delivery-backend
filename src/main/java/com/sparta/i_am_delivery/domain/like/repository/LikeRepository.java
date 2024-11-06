package com.sparta.i_am_delivery.domain.like.repository;

import com.sparta.i_am_delivery.domain.like.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {

  boolean existsByUserIdAndStoreId(Long userId, Long storeId);

  void deleteByStoreId(Long storeId);
}
