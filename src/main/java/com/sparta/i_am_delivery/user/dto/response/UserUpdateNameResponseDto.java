package com.sparta.i_am_delivery.user.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateNameResponseDto {
  private Long id;
  private String name;

  @Builder
  public UserUpdateNameResponseDto(Long id, String name) {
    this.id = id;
    this.name = name;
  }
}
