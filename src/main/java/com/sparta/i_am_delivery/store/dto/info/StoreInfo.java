package com.sparta.i_am_delivery.store.dto.info;

import java.time.LocalTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StoreInfo {
  private Long id;
  private String name;
  private LocalTime openTime;
  private LocalTime closeTime;
  private Long minimumPrice;

  @Builder
  public StoreInfo(
      Long id, String name, LocalTime openTime, LocalTime closeTime, Long minimumPrice) {
    this.id = id;
    this.name = name;
    this.openTime = openTime;
    this.closeTime = closeTime;
    this.minimumPrice = minimumPrice;
  }
}
