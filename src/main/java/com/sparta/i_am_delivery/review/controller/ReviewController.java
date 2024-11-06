package com.sparta.i_am_delivery.review.controller;

import com.sparta.i_am_delivery.common.annotation.AuthUser;
import com.sparta.i_am_delivery.domain.user.entity.User;
import com.sparta.i_am_delivery.review.dto.request.ReviewRequestDto;
import com.sparta.i_am_delivery.review.dto.response.ReviewCreationResponseDto;
import com.sparta.i_am_delivery.review.dto.response.ReviewListResponseDto;
import com.sparta.i_am_delivery.review.dto.response.ReviewUpdateResponseDto;
import com.sparta.i_am_delivery.review.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stores/{storeId}")
@RequiredArgsConstructor
public class ReviewController {

  private final ReviewService reviewService;

  @PostMapping("/orders/{orderId}/reviews")
  public ResponseEntity<ReviewCreationResponseDto> createReview(@AuthUser User user,
      @PathVariable @Valid Long storeId, @RequestBody @Valid ReviewRequestDto reviewRequestDto,
      @PathVariable @Valid Long orderId) {

    ReviewCreationResponseDto review = reviewService.createReview(user, storeId, orderId,
        reviewRequestDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(review);
  }

  @PutMapping("/reviews/{id}")
  public ResponseEntity<ReviewUpdateResponseDto> updateReview(@AuthUser User user,
      @PathVariable Long id, @RequestBody @Valid ReviewRequestDto reviewRequestDto) {

    ReviewUpdateResponseDto review = reviewService.updateReview(user, id, reviewRequestDto);
    return ResponseEntity.status(HttpStatus.OK).body(review);
  }

  @GetMapping("/review")
  public ResponseEntity<ReviewListResponseDto> getAllReview(@PathVariable Long storeId,
      @RequestParam(defaultValue = "1", required = false) int page) {
    ReviewListResponseDto reviews = reviewService.getAllReview(storeId, page);
    return ResponseEntity.status(HttpStatus.OK).body(reviews);
  }
}