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
import com.sparta.i_am_delivery.review.dto.response.CommentUpdateResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    // 해당 유저가 이미 댓글을 작성했는지 확인
    boolean commentExists = commentRepository.existsByReviewIdAndUserId(reviewId, user.getId());
    if (commentExists) {
      throw new CustomException(ErrorCode.COMMENT_DUPLICATE);
    }

    Comment comment = new Comment();
    comment.createComment(store, review, user, commentRequestDto.getContent());
    commentRepository.save(comment);

    return new CommentCreationResponseDto(comment);
  }

  @Transactional
  public CommentUpdateResponseDto updateComment(Long id, User user,
      CommentRequestDto commentRequestDto) {

    // 리뷰 존재 유무 + 작성자 확인
    Comment comment = commentRepository.findByIdAndUserId(id, user.getId())
        .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));

    comment.updateComment(commentRequestDto);
    return new CommentUpdateResponseDto(comment);
  }

  @Transactional
  public void deleteComment(Long storeId, Long id, User user) {
    Comment comment = commentRepository.findById(id)
        .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));

    if (!comment.getStore().getId().equals(storeId)) {
      throw new CustomException(ErrorCode.COMMENT_NOT_BELONG_TO_STORE);
    }
    if (!comment.getUser().getId().equals(user.getId())) {
      throw new CustomException(ErrorCode.NOT_AUTHOR_OF_COMMENT);
    }
    commentRepository.delete(comment);
  }
}
