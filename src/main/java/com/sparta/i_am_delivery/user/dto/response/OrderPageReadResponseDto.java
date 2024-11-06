package com.sparta.i_am_delivery.user.dto.response;

import com.sparta.i_am_delivery.menu.dto.info.MenuInfo;
import com.sparta.i_am_delivery.order.enums.OrderStatus;
import com.sparta.i_am_delivery.store.dto.info.StoreInfo;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderPageReadResponseDto {
  private Long orderId;
  private StoreInfo storeInfo;
  private MenuInfo menuInfo;
  private Long totalPrice;
  private Integer quantity;
  private OrderStatus status;
  private LocalDateTime modifiedAt;

  @Builder
  public OrderPageReadResponseDto(
      Long orderId,
      StoreInfo storeInfo,
      MenuInfo menuInfo,
      Long totalPrice,
      OrderStatus status,
      LocalDateTime modifiedAt,
      Integer quantity) {
    this.orderId = orderId;
    this.storeInfo = storeInfo;
    this.menuInfo = menuInfo;
    this.totalPrice = totalPrice;
    this.status = status;
    this.modifiedAt = modifiedAt;
    this.quantity = quantity;
  }
}
