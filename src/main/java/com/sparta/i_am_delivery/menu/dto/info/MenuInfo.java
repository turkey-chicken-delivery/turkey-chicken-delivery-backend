package com.sparta.i_am_delivery.menu.dto.info;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MenuInfo {
  private String name;
  private Long price;

  @Builder
  public MenuInfo(String name, Long price) {
    this.name = name;
    this.price = price;
  }
}
