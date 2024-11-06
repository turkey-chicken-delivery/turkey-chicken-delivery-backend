package com.sparta.i_am_delivery.domain.like.repository;

import com.sparta.i_am_delivery.domain.like.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

  boolean existsByUserIdAndStoreId(Long userId, Long storeId);

  @Query(
      "SELECT l FROM Like l JOIN FETCH l.user JOIN FETCH l.store WHERE l.id = :likeId AND l.user.id = :userId")
  Optional<Like> findByIdAndUserId(@Param("likeId") Long likeId, @Param("userId") Long userId);

  void deleteByUserId(Long id);

  void deleteByStoreId(Long storeId);
}
