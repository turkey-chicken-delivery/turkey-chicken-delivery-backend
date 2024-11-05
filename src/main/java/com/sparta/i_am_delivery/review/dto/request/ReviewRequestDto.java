package com.sparta.i_am_delivery.review.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewRequestDto {

  private Long orderId;

  @NotBlank(message = "리뷰 내용은 필수입니다.")
  private String comment;

  private Long star;
}
