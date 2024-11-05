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

  // 로그인 유저 유효성 처리
  public void authenticate(String reqPassword, PasswordEncoder passwordEncoder) {
    if (this.getDeletedAt() != null) {
      throw new CustomException(ErrorCode.INACTIVE_MEMBER);
    }
    if (!passwordEncoder.matches(reqPassword, this.getPassword())) {
      throw new CustomException(ErrorCode.LOGIN_FAILED);
    }
  }
}
