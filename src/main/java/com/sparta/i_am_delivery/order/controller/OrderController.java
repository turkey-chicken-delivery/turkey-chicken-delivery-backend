package com.sparta.i_am_delivery.order.controller;

import com.sparta.i_am_delivery.common.annotation.AuthUser;
import com.sparta.i_am_delivery.domain.user.entity.User;
import com.sparta.i_am_delivery.order.dto.request.OrderRequestDto;
import com.sparta.i_am_delivery.order.dto.request.OrderStatusRequestDto;
import com.sparta.i_am_delivery.order.dto.response.CreateResponseDto;
import com.sparta.i_am_delivery.order.dto.response.UpdatedResponseDto;
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
  public ResponseEntity<CreateResponseDto> createOrder(
      @PathVariable Long storeId,
      @Valid @RequestBody OrderRequestDto orderRequestDto,
      @AuthUser User user
  ) {
    Long userId = user.getId();
    CreateResponseDto responseDto = orderService.createOrder(userId, storeId, orderRequestDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
  }

  // 주문 상태 변경 (유저 전용, PENDING 상태일 때만 CANCELED로 변경 가능)
  @PutMapping("/{storeId}/orders/{orderId}")
  public ResponseEntity<UpdatedResponseDto> cancelOrder(
      @PathVariable Long storeId,
      @PathVariable Long orderId,
      @Valid @RequestBody OrderStatusRequestDto orderStatusRequestDto,
      @AuthUser User user
  ) {
    UpdatedResponseDto responseDto = orderService.cancelOrder(user.getId(), storeId, orderId,
        orderStatusRequestDto);
    return ResponseEntity.ok(responseDto);
  }

  // 주문 상태 변경 (사장님 전용)
  @PatchMapping("/{storeId}/orders/{orderId}/owner")
  public ResponseEntity<UpdatedResponseDto> updateOrderStatus(
      @PathVariable Long storeId,
      @PathVariable Long orderId,
      @Valid @RequestBody OrderStatusRequestDto orderStatusRequestDto,
      HttpServletRequest request
  ) {
    Long ownerId = (Long) request.getAttribute("ownerId");
    UpdatedResponseDto responseDto = orderService.updateOrderStatus(ownerId, storeId, orderId,
        orderStatusRequestDto);
    return ResponseEntity.ok(responseDto);
  }
}
