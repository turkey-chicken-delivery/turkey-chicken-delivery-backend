package com.sparta.i_am_delivery.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateNameRequestDto {
  @NotBlank(message = "변경 할 이름을 입력해주세요.")
  private String name;
}
