package com.sparta.i_am_delivery.like.controller;

import com.sparta.i_am_delivery.common.annotation.AuthUser;
import com.sparta.i_am_delivery.domain.user.entity.User;
import com.sparta.i_am_delivery.like.dto.response.LikeAddResponseDto;
import com.sparta.i_am_delivery.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores/{storeId}/likes")
public class likeController {
  private final LikeService likeService;

  @PostMapping
  public ResponseEntity<LikeAddResponseDto> addToStoreLike(
      @PathVariable("storeId") Long storeId, @AuthUser User user) {
    LikeAddResponseDto likeAddResponseDto = likeService.addToStoreLike(storeId, user);
    return ResponseEntity.status(HttpStatus.CREATED).body(likeAddResponseDto);
  }
}
