package com.sparta.i_am_delivery.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
  // 담당별 예외 처리 추가 해주세요.

  // 회원 관련 에러 코드
  USER_NOT_FOUND("USER_NOT_FOUND", "유저를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
  USERNAME_DUPLICATED("USERNAME_DUPLICATED", "이미 사용 중인 유저명입니다.", HttpStatus.CONFLICT),
  EMAIL_DUPLICATED("EMAIL_DUPLICATED", "이미 사용 중인 이메일입니다.", HttpStatus.CONFLICT),
  INVALID_USER_TYPE("INVALID_USER_TYPE", "올바른 유저 타입을 입력해주세요.", HttpStatus.BAD_REQUEST),
  INACTIVE_MEMBER("INACTIVE_MEMBER", "탈퇴한 회원입니다.", HttpStatus.NOT_FOUND),
  LOGIN_FAILED("LOGIN_FAILED", "ID 혹은 PW 가 적합하지 않습니다.", HttpStatus.UNAUTHORIZED),
  NOT_FOUND_ACCESS_TOKEN(
      "NOT_FOUND_ACCESS_TOKEN", "접근할 수 없습니다. 로그인 해주세요.", HttpStatus.UNAUTHORIZED),
  INVALID_ACCESS_TOKEN("INVALID_ACCESS_TOKEN", "유효하지 않은 로그인 토큰 입니다.", HttpStatus.UNAUTHORIZED),
  UNAUTHORIZED_ACCESS("UNAUTHORIZED_ACCESS", "본인이 아닌 사용자입니다.", HttpStatus.FORBIDDEN),
  INVALID_PASSWORD("INVALID_PASSWORD", "현재 사용중인 비밀 번호입니다.", HttpStatus.BAD_REQUEST),
  PASSWORD_MISMATCH("PASSWORD_MISMATCH","현재 비밀번호가 일치하지 않습니다.",HttpStatus.UNAUTHORIZED),

  // 가게 관련 에러 코드
  STORE_NOT_FOUND("STORE_NOT_FOUND", "가게를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),

  // 메뉴 관련 에러 코드
  MENU_NOT_FOUND("MENU_NOT_FOUND", "메뉴를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),

  // 주문 관련 에러 코드
  ORDER_NOT_FOUND("ORDER_NOT_FOUND", "주문을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
  ORDER_NOT_COMPLETED("ORDER_NOT_COMPLETED", "배달이 완료되지 않았습니다.", HttpStatus.BAD_REQUEST),

  // 리뷰 관련 에러 코드
  REVIEW_NOT_FOUND("REVIEW_NOT_FOUND", "리뷰를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
  INVALID_STAR_RATING("INVALID_STAR_RATING", "별점은 1에서 5 사이여야 합니다.", HttpStatus.BAD_REQUEST),
  INVALID_STORE_OR_ORDER("INVALID_STORE_OR_ORDER", "유효하지 않은 가게 또는 주문입니다.", HttpStatus.BAD_REQUEST),
  REVIEW_ALREADY_EXISTS("REVIEW_ALREADY_EXISTS", "이미 리뷰가 존재합니다.", HttpStatus.CONFLICT),


  // 댓글 관련 에러 코드
  COMMENT_NOT_FOUND("COMMENT_NOT_FOUND", "댓글을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
  NO_COMMENT_PERMISSION("NO_COMMENT_PERMISSION", "댓글 수정/삭제 권한이 없습니다.", HttpStatus.FORBIDDEN),

  // 즐겨찾기 관련 에러 코드
  LIKE_NOT_FOUND("LIKE_NOT_FOUND", "즐겨찾기를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),

  // 기타 에러 코드
  INVALID_REQUEST("INVALID_REQUEST", "잘못된 요청입니다.", HttpStatus.BAD_REQUEST),
  INTERNAL_SERVER_ERROR(
      "INTERNAL_SERVER_ERROR", "서버 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);

  private final String code;
  private final String message;
  private final HttpStatus status;
}
