package com.sparta.i_am_delivery.review.service;


import com.sparta.i_am_delivery.common.exception.CustomException;
import com.sparta.i_am_delivery.common.exception.ErrorCode;
import com.sparta.i_am_delivery.domain.order.entity.Order;
import com.sparta.i_am_delivery.domain.order.repository.OrderRepository;
import com.sparta.i_am_delivery.domain.review.entity.Review;
import com.sparta.i_am_delivery.domain.store.entity.Store;
import com.sparta.i_am_delivery.domain.store.repository.StoreRepository;
import com.sparta.i_am_delivery.domain.user.entity.User;
import com.sparta.i_am_delivery.domain.user.repository.UserRepository;
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
  private final UserRepository userRepository;

  @Transactional
  public ReviewCreationResponseDto createReview(User jwtUser, Long storeId, Long orderId,
      ReviewRequestDto reviewRequestDto) {

    User user = userRepository.findById(jwtUser.getId())
        .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    Store store = storeRepository.findById(storeId)
        .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND));
    Order order = orderRepository.findById(orderId)
        .orElseThrow(() -> new CustomException(ErrorCode.ORDER_NOT_FOUND));

    Review review = new Review();
    review.createReview(user, store, order, reviewRequestDto.getConmment(),
        reviewRequestDto.getStar());
    
    return new ReviewCreationResponseDto(review);
  }
}
