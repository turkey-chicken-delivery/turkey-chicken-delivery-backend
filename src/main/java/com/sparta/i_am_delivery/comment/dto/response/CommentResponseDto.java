package com.sparta.i_am_delivery.comment.dto.response;

import com.sparta.i_am_delivery.domain.comment.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentResponseDto {

  private String content;

  public CommentResponseDto(Comment comment) {
    this.content = comment.getContent();
  }
}
