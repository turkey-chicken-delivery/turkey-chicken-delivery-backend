package com.sparta.i_am_delivery.user.resolver;

import com.sparta.i_am_delivery.common.annotation.AuthUser;
import com.sparta.i_am_delivery.domain.user.entity.User;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class AuthUserResolver implements HandlerMethodArgumentResolver {

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    boolean hasAuthUserAnnotation = parameter.hasParameterAnnotation(AuthUser.class);
    boolean isUserType = User.class.isAssignableFrom(parameter.getParameterType());
    return hasAuthUserAnnotation && isUserType;
  }

  @Override
  public Object resolveArgument(
      MethodParameter parameter,
      ModelAndViewContainer mavContainer,
      NativeWebRequest webRequest,
      WebDataBinderFactory binderFactory)
      throws Exception {
    Optional<Authentication> authenticationOpt =
        Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());

    User user = (User) authenticationOpt.get().getPrincipal();
    return user;
  }
}
