package com.sparta.i_am_delivery.order.dto.response;

import com.sparta.i_am_delivery.order.enums.OrderStatus;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeliveryStatusResponseDto {

  private Long id;
  private OrderStatus orderStatus;
  private LocalDateTime modifiedAt;

  @Builder
  public DeliveryStatusResponseDto(Long id, OrderStatus orderStatus, LocalDateTime modifiedAt) {
    this.id = id;
    this.orderStatus = orderStatus;
    this.modifiedAt = modifiedAt;
  }
}
