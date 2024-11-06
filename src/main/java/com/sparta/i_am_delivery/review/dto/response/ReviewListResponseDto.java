package com.sparta.i_am_delivery.review.dto.response;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewListResponseDto {

  private List<ReviewResponseDto> contents;
  private int size;
  private int page;
  private int totalPages;

  public ReviewListResponseDto(List<ReviewResponseDto> reviews, int size, int page,
      int totalPages) {

    this.contents = reviews;
    this.size = size;
    this.page = page;
    this.totalPages = totalPages;
  }
}