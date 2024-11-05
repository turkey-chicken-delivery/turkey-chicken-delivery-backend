package com.sparta.i_am_delivery.order.dto.orderstatus;

import com.sparta.i_am_delivery.order.enums.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderStatusRequestDto {

  @NotNull(message = "변경할 주문 상태를 입력해주세요.")
  private OrderStatus orderStatus;
}