package com.sparta.i_am_delivery.review.dto.response;

import com.sparta.i_am_delivery.domain.review.entity.Review;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewCreationResponseDto {

  private Long id;

  private String comment;

  private Long star;

  public ReviewCreationResponseDto(Review review) {
    this.id = review.getId();
    this.comment = review.getContent();
    this.star = review.getStar();
  }

}
