package com.sparta.i_am_delivery.review.service;

import com.sparta.i_am_delivery.comment.dto.response.CommentResponseDto;
import com.sparta.i_am_delivery.common.exception.CustomException;
import com.sparta.i_am_delivery.common.exception.ErrorCode;
import com.sparta.i_am_delivery.domain.comment.entity.Comment;
import com.sparta.i_am_delivery.domain.comment.repository.CommentRepository;
import com.sparta.i_am_delivery.domain.order.entity.Order;
import com.sparta.i_am_delivery.domain.order.repository.OrderRepository;
import com.sparta.i_am_delivery.domain.review.entity.Review;
import com.sparta.i_am_delivery.domain.review.repository.ReviewRepository;
import com.sparta.i_am_delivery.domain.store.entity.Store;
import com.sparta.i_am_delivery.domain.store.repository.StoreRepository;
import com.sparta.i_am_delivery.domain.user.entity.User;
import com.sparta.i_am_delivery.order.enums.OrderStatus;
import com.sparta.i_am_delivery.review.dto.request.ReviewRequestDto;
import com.sparta.i_am_delivery.review.dto.response.ReviewCreationResponseDto;
import com.sparta.i_am_delivery.review.dto.response.ReviewListResponseDto;
import com.sparta.i_am_delivery.review.dto.response.ReviewResponseDto;
import com.sparta.i_am_delivery.review.dto.response.ReviewUpdateResponseDto;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

  private final StoreRepository storeRepository;
  private final OrderRepository orderRepository;
  private final ReviewRepository reviewRepository;
  private final CommentRepository commentRepository;

  @Transactional
  public ReviewCreationResponseDto createReview(User user, Long storeId, Long orderId,
      ReviewRequestDto reviewRequestDto) {

    // 가게 조회
    Store store = storeRepository.findById(storeId)
        .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND));

    Order order = orderRepository.findByIdAndStatus(orderId,
            OrderStatus.COMPLETED)
        .orElseThrow(() -> new CustomException(ErrorCode.ORDER_NOT_COMPLETED));

    // 이미 해당 유저가 리뷰를 작성했는지 확인
    boolean commentExists = reviewRepository.existsByOrderId(order.getId());
    if (commentExists) {
      throw new CustomException(ErrorCode.REVIEW_DUPLICATE);
    }

    Review review = new Review();
    review.createReview(user, store, order, reviewRequestDto.getComment(),
        reviewRequestDto.getStar());
    reviewRepository.save(review);

    return new ReviewCreationResponseDto(review);
  }

  @Transactional
  public ReviewUpdateResponseDto updateReview(User user, Long reviewId,
      ReviewRequestDto reviewRequestDto) {

    // 리뷰 존재 여부 및 작성자 확인
    Review review = reviewRepository.findByIdAndUserId(reviewId, user.getId())
        .orElseThrow(() -> new CustomException(ErrorCode.REVIEW_NOT_FOUND));  // 리뷰 존재 유무 확인

    review.updateReview(reviewRequestDto.getComment(), reviewRequestDto.getStar());
    return new ReviewUpdateResponseDto(review);
  }

  public ReviewListResponseDto getAllReview(Long storeId, int page) {
    // 페이지네이션을 위한 Pageable 객체 생성
    Pageable pageable = PageRequest.of(page - 1, 10,
        Sort.by(Sort.Order.desc("createdAt"))); // 페이지 크기 10, 페이지 번호는 0부터 시작

    // Store 존재 여부 확인
    Store store = storeRepository.findById(storeId)
        .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND));

    // 해당 가게에 대한 리뷰를 페이지 단위로 조회
    Page<Review> reviewPage = reviewRepository.findByStore(store, pageable);

    // 리뷰 목록을 DTO로 변환
    List<ReviewResponseDto> reviewResponseDtos = reviewPage.getContent().stream()
        .map(review -> {
          // 각 리뷰에 대해 댓글을 조회 (한 개의 댓글만 존재)
          CommentResponseDto commentResponseDto = commentRepository.findByReviewId(review.getId())
              .map(CommentResponseDto::new)
              .orElse(null); // 댓글이 없을 수도 있으므로 null 처리

          return new ReviewResponseDto(review, commentResponseDto);
        })
        .collect(Collectors.toList());

    int totalPages = reviewPage.getTotalPages();

    return new ReviewListResponseDto(reviewResponseDtos, reviewPage.getSize(), page, totalPages);
  }

  @Transactional
  public void deleteReview(Long storeId, Long id, User user) {

    Review review = reviewRepository.findById(id)
        .orElseThrow(() -> new CustomException(ErrorCode.REVIEW_NOT_FOUND));

    Comment comment = commentRepository.findByReviewId(review.getId())
        .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));

    if (!review.getStore().getId().equals(storeId)) {
      throw new CustomException(ErrorCode.REVIEW_NOT_BELONG_TO_STORE);
    }
    if (!review.getUser().getId().equals(user.getId())) {
      throw new CustomException(ErrorCode.NOT_AUTHOR_OF_REVIEW);
    }

    commentRepository.delete(comment);
    reviewRepository.delete(review);
  }

}
