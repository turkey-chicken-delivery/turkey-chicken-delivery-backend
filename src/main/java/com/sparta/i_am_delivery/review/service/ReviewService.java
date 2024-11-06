package com.sparta.i_am_delivery.review.service;


import com.sparta.i_am_delivery.common.exception.CustomException;
import com.sparta.i_am_delivery.common.exception.ErrorCode;
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
import com.sparta.i_am_delivery.review.dto.response.ReviewUpdateResponseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

  private final StoreRepository storeRepository;
  private final OrderRepository orderRepository;
  private final ReviewRepository reviewRepository;

  @Transactional
  public ReviewCreationResponseDto createReview(User user, Long storeId, Long orderId,
      ReviewRequestDto reviewRequestDto) {

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
}
