package com.sparta.i_am_delivery.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j(topic = "GlobalExceptionHandler : ")
@RestControllerAdvice
public class GlobalExceptionHandler {

  // CustomException 처리
  @ExceptionHandler(CustomException.class)
  public ResponseEntity<ErrorResponse> handleCustomException(CustomException ex) {
    ErrorCode errorCode = ex.getErrorCode();
    ErrorResponse response = new ErrorResponse(errorCode.getCode(), errorCode.getMessage());
    return new ResponseEntity<>(response, errorCode.getStatus());
  }

  // DTO 유효성 검사 실패 처리
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleValidationExceptions(
      MethodArgumentNotValidException ex) {
    String errorMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
    ErrorResponse response = new ErrorResponse(ErrorCode.INVALID_REQUEST.getCode(), errorMessage);
    return new ResponseEntity<>(response, ErrorCode.INVALID_REQUEST.getStatus());
  }

  @ExceptionHandler(Exception.class)
  public void handleException(Exception ex) {
    log.error(ex.getMessage(), ex);
  }
}
