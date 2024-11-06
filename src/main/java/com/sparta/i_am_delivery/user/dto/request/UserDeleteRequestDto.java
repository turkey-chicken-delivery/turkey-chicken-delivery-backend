package com.sparta.i_am_delivery.user.dto.request;

import com.sparta.i_am_delivery.common.annotation.ValidPasswordPatten;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserDeleteRequestDto {
  @NotBlank(message = "비밀번호를 입력해주세요.")
  @Size(min = 8, max = 20, message = "최소 8글자 최대 20글자로 입력해주세요.")
  @ValidPasswordPatten
  private String password;
}