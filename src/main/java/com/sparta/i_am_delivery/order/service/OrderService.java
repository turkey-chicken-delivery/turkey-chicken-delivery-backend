package com.sparta.i_am_delivery.order.service;

import com.sparta.i_am_delivery.common.exception.CustomException;
import com.sparta.i_am_delivery.common.exception.ErrorCode;
import com.sparta.i_am_delivery.domain.comment.entity.Comment;
import com.sparta.i_am_delivery.domain.comment.repository.CommentRepository;
import com.sparta.i_am_delivery.domain.menu.entity.Menu;
import com.sparta.i_am_delivery.domain.menu.repository.MenuRepository;
import com.sparta.i_am_delivery.domain.order.entity.Order;
import com.sparta.i_am_delivery.domain.order.repository.OrderRepository;
import com.sparta.i_am_delivery.domain.review.repository.ReviewRepository;
import com.sparta.i_am_delivery.domain.store.entity.Store;
import com.sparta.i_am_delivery.domain.store.repository.StoreRepository;
import com.sparta.i_am_delivery.domain.user.entity.User;
import com.sparta.i_am_delivery.domain.user.repository.UserRepository;
import com.sparta.i_am_delivery.order.dto.request.OrderRequestDto;
import com.sparta.i_am_delivery.order.dto.request.OrderStatusRequestDto;
import com.sparta.i_am_delivery.order.dto.response.CreateResponseDto;
import com.sparta.i_am_delivery.order.dto.response.DeliveryStatusResponseDto;
import com.sparta.i_am_delivery.order.dto.response.OrderDetailResponseDto;
import com.sparta.i_am_delivery.order.dto.response.UpdatedResponseDto;
import com.sparta.i_am_delivery.order.enums.OrderStatus;
import jakarta.transaction.Transactional;
import java.time.LocalTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;
  private final UserRepository userRepository;
  private final StoreRepository storeRepository;
  private final MenuRepository menuRepository;
  private final CommentRepository commentRepository;
  private final ReviewRepository reviewRepository;

  // 주문 생성
  @Transactional
  public CreateResponseDto createOrder(Long userId, Long storeId, OrderRequestDto orderRequestDto) {
    // 유효한 사용자 확인
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

    // 유효한 가게 확인
    Store store = storeRepository.findById(storeId)
        .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND));

    // 가게 사장님이 본인 가게에 주문하기 금지
    if (store.getOwner().getId().equals(userId)) {
      throw new CustomException(ErrorCode.CANNOT_ORDER_OWN_STORE);
    }

    // 가게 영업 시간 확인
    LocalTime now = LocalTime.now();
    if (now.isBefore(store.getOpenTime()) || now.isAfter(store.getCloseTime())) {
      throw new CustomException(ErrorCode.STORE_CLOSED);
    }

    // 유효한 메뉴 확인
    Menu menu = menuRepository.findById(orderRequestDto.getMenuId())
        .orElseThrow(() -> new CustomException(ErrorCode.MENU_NOT_FOUND));

    // 메뉴가 해당 가게에 속해 있는지 확인
    if (!menu.getStore().getId().equals(storeId)) {
      throw new CustomException(ErrorCode.MENU_NOT_FOUND_IN_STORE);
    }

    // 수량 확인 및 기본값 설정
    Integer quantity = orderRequestDto.getQuantity();
    if (quantity == null) {
      quantity = 1;
    }

    // 총 가격 계산
    Long totalPrice = menu.getPrice() * quantity;
    // 최소 주문 금액 확인
    if (totalPrice < store.getMinimumPrice()) {
      throw new CustomException(ErrorCode.MIN_ORDER_PRICE_NOT_MET);
    }

    // 주문 생성
    Order order = Order.builder()
        .user(user)
        .store(store)
        .menu(menu)
        .quantity(quantity)
        .totalPrice(totalPrice)
        .status(OrderStatus.PENDING)
        .build();

    orderRepository.save(order);

    // 응답 DTO 생성
    return CreateResponseDto.builder()
        .orderId(order.getId())
        .quantity(order.getQuantity())
        .totalPrice(order.getTotalPrice())
        .orderStatus(order.getStatus())
        .createdAt(order.getCreatedAt())
        .build();
  }

  // 주문 상태 변경 (유저 전용)
  @Transactional
  public UpdatedResponseDto cancelOrder(Long userId, Long storeId, Long orderId,
      OrderStatusRequestDto orderStatusRequestDto) {
    // 유효한 가게 확인
    Store store = storeRepository.findById(storeId)
        .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND));

    // 주문 확인
    Order order = orderRepository.findById(orderId)
        .orElseThrow(() -> new CustomException(ErrorCode.ORDER_NOT_FOUND));

    // 주문이 해당 가게의 주문인지 확인
    if (!order.getStore().getId().equals(storeId)) {
      throw new CustomException(ErrorCode.ORDER_NOT_FOUND_IN_STORE);
    }

    // 주문이 PENDING 상태인지 확인
    if (!order.getStatus().equals(OrderStatus.PENDING)) {
      throw new CustomException(ErrorCode.INVALID_ORDER_STATUS_TRANSITION);
    }

    // 요청한 상태가 CANCELED 인지 확인
    OrderStatus orderStatus = OrderStatus.valueOf(orderStatusRequestDto.getOrderStatus());
    if (!order.getStatus().canChangeTo(orderStatus)) {
      throw new CustomException(ErrorCode.MISMATCH_STATUS_TEXT);
    }

    // 주문 상태 변경
    order.updateStatus(OrderStatus.CANCELED);

    // 응답 DTO 생성
    return UpdatedResponseDto.builder()
        .orderId(order.getId())
        .orderStatus(order.getStatus())
        .modifiedAt(order.getModifiedAt())
        .build();
  }

  // 주문 상태 변경 (사장님 전용)
  @Transactional
  public DeliveryStatusResponseDto updateOrderStatus(Long userId, Long storeId, Long orderId,
      OrderStatusRequestDto orderStatusRequestDto) {
    Order order = orderRepository.findByIdAndStoreId(orderId, storeId)
        .orElseThrow(() -> new CustomException(ErrorCode.ORDER_NOT_FOUND));

    if (!order.getStore().getOwner().getId().equals(userId)) {
      throw new CustomException(ErrorCode.ORDER_NOT_FOUND_IN_STORE);
    }
    // 주문 상태 변경 순서 확인
    OrderStatus orderStatus = OrderStatus.valueOf(orderStatusRequestDto.getOrderStatus());
    if (!order.getStatus().canChangeTo(orderStatus)) {
      throw new CustomException(ErrorCode.INVALID_ORDER_STATUS_TRANSITION);
    }

    // 주문 상태 변경
    order.updateStatus(orderStatus);

    // 응답 DTO 생성
    return DeliveryStatusResponseDto.builder()
        .id(order.getId())
        .orderStatus(order.getStatus())
        .modifiedAt(order.getModifiedAt())
        .build();
  }

  public OrderDetailResponseDto getOrder(Long id, User user) {
    Order order = orderRepository.findByIdAndUserId(id, user.getId())
        .orElseThrow(() -> new CustomException(ErrorCode.ORDER_NOT_FOUND));
    return new OrderDetailResponseDto(order);
  }

  // 주문 삭제
  @Transactional
  public void deleteOrder(Long userId, Long storeId, Long orderId) {
    // 주문 확인
    Order order = orderRepository.findById(orderId)
        .orElseThrow(() -> new CustomException(ErrorCode.ORDER_NOT_FOUND));

    // 주문이 해당 가게의 주문인지 확인
    if (!order.getStore().getId().equals(storeId)) {
      throw new CustomException(ErrorCode.ORDER_NOT_FOUND_IN_STORE);
    }

    // 주문 소유자인지 확인
    if (!order.getUser().getId().equals(userId)) {
      throw new CustomException(ErrorCode.UNAUTHORIZED_ACCESS);
    }

    // 주문 상태가 COMPLETED인지 확인
    if (!order.getStatus().equals(OrderStatus.COMPLETED)) {
      throw new CustomException(ErrorCode.INVALID_ORDER_STATUS_FOR_DELETION);
    }

    // 관련 리뷰 및 댓글 삭제 (하드 삭제)
    reviewRepository.findByOrderId(orderId).ifPresent(review -> {
      // 관련 댓글 모두 삭제
      List<Comment> comments = commentRepository.findAllByReviewId(review.getId());
      if (!comments.isEmpty()) {
        commentRepository.deleteAll(comments);
      }
      // 리뷰 삭제
      reviewRepository.delete(review);
    });

    // 주문 소프트 삭제 처리
    order.softDelete();
    orderRepository.save(order);
  }
}
