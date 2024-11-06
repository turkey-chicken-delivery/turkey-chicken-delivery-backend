package com.sparta.i_am_delivery.like.controller;

import com.sparta.i_am_delivery.common.annotation.AuthUser;
import com.sparta.i_am_delivery.domain.user.entity.User;
import com.sparta.i_am_delivery.like.dto.response.LikeAddResponseDto;
import com.sparta.i_am_delivery.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores/{storeId}/likes")
public class LikeController {
  private final LikeService likeService;

  @PostMapping
  public ResponseEntity<LikeAddResponseDto> addToStoreLike(
      @PathVariable("storeId") Long storeId, @AuthUser User user) {
    LikeAddResponseDto likeAddResponseDto = likeService.addToStoreLike(storeId, user);
    return ResponseEntity.status(HttpStatus.CREATED).body(likeAddResponseDto);
  }

  @DeleteMapping("/{id}")
  ResponseEntity<Void> removeToStoreLike(@PathVariable("id") Long id, @AuthUser User user) {
    likeService.removeToStoreLike(id, user);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
