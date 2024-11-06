package com.sparta.i_am_delivery.review.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ReviewRequestDto {

  @NotNull
  private String comment;

  @NotBlank
  private Long star;
}