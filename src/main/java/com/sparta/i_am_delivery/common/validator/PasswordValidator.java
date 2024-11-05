package com.sparta.i_am_delivery.common.validator;

import com.sparta.i_am_delivery.common.annotation.ValidPasswordPatten;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Value;

public class PasswordValidator implements ConstraintValidator<ValidPasswordPatten, String> {

  @Value("${regex.password}")
  private String passwordRegex;

  @Override
  public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
    return value.matches(passwordRegex);
  }
}
