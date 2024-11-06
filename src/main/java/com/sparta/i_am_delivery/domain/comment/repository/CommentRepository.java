package com.sparta.i_am_delivery.domain.comment.repository;

import com.sparta.i_am_delivery.domain.comment.entity.Comment;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

  boolean existsByReviewIdAndUserId(Long reviewId, Long userId);

  Optional<Comment> findByReviewId(Long reviewId);
}