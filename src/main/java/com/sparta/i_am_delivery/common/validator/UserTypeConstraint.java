package com.sparta.i_am_delivery.common.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UserTypeValidator.class) // 검증 로직을 수행할 클래스 지정
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER }) // 적용 가능한 곳
@Retention(RetentionPolicy.RUNTIME)
public @interface UserTypeConstraint {
    String message() default "Invalid user type";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
