package com.sparta.i_am_delivery.user.controller;

import com.sparta.i_am_delivery.common.annotation.AuthUser;
import com.sparta.i_am_delivery.common.config.jwt.JwtHelper;
import com.sparta.i_am_delivery.domain.user.entity.User;
import com.sparta.i_am_delivery.user.dto.request.*;
import com.sparta.i_am_delivery.user.dto.response.UserSignUpResponseDto;
import com.sparta.i_am_delivery.user.dto.response.UserUpdateNameResponseDto;
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

  @PutMapping("/{id}/name")
  public ResponseEntity<UserUpdateNameResponseDto> updateUserName(
      @AuthUser User user,
      @PathVariable Long id,
      @Valid @RequestBody UserUpdateNameRequestDto userUpdateNameRequestDto) {
    UserUpdateNameResponseDto userUpdateNameResponseDto =
        userService.updateName(user, id, userUpdateNameRequestDto.getName());
    return ResponseEntity.status(HttpStatus.OK).body(userUpdateNameResponseDto);
  }

  @PutMapping("/{id}/password")
  public ResponseEntity<Void> updateUserPassword(
      @AuthUser User user,
      @PathVariable Long id,
      @Valid @RequestBody UserUpdatePasswordRequestDto userUpdatePasswordRequestDto) {
    userService.updatePassword(user, id, userUpdatePasswordRequestDto);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(
      @AuthUser User user,
      @PathVariable Long id,
      @Valid @RequestBody UserDeleteRequestDto userDeleteRequestDto) {
    userService.deleteUser(user,id,userDeleteRequestDto);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
