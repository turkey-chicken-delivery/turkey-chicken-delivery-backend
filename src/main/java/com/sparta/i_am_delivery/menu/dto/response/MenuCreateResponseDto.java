package com.sparta.i_am_delivery.menu.dto.response;

import com.sparta.i_am_delivery.domain.menu.entity.Menu;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MenuCreateResponseDto {

  private Long id;
  private String name;
  private Long price;
  private LocalDateTime createdAt;

  public MenuCreateResponseDto(Menu menu) {
    this.id = menu.getId();
    this.name = menu.getName();
    this.price = menu.getPrice();
    this.createdAt = menu.getCreatedAt();
  }
}
