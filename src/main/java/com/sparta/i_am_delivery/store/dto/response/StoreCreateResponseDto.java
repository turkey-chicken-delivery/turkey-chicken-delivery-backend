package com.sparta.i_am_delivery.store.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sparta.i_am_delivery.domain.store.entity.Store;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.Getter;

@Getter
@NoArgsConstructor
public class StoreCreateResponseDto {
  private Long id;
  private String name;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
  private LocalTime openTime;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
  private LocalTime closeTime;
  private Long minimumPrice;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime createdAt;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
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
