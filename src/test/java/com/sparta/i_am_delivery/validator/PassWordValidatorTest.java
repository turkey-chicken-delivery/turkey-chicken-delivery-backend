package com.sparta.i_am_delivery.validator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.sparta.i_am_delivery.common.validator.PasswordValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

public class PassWordValidatorTest {
  private PasswordValidator passwordValidator;

  @BeforeEach
  public void setUp() {
    // given
    passwordValidator = new PasswordValidator();
    ReflectionTestUtils.setField(
        passwordValidator,
        "passwordRegex",
        "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()_+={}:;\"'<>,.?/\\[\\]\\\\-])[A-Za-z\\d~!@#$%^&*()+|=]+$");
  }

  @Test
  @DisplayName("비밀번호 정규식 성공")
  public void testValidatePassword() {
    // when & then
    assertTrue(passwordValidator.isValid("Password123!", null));
  }

  @Test
  @DisplayName("비밀번호 정규식 실패")
  public void testInvalidPassword() {
    // when & then
    assertFalse(passwordValidator.isValid("password", null));
    assertFalse(passwordValidator.isValid("12345!", null));
    assertFalse(passwordValidator.isValid("Password", null));
  }
}
