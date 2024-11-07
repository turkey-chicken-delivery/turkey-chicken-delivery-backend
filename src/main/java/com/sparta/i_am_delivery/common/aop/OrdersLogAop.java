package com.sparta.i_am_delivery.common.aop;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

@Slf4j(topic = "ManagerLogAop")
@Aspect
@Component
@RequiredArgsConstructor
public class OrdersLogAop {
  @Pointcut("execution(* com.sparta.i_am_delivery.order.controller..*(..))")
  private void orderPointcut() {}

  @Around("orderPointcut()")
  public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
    LocalDateTime startTime = LocalDateTime.now();

    ServletRequestAttributes attributes =
        (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = attributes.getRequest();
    ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
    String storeId = joinPoint.getArgs()[0].toString();

    Object output = joinPoint.proceed();

    ContentCachingResponseWrapper responseWrapper =
        new ContentCachingResponseWrapper(attributes.getResponse());
    String responseBody =
        new String(responseWrapper.getContentAsByteArray(), StandardCharsets.UTF_8);
    String url = requestWrapper.getRequestURI();
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      JsonNode jsonResponse = objectMapper.readTree(responseBody);
      String orderId = jsonResponse.path("orderId").asText(null);
      log.info(
          "Request Time: {},Request URL: {}, Store ID: {}, Order ID: {}",
          startTime,
          url,
          storeId,
          orderId);
      return output;
    } finally {
      responseWrapper.copyBodyToResponse();
    }
  }
}
