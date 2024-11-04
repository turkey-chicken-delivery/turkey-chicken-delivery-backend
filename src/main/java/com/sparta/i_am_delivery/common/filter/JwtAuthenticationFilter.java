package com.sparta.i_am_delivery.common.filter;

import com.sparta.i_am_delivery.common.config.jwt.JwtHelper;
import com.sparta.i_am_delivery.common.exception.CustomException;
import com.sparta.i_am_delivery.common.exception.ErrorCode;
import com.sparta.i_am_delivery.domain.user.entity.User;
import com.sparta.i_am_delivery.domain.user.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Order(1)
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtHelper jwtHelper;
  private final UserRepository userRepository;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    if (this.isApplicable(request)) {
      filterChain.doFilter(request, response);
      return;
    }
    // Request 에서 쿠키를 읽어 JWT 토큰 추출
    String accessToken = extractTokenFromCookies(request);
    jwtHelper.validate(accessToken);
    Authentication authentication = getAuthentication(accessToken);
    // SecurityContext 에 인증 정보 설정
    SecurityContextHolder.getContext().setAuthentication(authentication);
    filterChain.doFilter(request, response);
  }

  public boolean isApplicable(HttpServletRequest request) {
    return "/api/users/login".equals(request.getRequestURI())
        || "/api/users".equals(request.getRequestURI());
  }

  public String extractTokenFromCookies(HttpServletRequest request) {
    return Arrays.stream(
            Optional.ofNullable(request.getCookies())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ACCESS_TOKEN)))
        .filter(cookie -> "Authorization".equals(cookie.getName()))
        .findFirst()
        .map(
            cookie -> {
              try {
                // URL 디코딩 수행
                String token = URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8.name());
                // Bearer 접두사 제거
                if (token.startsWith("Bearer ")) {
                  return token.substring(7);
                }
                return token;
              } catch (UnsupportedEncodingException e) {
                throw new CustomException(ErrorCode.INVALID_ACCESS_TOKEN);
              }
            })
        .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ACCESS_TOKEN));
  }

  private Authentication getAuthentication(String token) {
    Claims claims = jwtHelper.getUserInfoFromToken(token);
    Long id = Long.valueOf(claims.getSubject());

    User user =
        userRepository
            .findById(id)
            .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

    List<SimpleGrantedAuthority> authorities =
        Collections.singletonList(new SimpleGrantedAuthority(user.getType().toString()));
    return new UsernamePasswordAuthenticationToken(user, token, authorities);
  }
}
