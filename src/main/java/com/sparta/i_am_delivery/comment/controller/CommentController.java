package com.sparta.i_am_delivery.comment.controller;


import com.sparta.i_am_delivery.comment.dto.request.CommentRequestDto;
import com.sparta.i_am_delivery.comment.dto.response.CommentCreationResponseDto;
import com.sparta.i_am_delivery.comment.service.CommentService;
import com.sparta.i_am_delivery.common.annotation.AuthUser;
import com.sparta.i_am_delivery.domain.comment.repository.CommentRepository;
import com.sparta.i_am_delivery.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stores/{storeId}")
@RequiredArgsConstructor
public class CommentController {

  private final CommentService commentService;
  private final CommentRepository commentRepository;

  @PostMapping("/reviews/{reviewId}/comments")
  public ResponseEntity<CommentCreationResponseDto> createComment(@PathVariable Long storeId,
      @PathVariable Long reviewId, @AuthUser User user,
      @RequestBody CommentRequestDto commentRequestDto) {

    CommentCreationResponseDto comment = commentService.createComment(storeId, reviewId, user,
        commentRequestDto);

    return ResponseEntity.status(HttpStatus.CREATED).body(comment);
  }
}
