package com.sparta.i_am_delivery.store.service;

import com.sparta.i_am_delivery.common.exception.CustomException;
import com.sparta.i_am_delivery.common.exception.ErrorCode;
import com.sparta.i_am_delivery.domain.comment.repository.CommentRepository;
import com.sparta.i_am_delivery.domain.like.repository.LikeRepository;
import com.sparta.i_am_delivery.domain.menu.repository.MenuRepository;
import com.sparta.i_am_delivery.domain.order.repository.OrderRepository;
import com.sparta.i_am_delivery.domain.review.repository.ReviewRepository;
import com.sparta.i_am_delivery.domain.store.entity.Store;
import com.sparta.i_am_delivery.domain.store.repository.StoreRepository;
import com.sparta.i_am_delivery.domain.user.entity.User;
import com.sparta.i_am_delivery.store.dto.request.StoreCreateRequestDto;
import com.sparta.i_am_delivery.store.dto.request.StoreUpdateRequestDto;
import com.sparta.i_am_delivery.store.dto.response.StoreCreateResponseDto;
import com.sparta.i_am_delivery.store.dto.response.StoreUpdateResponseDto;
import com.sparta.i_am_delivery.user.enums.UserType;
import java.time.LocalTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreService {

  private final CommentRepository commentRepository;
  private final StoreRepository storeRepository;
  private final OrderRepository orderRepository;
  private final MenuRepository menuRepository;
  private final ReviewRepository reviewRepository;
  private final LikeRepository likeRepository;

  @Transactional
  public StoreCreateResponseDto createStore(StoreCreateRequestDto requestDto, User user) {

    if (user.getType() != UserType.OWNER) {
      throw new CustomException(ErrorCode.INVALID_OWNER);
    }

    int storeCount = storeRepository.countByOwnerAndDeletedAtIsNull(user);
    if (storeCount >= 3) {
      throw new CustomException(ErrorCode.TOO_MANY_STORE);
    }

    validateStoreTimes(requestDto.getOpenTime(), requestDto.getCloseTime());

    Store store =
        Store.builder()
            .owner(user)
            .name(requestDto.getName())
            .openTime(requestDto.getOpenTime())
            .closeTime(requestDto.getCloseTime())
            .minimumPrice(requestDto.getMinimumPrice())
            .build();

    Store saveStore = storeRepository.save(store);
    return new StoreCreateResponseDto(saveStore);
  }

  private void validateStoreTimes(LocalTime openTime, LocalTime closeTime) {

    if (!openTime.isBefore(closeTime)) {
      throw new CustomException(ErrorCode.STORE_BAD_REQUEST);
    }
  }

  @Transactional
  public StoreUpdateResponseDto updateStore(Long id, StoreUpdateRequestDto requestDto, User user) {

    Store updateStore =
        storeRepository
            .findById(id)
            .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND));

    if (!updateStore.getOwner().getId().equals(user.getId())) {
      throw new CustomException(ErrorCode.INVALID_OWNER);
    }
    updateStore.update(requestDto);
    storeRepository.save(updateStore);

    return new StoreUpdateResponseDto(updateStore);
  }

  @Transactional
  public void deleteStore(Long id, User user) {
    Store deleteStore =
        storeRepository
            .findById(id)
            .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND));

    if (!deleteStore.getOwner().getId().equals(user.getId())) {
      throw new CustomException(ErrorCode.INVALID_OWNER);
    }
    deleteStore.delete();

    storeRepository.save(deleteStore);
    commentRepository.deleteByStoreId(id);
    orderRepository.deleteByStoreId(id);
    menuRepository.deleteByStoreId(id);
    reviewRepository.deleteByStoreId(id);
    likeRepository.deleteByStoreId(id);
  }
}
