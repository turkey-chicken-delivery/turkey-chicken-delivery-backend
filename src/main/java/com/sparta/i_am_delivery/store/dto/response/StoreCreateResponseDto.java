package com.sparta.i_am_delivery.store.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sparta.i_am_delivery.domain.store.entity.Store;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

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
  @NotNull(message = "최소 1자 이상 입력해주세요.")
  @Size(min = 0, max = 50, message = "최소 0자, 최대 50자까지 입력가능합니다.")
  private String ownerMessage;

  public StoreCreateResponseDto(Store store) {
    this.id = store.getId();
    this.name = store.getName();
    this.openTime = store.getOpenTime();
    this.closeTime = store.getCloseTime();
    this.minimumPrice = store.getMinimumPrice();
    this.createdAt = store.getCreatedAt();
    this.ownerMessage = store.getOwnerMessage();
  }
}
