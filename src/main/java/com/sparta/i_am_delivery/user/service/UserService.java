package com.sparta.i_am_delivery.user.service;

import com.sparta.i_am_delivery.common.config.jwt.JwtHelper;
import com.sparta.i_am_delivery.common.config.security.PasswordEncoder;
import com.sparta.i_am_delivery.common.exception.CustomException;
import com.sparta.i_am_delivery.common.exception.ErrorCode;
import com.sparta.i_am_delivery.user.dto.UserSingUpRequestDto;
import com.sparta.i_am_delivery.user.dto.UserSingUpResponseDto;
import com.sparta.i_am_delivery.user.entity.User;
import com.sparta.i_am_delivery.user.enums.UserType;
import com.sparta.i_am_delivery.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Transactional
  public UserSingUpResponseDto singUp(UserSingUpRequestDto userSingupRequestDto) {
    this.isDuplicateUser(userSingupRequestDto.getEmail(), userSingupRequestDto.getName());
    User user =
        User.builder()
            .email(userSingupRequestDto.getEmail())
            .password(passwordEncoder.encode(userSingupRequestDto.getPassword()))
            .name(userSingupRequestDto.getName())
            .type(UserType.valueOf(userSingupRequestDto.getType()))
            .build();
    userRepository.save(user);
    return UserSingUpResponseDto.builder()
        .id(user.getId())
        .email(user.getEmail())
        .name(user.getName())
        .type(user.getType())
        .build();
  }

  private void isDuplicateUser(String email, String name) {
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
