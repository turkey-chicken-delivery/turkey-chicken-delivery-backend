package com.sparta.i_am_delivery.user.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.sparta.i_am_delivery.common.config.security.PasswordEncoder;
import com.sparta.i_am_delivery.common.exception.CustomException;
import com.sparta.i_am_delivery.common.exception.ErrorCode;
import com.sparta.i_am_delivery.user.dto.request.UserSignUpRequestDto;
import com.sparta.i_am_delivery.user.dto.response.UserSignUpResponseDto;
import com.sparta.i_am_delivery.domain.user.entity.User;
import com.sparta.i_am_delivery.user.enums.UserType;
import com.sparta.i_am_delivery.domain.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
  @Mock UserRepository userRepository;
  @Mock PasswordEncoder passwordEncoder;
  @InjectMocks UserService userService;

  @Test
  @DisplayName("회원가입 성공")
  void signUpSuccessTest() {
    // given
    UserSignUpRequestDto req =
        UserSignUpRequestDto.builder()
            .email("aaa@gmail.com")
            .password("q1!w2@e3#r4$")
            .name("김사장")
            .type("OWNER")
            .build();
    User user =
        User.builder()
            .email(req.getEmail())
            .password("encodedPassword")
            .name(req.getName())
            .type(UserType.valueOf(req.getType()))
            .build();
    when(userRepository.save(any(User.class))).thenReturn(user);
    when(passwordEncoder.encode(req.getPassword())).thenReturn("encodedPassword");
    // when
    UserSignUpResponseDto responseDto = userService.signUp(req);
    // then
    assertNotNull(responseDto);
    assertEquals(user.getEmail(), responseDto.getEmail());
    assertEquals(user.getName(), responseDto.getName());
    verify(userRepository, times(1)).save(any(User.class));
  }

  @Test
  @DisplayName("중복 이메일 회원가입")
  public void testSignUpWithDuplicateEmail() {
    // Given
    String duplicateEmail = "test@example.com";
    UserSignUpRequestDto req =
            UserSignUpRequestDto.builder()
                    .email(duplicateEmail)
                    .password("q1!w2@e3#r4$")
                    .name("김사장")
                    .type("OWNER")
                    .build();
    User existingUser = User.builder().email(duplicateEmail).name("differentName").build();

    when(userRepository.findByEmailOrName(any(String.class), any(String.class)))
            .thenReturn(Optional.of(existingUser));

    // When & Then
    CustomException exception = assertThrows(CustomException.class, () -> {
      userService.signUp(req);
    });

    assertEquals(ErrorCode.EMAIL_DUPLICATED, exception.getErrorCode());
  }

  @Test
  @DisplayName("중복 이름 회원가입")
  public void testSignUpWithDuplicateName() {
    // Given
    String duplicateName = "duplicateName";
    UserSignUpRequestDto req =
            UserSignUpRequestDto.builder()
                    .email("unique@example.com")
                    .password("q1!w2@e3#r4$")
                    .name(duplicateName)
                    .type("OWNER")
                    .build();
    User existingUser = User.builder().email("different@example.com").name(duplicateName).build();

    when(userRepository.findByEmailOrName(any(String.class), any(String.class)))
            .thenReturn(Optional.of(existingUser));

    // When & Then
    CustomException exception = assertThrows(CustomException.class, () -> {
      userService.signUp(req);
    });

    assertEquals(ErrorCode.USERNAME_DUPLICATED, exception.getErrorCode());
  }
}
