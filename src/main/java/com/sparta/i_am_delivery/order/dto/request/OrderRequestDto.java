package com.sparta.i_am_delivery.order.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderRequestDto {

  @NotNull(message = "메뉴 ID를 입력해주세요.")
  private Long menuId;

  @Positive
  @NotNull(message = "수량은 1 이상의 양수여야 합니다.")
  private Integer quantity = 1; // 기본값 1
}
