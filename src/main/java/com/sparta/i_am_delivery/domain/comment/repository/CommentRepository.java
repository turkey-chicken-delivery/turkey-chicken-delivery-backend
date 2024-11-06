package com.sparta.i_am_delivery.domain.comment.repository;

import com.sparta.i_am_delivery.domain.comment.entity.Comment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

  boolean existsByReviewIdAndUserId(Long reviewId, Long userId);

  Comment findByReviewId(Long reviewId);

  void deleteByStoreId(Long storeId);

  List<Comment> findAllByReviewId(Long reviewId);

}