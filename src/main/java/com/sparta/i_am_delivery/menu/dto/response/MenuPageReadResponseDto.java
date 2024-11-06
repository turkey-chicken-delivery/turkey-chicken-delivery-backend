package com.sparta.i_am_delivery.menu.dto.response;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MenuPageReadResponseDto {
  private Long id;
  private String name;
  private Long price;
  private LocalDateTime createdAt;

  @Builder
  public MenuPageReadResponseDto(Long id, String name, Long price, LocalDateTime createdAt) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.createdAt = createdAt;
  }
}
