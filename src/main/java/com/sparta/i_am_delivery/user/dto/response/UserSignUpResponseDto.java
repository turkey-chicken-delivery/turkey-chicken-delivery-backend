package com.sparta.i_am_delivery.user.dto.response;

import com.sparta.i_am_delivery.user.enums.UserType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSignUpResponseDto {
  private Long id;
  private String email;
  private String name;
  private UserType type;

  @Builder
  public UserSignUpResponseDto(Long id, String email, String name, UserType type) {
    this.id = id;
    this.email = email;
    this.name = name;
    this.type = type;
  }
}
