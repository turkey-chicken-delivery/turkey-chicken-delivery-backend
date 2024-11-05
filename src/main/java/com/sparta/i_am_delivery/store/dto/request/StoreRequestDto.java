package com.sparta.i_am_delivery.store.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

@Getter
public class StoreRequestDto {

    @NotBlank(message = "이름을 입력해주세요.")
    private String name;
    @NotNull(message = "오픈시간을 입력해주세요.")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime openTime;
    @NotNull(message = "마감시간을 입력해주세요.")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime closeTime;
    @Min(value = 18000)
    private Long minimumPrice;
}
