package com.sparta.i_am_delivery.store.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sparta.i_am_delivery.domain.store.entity.Store;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StoreUpdateResponseDto {
  private Long id;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
  private LocalTime openTime;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
  private LocalTime closeTime;

  private Long minimumPrice;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime createdAt;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime modifiedAt;

  @NotNull(message = "최소 1자 이상 입력해주세요.")
  @Size(min = 0, max = 50, message = "최소 0자, 최대 50자까지 입력가능합니다.")
  private String ownerMessage;

  public StoreUpdateResponseDto(Store updateStore) {
    this.id = updateStore.getId();
    this.openTime = updateStore.getOpenTime();
    this.closeTime = updateStore.getCloseTime();
    this.minimumPrice = updateStore.getMinimumPrice();
    this.createdAt = updateStore.getCreatedAt();
    this.modifiedAt = updateStore.getModifiedAt();
    this.ownerMessage = updateStore.getOwnerMessage();
  }
}
