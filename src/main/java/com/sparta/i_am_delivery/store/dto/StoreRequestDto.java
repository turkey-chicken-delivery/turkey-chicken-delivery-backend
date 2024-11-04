package com.sparta.i_am_delivery.store.dto;

import lombok.Getter;

import java.time.LocalTime;

@Getter
public class StoreRequestDto {
    private String name;
    private LocalTime openTime;
    private LocalTime closeTime;
    private Long minimumPrice;
}
