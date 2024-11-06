package com.sparta.i_am_delivery.comment.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CommentRequestDto {

  @NotNull
  private String comment;
}
