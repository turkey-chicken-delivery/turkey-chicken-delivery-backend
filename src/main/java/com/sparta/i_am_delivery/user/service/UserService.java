package com.sparta.i_am_delivery.user.service;

import com.sparta.i_am_delivery.common.config.jwt.JwtHelper;
import com.sparta.i_am_delivery.common.exception.CustomException;
import com.sparta.i_am_delivery.common.exception.ErrorCode;
import com.sparta.i_am_delivery.domain.user.entity.User;
import com.sparta.i_am_delivery.domain.user.repository.UserRepository;
import com.sparta.i_am_delivery.user.dto.request.UserSignUpRequestDto;
import com.sparta.i_am_delivery.user.dto.response.UserSignUpResponseDto;
import com.sparta.i_am_delivery.user.enums.UserType;
import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtHelper jwtHelper;

  @Transactional
  public UserSignUpResponseDto signUp(UserSignUpRequestDto userSingUpRequestDto) {
    this.isValidateUserUniqueness(userSingUpRequestDto.getEmail(), userSingUpRequestDto.getName());
    User user =
        User.builder()
            .email(userSingUpRequestDto.getEmail())
            .password(passwordEncoder.encode(userSingUpRequestDto.getPassword()))
            .name(userSingUpRequestDto.getName())
            .type(UserType.valueOf(userSingUpRequestDto.getType()))
            .build();
    userRepository.save(user);
    return UserSignUpResponseDto.builder()
        .id(user.getId())
        .email(user.getEmail())
        .name(user.getName())
        .type(user.getType())
        .build();
  }

  public String logIn(String email, String password) {
    User user =
        userRepository
            .findByEmail(email)
            .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    user.authenticate(password, passwordEncoder);
    return jwtHelper.generateAccessToken(user.getId(), user.getEmail(), user.getType());
  }

  private void isValidateUserUniqueness(String email, String name) {
    Optional<User> foundUser = userRepository.findByEmailOrName(email, name);
    if (foundUser.isPresent()) {
      User existingUser = foundUser.get();
      if (existingUser.getEmail().equals(email)) {
        throw new CustomException(ErrorCode.EMAIL_DUPLICATED);
      }
      if (existingUser.getName().equals(name)) {
        throw new CustomException(ErrorCode.USERNAME_DUPLICATED);
      }
    }
  }
}
