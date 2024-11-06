package com.sparta.i_am_delivery.like.dto.response;

import com.sparta.i_am_delivery.store.dto.info.StoreInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LikeAddResponseDto {
  private Long id;
  private StoreInfo storeInfo;

  @Builder
  public LikeAddResponseDto(Long id, StoreInfo storeInfo) {
    this.id = id;
    this.storeInfo = storeInfo;
  }
}
