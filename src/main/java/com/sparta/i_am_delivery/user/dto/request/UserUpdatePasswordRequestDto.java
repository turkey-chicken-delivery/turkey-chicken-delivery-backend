package com.sparta.i_am_delivery.user.dto.request;

import com.sparta.i_am_delivery.common.annotation.ValidPasswordPatten;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdatePasswordRequestDto {
  @NotBlank(message = "현재 사용중인 비밀번호를 입력해주세요.")
  @Size(min = 8, max = 20, message = "최소 8글자 최대 20글자로 입력해주세요.")
  @ValidPasswordPatten
  private String currentPassword;

  @NotBlank(message = "변경 할 비밀번호를 입력해주세요.")
  @Size(min = 8, max = 20, message = "최소 8글자 최대 20글자로 입력해주세요.")
  @ValidPasswordPatten
  private String changePassword;
}
