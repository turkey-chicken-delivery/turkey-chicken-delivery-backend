package com.sparta.i_am_delivery.order.controller;

import com.sparta.i_am_delivery.common.annotation.AuthUser;
import com.sparta.i_am_delivery.domain.user.entity.User;
import com.sparta.i_am_delivery.order.dto.request.OrderRequestDto;
import com.sparta.i_am_delivery.order.dto.request.OrderStatusRequestDto;
import com.sparta.i_am_delivery.order.dto.response.OrderResponseDto;
import com.sparta.i_am_delivery.order.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;

  // 주문 생성
  @PostMapping("/{storeId}/orders")
  public ResponseEntity<OrderResponseDto> createOrder(
      @PathVariable Long storeId,
      @Valid @RequestBody OrderRequestDto orderRequestDto,
      @AuthUser User user
  ) {

    // 로그인된 userId를 가져오기
    Long userId = user.getId();

    OrderResponseDto orderResponseDto = orderService.createOrder(userId, storeId, orderRequestDto);

    return ResponseEntity.status(HttpStatus.CREATED).body(orderResponseDto);
  }

  // 주문 상태 변경 (유저 전용, PENDING 상태일 때만 CANCELED로 변경 가능)
  @PutMapping("/{storeId}/orders/{orderId}")
  public ResponseEntity<OrderResponseDto> cancelOrder(
      @PathVariable Long storeId,
      @PathVariable Long orderId,
      @Valid @RequestBody OrderStatusRequestDto orderStatusRequestDto,
      @AuthUser User user) {

    // 서비스 메서드를 호출하여 주문을 취소하고 응답 생성
    OrderResponseDto orderResponseDto = orderService.cancelOrder(user.getId(), storeId, orderId,
        orderStatusRequestDto);
    return ResponseEntity.ok(orderResponseDto);
  }


  // 주문 상태 변경 엔드포인트 (사장님 전용)
  @PatchMapping("/{storeId}/orders/{orderId}/owner")
  public ResponseEntity<OrderResponseDto> updateOrderStatus(
      @PathVariable Long storeId,
      @PathVariable Long orderId,
      @Valid @RequestBody OrderStatusRequestDto orderStatusRequestDto,
      HttpServletRequest request) {

    // 사장님 인증 및 권한 체크 로직 필요 (예시로 ownerId를 가져오는 것으로 가정)
    Long ownerId = (Long) request.getAttribute("ownerId");

    OrderResponseDto orderResponseDto = orderService.updateOrderStatus(ownerId, storeId, orderId,
        orderStatusRequestDto);

    return ResponseEntity.ok(orderResponseDto);
  }
}
