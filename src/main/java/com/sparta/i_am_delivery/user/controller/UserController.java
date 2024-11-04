package com.sparta.i_am_delivery.user.controller;

import com.sparta.i_am_delivery.common.config.jwt.JwtHelper;
import com.sparta.i_am_delivery.user.dto.request.UserSignUpRequestDto;
import com.sparta.i_am_delivery.user.dto.response.UserSignUpResponseDto;
import com.sparta.i_am_delivery.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
  private final UserService userService;
  private final JwtHelper jwtHelper;

  @PostMapping
  public ResponseEntity<UserSignUpResponseDto> signUp(
          @Valid @RequestBody UserSignUpRequestDto userSingupRequestDto, HttpServletResponse response) {
    UserSignUpResponseDto userSingupResponseDto = userService.signUp(userSingupRequestDto);
    String token =
        jwtHelper.generateAccessToken(
            userSingupResponseDto.getId(), userSingupResponseDto.getType());
    jwtHelper.addToken(response, token);
    return ResponseEntity.status(HttpStatus.CREATED).body(userSingupResponseDto);
  }
}
