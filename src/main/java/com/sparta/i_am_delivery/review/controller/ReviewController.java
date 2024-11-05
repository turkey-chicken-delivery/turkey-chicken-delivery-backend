package com.sparta.i_am_delivery.review.controller;

import com.sparta.i_am_delivery.common.annotation.AuthUser;
import com.sparta.i_am_delivery.domain.user.entity.User;
import com.sparta.i_am_delivery.review.dto.request.ReviewRequestDto;
import com.sparta.i_am_delivery.review.dto.response.ReviewCreationResponseDto;
import com.sparta.i_am_delivery.review.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stores/{storeId}")
@RequiredArgsConstructor
public class ReviewController {

  private final ReviewService reviewService;

  @GetMapping("/reviews")
  public ResponseEntity<ReviewCreationResponseDto> createReview(@AuthUser User user,
      @PathVariable @Valid Long storeId, @RequestBody @Valid ReviewRequestDto reviewRequestDto) {

    ReviewCreationResponseDto review = reviewService.createReview(user, storeId, reviewRequestDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(review);
  }
}
