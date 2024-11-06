package com.sparta.i_am_delivery.domain.comment.repository;

import com.sparta.i_am_delivery.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

  boolean existsByReviewIdAndUserId(Long reviewId, Long userId);

  Comment findByReviewId(Long reviewId);

  void deleteByStoreId(Long storeId);
}