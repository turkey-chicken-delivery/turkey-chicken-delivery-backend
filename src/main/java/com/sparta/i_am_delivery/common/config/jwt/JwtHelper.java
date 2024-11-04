package com.sparta.i_am_delivery.common.config.jwt;

import com.sparta.i_am_delivery.user.enums.UserType;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtHelper {
  @Value("${jwt.secret.key}")
  private String secretKey;

  @Value("${token-expires-in}")
  private long expiresIn;

  private Key key;

  private static final String BEARER_PREFIX = "Bearer ";

  @PostConstruct
  public void init() {
    key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
  }

  public String generateAccessToken(Long userId, UserType userType) {
    return BEARER_PREFIX
        + Jwts.builder()
            .setSubject(userId.toString())
            .claim("userType",userType)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + expiresIn))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
  }

  // HttpServletResponse Header 에 토큰삽입 후 전달.
  public void addToken(HttpServletResponse response, String token) {
    response.addHeader("Authorization", token);
  }
}
