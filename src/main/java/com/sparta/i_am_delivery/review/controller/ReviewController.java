package com.sparta.i_am_delivery.review.controller;

import com.sparta.i_am_delivery.common.config.jwt.JwtHelper;
import com.sparta.i_am_delivery.domain.user.entity.User;
import com.sparta.i_am_delivery.review.dto.request.ReviewRequestDto;
import com.sparta.i_am_delivery.review.dto.response.ReviewCreationResponseDto;
import com.sparta.i_am_delivery.review.service.ReviewService;
import com.sparta.i_am_delivery.user.enums.UserType;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stores/{storeId}")
@RequiredArgsConstructor
public class ReviewController {

  private final JwtHelper jwtHelper;
  private final ReviewService reviewService;

  @GetMapping("/orders/{orderId}/reviews")
  public ResponseEntity<ReviewCreationResponseDto> createReview(
      @RequestHeader("Authorization") String authorizationHeader,
      @PathVariable @Valid Long storeId,
      @PathVariable @Valid Long orderId, @RequestBody @Valid ReviewRequestDto reviewRequestDto) {

    // User 객체를 임시로 생성하는 예시
    User jwtUser = new User();
    jwtUser.setId(1L);
    jwtUser.setEmail("text@example.com");
    jwtUser.setName("임시 사용자");
    jwtUser.setPassword("wlsdh741@");
    jwtUser.setType(UserType.USER);

/*    // JWT토큰에서 userId 추출
    String token = authorizationHeader.substring("Bearer ".length());
    Long userId = jwtHelper.getUserIdFromToken(token);*/

    // Review 생성 서비스 호출 시 userId 전달
    ReviewCreationResponseDto review = reviewService.createReview(jwtUser, storeId, orderId,
        reviewRequestDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(review);
  }
}
