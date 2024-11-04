package com.sparta.i_am_delivery.user.dto.request;

import com.sparta.i_am_delivery.common.validator.UserTypeConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSignUpRequestDto {
  @Email
  @NotBlank(message = "이메일을 입력해주세요.")
  private String email;

  @NotBlank(message = "비밀번호를 입력해주세요.")
  @Size(min = 8, max = 20, message = "최소 8글자 최대 20글자로 입력해주세요.")
  @Pattern(
      regexp =
          "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()_+={}:;\"'<>,.?/\\[\\]\\\\-])[A-Za-z\\d~!@#$%^&*()+|=]+$",
      message = "영문+숫자+특수문자가 1글자 포함되어야합니다.")
  private String password;

  @NotBlank(message = "이름을 입력해주세요.")
  private String name;

  @UserTypeConstraint
  @NotBlank(message = "가입형태를 지정 해주세요.")
  private String type;

  @Builder
  public UserSignUpRequestDto(String email, String password, String name, String type) {
    this.email = email;
    this.password = password;
    this.name = name;
    this.type = type;
  }
}
