package com.sparta.i_am_delivery.order.dto.response;

import com.sparta.i_am_delivery.domain.order.entity.Order;
import com.sparta.i_am_delivery.order.enums.OrderStatus;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderDetailResponseDto {

  private Long id;
  private String storeName;
  private String menuName;
  private int quantity;
  private Long totalPrice;
  private OrderStatus status;
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;

  public OrderDetailResponseDto(Order order) {
    this.id = order.getId();
    this.storeName = order.getStore().getName();
    this.menuName = order.getMenu().getName();
    this.quantity = order.getQuantity();
    this.totalPrice = order.getTotalPrice();
    this.status = order.getStatus();
    this.createdAt = order.getCreatedAt();
    this.modifiedAt = order.getModifiedAt();
  }
}
