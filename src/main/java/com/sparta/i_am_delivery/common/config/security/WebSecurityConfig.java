package com.sparta.i_am_delivery.common.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
            .csrf(csrf -> csrf.disable()) // CSRF 비활성화
            .formLogin(form -> form.disable()) // Form Login 비활성화
            .httpBasic(httpBasic -> httpBasic.disable()) // HTTP Basic 비활성화
            .authorizeRequests(authorize -> authorize
                    .requestMatchers("/api/users") // 회원가입 엔드포인트
                    .permitAll()
                    .anyRequest() // 그 외 모든 요청
                    .authenticated())
            .sessionManagement(sessionManagement ->
                    sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // 세션 비활성화

    return http.build();
  }
}

