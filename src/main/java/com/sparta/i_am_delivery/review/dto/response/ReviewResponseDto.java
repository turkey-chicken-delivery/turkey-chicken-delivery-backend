package com.sparta.i_am_delivery.review.dto.response;

import com.sparta.i_am_delivery.comment.dto.response.CommentResponseDto;
import com.sparta.i_am_delivery.domain.review.entity.Review;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewResponseDto {

  private String content;
  private Long star;
  private CommentResponseDto reply;

  public ReviewResponseDto(Review review, CommentResponseDto reply) {
    this.content = review.getContent();
    this.star = review.getStar();
    this.reply = reply;
  }
}
