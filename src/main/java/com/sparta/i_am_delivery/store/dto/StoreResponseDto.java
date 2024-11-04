package com.sparta.i_am_delivery.store.dto;

import com.sparta.i_am_delivery.domain.store.entity.Store;
import lombok.Getter;

import java.time.LocalTime;

@Getter
public class StoreResponseDto {
    private Long id;
    private String name;
    private LocalTime openTime;
    private LocalTime closeTime;
    private Long minimumPrice;

    public StoreResponseDto(Store saveStore) {
        this.id = saveStore.getId();
        this.name = saveStore.getName();
        this.openTime = saveStore.getOpenTime();
        this.closeTime = saveStore.getCloseTime();
        this.minimumPrice = saveStore.getMinimumPrice();
    }
}
