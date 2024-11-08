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
  private OrderStatus orderStatus;
  private LocalDateTime modifiedAt;

  @Builder
  public UpdatedResponseDto(Long orderId, OrderStatus orderStatus, LocalDateTime modifiedAt) {
    this.orderId = orderId;
    this.orderStatus = orderStatus;
    this.modifiedAt = modifiedAt;
  }
}
