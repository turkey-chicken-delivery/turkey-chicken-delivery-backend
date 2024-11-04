package com.sparta.i_am_delivery.user.controller;

import com.sparta.i_am_delivery.common.config.jwt.JwtHelper;
import com.sparta.i_am_delivery.user.dto.request.UserLogInRequestDto;
import com.sparta.i_am_delivery.user.dto.request.UserSignUpRequestDto;
import com.sparta.i_am_delivery.user.dto.response.UserSignUpResponseDto;
import com.sparta.i_am_delivery.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
  private final UserService userService;
  private final JwtHelper jwtHelper;

  @PostMapping()
  public ResponseEntity<UserSignUpResponseDto> signUp(
      @Valid @RequestBody UserSignUpRequestDto userSingUpRequestDto, HttpServletResponse response) {
    UserSignUpResponseDto userSingUpResponseDto = userService.signUp(userSingUpRequestDto);
    // 회원가입시 자동로그인
    String token =
        userService.logIn(userSingUpResponseDto.getEmail(), userSingUpRequestDto.getPassword());
    jwtHelper.addTokenToCookie(response, token);
    return ResponseEntity.status(HttpStatus.CREATED).body(userSingUpResponseDto);
  }

  @PostMapping("/login")
  public ResponseEntity<Void> logIn(
      @Valid @RequestBody UserLogInRequestDto userLogInRequestDto, HttpServletResponse response) {
    String token =
        userService.logIn(userLogInRequestDto.getEmail(), userLogInRequestDto.getPassword());
    jwtHelper.addTokenToCookie(response, token);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
