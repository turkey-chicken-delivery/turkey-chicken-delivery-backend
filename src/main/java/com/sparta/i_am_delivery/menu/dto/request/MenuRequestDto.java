package com.sparta.i_am_delivery.menu.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class MenuRequestDto {
  @NotBlank(message = "메뉴 이름을 입력해주세요.")
  private String name;

  @Positive(message = "메뉴 가격을 양수로 입력해주세요.")
  @NotNull(message = "메뉴 가격을 입력해주세요.")
  private Long price;
}
