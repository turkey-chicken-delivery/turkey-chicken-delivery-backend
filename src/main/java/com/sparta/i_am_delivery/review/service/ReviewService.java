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
    Order order = orderRepository.findByIdAndStatus(orderId, OrderStatus.COMPLETED)
        .orElseThrow(() -> new CustomException(ErrorCode.ORDER_NOT_COMPLETED));

    if (reviewRepository.findByOrderId(orderId).isPresent()) {
      throw new CustomException(ErrorCode.REVIEW_ALREADY_EXISTS);
    }
    Review review = new Review();
    review.createReview(user, store, order, reviewRequestDto.getConmment(),
        reviewRequestDto.getStar());
    reviewRepository.save(review);

    return new ReviewCreationResponseDto(review);
  }
}