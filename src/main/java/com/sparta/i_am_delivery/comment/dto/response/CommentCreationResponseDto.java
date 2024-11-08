package com.sparta.i_am_delivery.comment.dto.response;

import com.sparta.i_am_delivery.domain.comment.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentCreationResponseDto {

  private Long id;

  private String content;

  public CommentCreationResponseDto(Comment comment) {
    this.id = comment.getId();
    this.content = comment.getContent();
  }
}
