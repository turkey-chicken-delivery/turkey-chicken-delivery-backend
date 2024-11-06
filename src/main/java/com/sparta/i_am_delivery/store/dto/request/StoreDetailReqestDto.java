package com.sparta.i_am_delivery.store.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@NoArgsConstructor
public class StoreDetailReqestDto {
  @NotBlank(message = "이름을 입력해주세요.")
  private String name;
  @NotNull(message = "오픈시간을 입력해주세요.")
  @JsonFormat(pattern = "HH:mm:ss")
  private LocalTime openTime;
  @JsonFormat(pattern = "HH:mm:ss")
  @NotNull(message = "마감시간을 입력해주세요.")
  private LocalTime closeTime;
  private Long minimumPrice;
  @NotNull(message = "최소 1자 이상 입력해주세요.")
  @Size(min = 0, max = 50, message = "최소 0자, 최대 50자까지 입력가능합니다.")
  private String ownerMessage;


}
