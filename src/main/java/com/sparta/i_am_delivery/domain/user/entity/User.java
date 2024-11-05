package com.sparta.i_am_delivery.domain.user.entity;

import com.sparta.i_am_delivery.common.entity.TimeStamped;
import com.sparta.i_am_delivery.common.exception.CustomException;
import com.sparta.i_am_delivery.common.exception.ErrorCode;
import com.sparta.i_am_delivery.user.enums.UserType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor
public class User extends TimeStamped {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  @Enumerated(value = EnumType.STRING)
  private UserType type;

  @Builder
  public User(String email, String password, String name, UserType type) {
    this.email = email;
    this.password = password;
    this.name = name;
    this.type = type;
  }
public void updateName(String name) {
    this.name = name;
  }

  // 로그인 유효성 처리
  public void authenticate(String reqPassword, PasswordEncoder passwordEncoder) {
    if (this.getDeletedAt() != null) {
      throw new CustomException(ErrorCode.INACTIVE_MEMBER);
    }
    if (!passwordEncoder.matches(reqPassword, this.getPassword())) {
      throw new CustomException(ErrorCode.LOGIN_FAILED);
    }
  }

  // 로그인 유저와 현재 유저 일치 여부 확인
  public void validateUserIdentity(Long reqUserId) {
    if (!this.id.equals(reqUserId)) {
      throw new CustomException(ErrorCode.UNAUTHORIZED_ACCESS);
    }
  }

  public void updatePassword(
      Long reqUserId,
      String currentPassword,
      String changePassword,
      PasswordEncoder passwordEncoder) {
    validateUserIdentity(reqUserId);
    if (!passwordEncoder.matches(currentPassword, this.getPassword())) {
      throw new CustomException(ErrorCode.PASSWORD_MISMATCH);
    }
    if (passwordEncoder.matches(changePassword, this.getPassword())) {
      throw new CustomException(ErrorCode.INVALID_PASSWORD);
    }
    this.password = passwordEncoder.encode(changePassword);
  }
}
