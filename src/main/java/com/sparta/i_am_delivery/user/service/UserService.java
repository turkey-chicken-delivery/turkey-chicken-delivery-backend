package com.sparta.i_am_delivery.user.service;

import com.sparta.i_am_delivery.common.config.jwt.JwtHelper;
import com.sparta.i_am_delivery.common.exception.CustomException;
import com.sparta.i_am_delivery.common.exception.ErrorCode;
import com.sparta.i_am_delivery.domain.like.repository.LikeRepository;
import com.sparta.i_am_delivery.domain.menu.entity.Menu;
import com.sparta.i_am_delivery.domain.menu.repository.MenuRepository;
import com.sparta.i_am_delivery.domain.order.entity.Order;
import com.sparta.i_am_delivery.domain.order.repository.OrderRepository;
import com.sparta.i_am_delivery.domain.store.entity.Store;
import com.sparta.i_am_delivery.domain.store.repository.StoreRepository;
import com.sparta.i_am_delivery.domain.user.entity.User;
import com.sparta.i_am_delivery.domain.user.repository.UserRepository;
import com.sparta.i_am_delivery.menu.dto.info.MenuInfo;
import com.sparta.i_am_delivery.store.dto.info.StoreInfo;
import com.sparta.i_am_delivery.user.dto.response.OrderPageReadResponseDto;
import com.sparta.i_am_delivery.user.dto.request.UserDeleteRequestDto;
import com.sparta.i_am_delivery.user.dto.request.UserSignUpRequestDto;
import com.sparta.i_am_delivery.user.dto.request.UserUpdatePasswordRequestDto;
import com.sparta.i_am_delivery.user.dto.response.UserSignUpResponseDto;
import com.sparta.i_am_delivery.user.dto.response.UserUpdateNameResponseDto;
import com.sparta.i_am_delivery.user.enums.UserType;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtHelper jwtHelper;
  private final StoreRepository storeRepository;
  private final LikeRepository likeRepository;
  private final OrderRepository orderRepository;
  private final MenuRepository menuRepository;

  @Transactional
  public UserSignUpResponseDto signUp(UserSignUpRequestDto userSingUpRequestDto) {
    this.isValidateUserUniqueness(userSingUpRequestDto.getEmail(), userSingUpRequestDto.getName());
    User user =
        User.builder()
            .email(userSingUpRequestDto.getEmail())
            .password(passwordEncoder.encode(userSingUpRequestDto.getPassword()))
            .name(userSingUpRequestDto.getName())
            .type(UserType.valueOf(userSingUpRequestDto.getType()))
            .build();
    userRepository.save(user);
    return UserSignUpResponseDto.builder()
        .id(user.getId())
        .email(user.getEmail())
        .name(user.getName())
        .type(user.getType())
        .build();
  }

  public String logIn(String email, String password) {
    User user =
        userRepository
            .findByEmail(email)
            .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    user.authenticate(password, passwordEncoder);
    return jwtHelper.generateAccessToken(user.getId(), user.getEmail(), user.getType());
  }

  @Transactional
  public UserUpdateNameResponseDto updateName(User user, Long id, String name) {
    user.validateUserIdentity(id);
    if (userRepository.existsByName(name)) {
      throw new CustomException(ErrorCode.USERNAME_DUPLICATED);
    }
    user.updateName(name);
    userRepository.save(user);
    return UserUpdateNameResponseDto.builder().id(user.getId()).name(user.getName()).build();
  }

  @Transactional
  public void updatePassword(
      User user, Long id, UserUpdatePasswordRequestDto userUpdatePasswordRequestDto) {
    user.updatePassword(
        id,
        userUpdatePasswordRequestDto.getCurrentPassword(),
        userUpdatePasswordRequestDto.getChangePassword(),
        passwordEncoder);
    userRepository.save(user);
  }

  @Transactional
  public void deleteUser(User user, Long id, @Valid UserDeleteRequestDto userDeleteRequestDto) {
    user.validateUserIdentity(id);
    user.validateUserPassword(userDeleteRequestDto.getPassword(), passwordEncoder);
    if (user.isOwner() && storeRepository.existsByOwnerIdAndDeletedAtIsNull(user.getId())) {
      throw new CustomException(ErrorCode.ACTIVE_STORE_EXISTS);
    }
    likeRepository.deleteByUserId(user.getId());
    user.delete();
    userRepository.save(user);
  }

  @Transactional
  public Page<OrderPageReadResponseDto> getUsersOrder(
      int pageNo, int pageSize, User user, Long id) {
    user.validateUserIdentity(id);
    PageRequest pageRequest =
        PageRequest.of(pageNo - 1, pageSize, Sort.Direction.DESC, "modifiedAt");
    Page<Order> orders = orderRepository.findAllByUserId(user.getId(), pageRequest);
    return orders.map(
        order -> {
          Menu menu = menuRepository.findByIdIncludeDeleted(order.getMenu().getId());
          MenuInfo menuInfo =
              MenuInfo.builder().name(menu.getName()).price(menu.getPrice()).build();
          Store store = order.getStore();
          StoreInfo storeDto =
              StoreInfo.builder()
                  .id(store.getId())
                  .name(store.getName())
                  .minimumPrice(store.getMinimumPrice())
                  .openTime(store.getOpenTime())
                  .closeTime(store.getCloseTime())
                  .build();
          return mapToOrderPageReadResponseDto(order, storeDto, menuInfo);
        });
  }

  private OrderPageReadResponseDto mapToOrderPageReadResponseDto(
      Order order, StoreInfo storeDto, MenuInfo menuInfo) {
    return OrderPageReadResponseDto.builder()
        .orderId(order.getId())
        .storeInfo(storeDto)
        .menuInfo(menuInfo)
        .totalPrice(order.getTotalPrice())
        .status(order.getStatus())
        .modifiedAt(order.getModifiedAt())
        .quantity(order.getQuantity())
        .build();
  }

  private void isValidateUserUniqueness(String email, String name) {
    Optional<User> foundUser = userRepository.findByEmailOrNameIncludingDeleted(email, name);
    if (foundUser.isPresent()) {
      User existingUser = foundUser.get();
      if (existingUser.getEmail().equals(email)) {
        throw new CustomException(ErrorCode.EMAIL_DUPLICATED);
      }
      if (existingUser.getName().equals(name)) {
        throw new CustomException(ErrorCode.USERNAME_DUPLICATED);
      }
    }
  }
}
