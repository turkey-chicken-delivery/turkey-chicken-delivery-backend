package com.sparta.i_am_delivery.review.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ReviewRequestDto {

  @NotBlank(message = "내용을 입력해주세요")
  private String content;

  @NotNull(message = "별점을 입력해주세요")
  private Long star;
}
