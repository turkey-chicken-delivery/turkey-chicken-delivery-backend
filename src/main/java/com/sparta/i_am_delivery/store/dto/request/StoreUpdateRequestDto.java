package com.sparta.i_am_delivery.store.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalTime;

@Getter
public class StoreUpdateRequestDto {

  @NotNull(message = "오픈시간을 입력해주세요.")
  @JsonFormat(pattern = "HH:mm:ss")
  private LocalTime openTime;
  @NotNull(message = "마감시간을 입력해주세요.")
  @JsonFormat(pattern = "HH:mm:ss")
  private LocalTime closeTime;
  @Min(value = 18000, message = "최소 주문금액은 18000원 이상입니다.")
  private Long minimumPrice;


}
