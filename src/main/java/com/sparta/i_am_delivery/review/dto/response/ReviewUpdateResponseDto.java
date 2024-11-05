package com.sparta.i_am_delivery.review.dto.response;

import com.sparta.i_am_delivery.domain.review.entity.Review;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewUpdateResponseDto {

  private Long id;

  private String comment;

  private Long star;

  public ReviewUpdateResponseDto(Review review) {
    this.id = review.getId();
    this.comment = review.getComment();
    this.star = review.getStar();
  }

}
