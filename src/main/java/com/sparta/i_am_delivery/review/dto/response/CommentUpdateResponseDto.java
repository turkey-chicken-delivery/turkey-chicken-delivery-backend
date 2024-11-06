package com.sparta.i_am_delivery.review.dto.response;


import com.sparta.i_am_delivery.domain.comment.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentUpdateResponseDto {

  private Long id;
  private String content;

  public CommentUpdateResponseDto(Comment comment) {
    this.id = comment.getId();
    this.content = comment.getContent();
  }
}
