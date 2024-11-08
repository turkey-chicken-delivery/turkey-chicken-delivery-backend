package com.sparta.i_am_delivery.domain.comment.repository;

import com.sparta.i_am_delivery.domain.comment.entity.Comment;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

  Optional<Comment> findByIdAndUserId(Long reviewId, Long userId);

  boolean existsByReviewIdAndUserId(Long reviewId, Long userId);

  Optional<Comment> findByReviewId(Long reviewId);

  void deleteByStoreId(Long id);

  List<Comment> findAllByReviewId(Long reviewId);
}