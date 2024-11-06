package com.sparta.i_am_delivery.order.dto.response;

import com.sparta.i_am_delivery.order.enums.OrderStatus;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdatedResponseDto {

  private Long orderId;
  private Long storeId;
  private Long userId;
  private Long menuId;
  private Integer quantity;
  private Long totalPrice;
  private OrderStatus orderStatus;
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;

  @Builder
  public UpdatedResponseDto(Long orderId, Long storeId, Long userId, Long menuId, Integer quantity,
      Long totalPrice, OrderStatus orderStatus, LocalDateTime createdAt, LocalDateTime modifiedAt) {
    this.orderId = orderId;
    this.storeId = storeId;
    this.userId = userId;
    this.menuId = menuId;
    this.quantity = quantity;
    this.totalPrice = totalPrice;
    this.orderStatus = orderStatus;
    this.createdAt = createdAt;
    this.modifiedAt = modifiedAt;
  }
}
