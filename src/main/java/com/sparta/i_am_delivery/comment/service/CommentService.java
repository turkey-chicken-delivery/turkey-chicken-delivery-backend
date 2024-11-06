package com.sparta.i_am_delivery.comment.service;


import com.sparta.i_am_delivery.comment.dto.request.CommentRequestDto;
import com.sparta.i_am_delivery.comment.dto.response.CommentCreationResponseDto;
import com.sparta.i_am_delivery.common.exception.CustomException;
import com.sparta.i_am_delivery.common.exception.ErrorCode;
import com.sparta.i_am_delivery.domain.comment.entity.Comment;
import com.sparta.i_am_delivery.domain.comment.repository.CommentRepository;
import com.sparta.i_am_delivery.domain.review.entity.Review;
import com.sparta.i_am_delivery.domain.review.repository.ReviewRepository;
import com.sparta.i_am_delivery.domain.store.entity.Store;
import com.sparta.i_am_delivery.domain.store.repository.StoreRepository;
import com.sparta.i_am_delivery.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

  private final StoreRepository storeRepository;
  private final ReviewRepository reviewRepository;
  private final CommentRepository commentRepository;

  public CommentCreationResponseDto createComment(Long storeId, Long reviewId, User user,
      CommentRequestDto commentRequestDto) {

    // 가게 조회 시 댓글 작성자와 일치하는지 확인 후 조회
    Store store = storeRepository.findByIdAndUserId(storeId, user.getId())
        .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND));

    Review review = reviewRepository.findById(reviewId)
        .orElseThrow(() -> new CustomException(ErrorCode.REVIEW_NOT_FOUND));

    // 이미 해당 유저가 이미 댓글을 작성했는지 확인
    boolean commentExists = commentRepository.existsByReviewIdAndUserId(reviewId, user.getId());
    if (commentExists) {
      throw new CustomException(ErrorCode.DUPLICATE_COMMENT);
    }

    Comment comment = new Comment();
    comment.createComment(store, review, user, commentRequestDto.getComment());
    commentRepository.save(comment);

    return new CommentCreationResponseDto(comment);
  }
}
