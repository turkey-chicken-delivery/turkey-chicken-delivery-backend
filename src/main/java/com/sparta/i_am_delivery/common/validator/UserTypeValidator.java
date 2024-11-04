package com.sparta.i_am_delivery.common.validator;

import com.sparta.i_am_delivery.common.exception.CustomException;
import com.sparta.i_am_delivery.common.exception.ErrorCode;
import com.sparta.i_am_delivery.user.enums.UserType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserTypeValidator implements ConstraintValidator<UserTypeConstraint, String> {
  @Override
  public boolean isValid(String userType, ConstraintValidatorContext context) {
    try {
      UserType.valueOf(userType);
      return true;
    } catch (IllegalArgumentException e) {
      throw new CustomException(ErrorCode.INVALID_USER_TYPE);
    }
  }
}
