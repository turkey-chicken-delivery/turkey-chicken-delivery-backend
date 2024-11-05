package com.sparta.i_am_delivery.store.dto.response;

import com.sparta.i_am_delivery.domain.store.entity.Store;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
public class StoreCreateResponseDto {
  private Long id;
  private String name;
  private LocalTime openTime;
  private LocalTime closeTime;
  private Long minimumPrice;
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;

  public StoreCreateResponseDto(Store saveStore) {
    this.id = saveStore.getId();
    this.name = saveStore.getName();
    this.openTime = saveStore.getOpenTime();
    this.closeTime = saveStore.getCloseTime();
    this.minimumPrice = saveStore.getMinimumPrice();
    this.createdAt = saveStore.getCreatedAt();
    this.modifiedAt = saveStore.getModifiedAt();
  }
}
