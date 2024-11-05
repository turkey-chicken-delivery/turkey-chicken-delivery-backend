package com.sparta.i_am_delivery.like.service;

import com.sparta.i_am_delivery.common.exception.CustomException;
import com.sparta.i_am_delivery.common.exception.ErrorCode;
import com.sparta.i_am_delivery.domain.like.entity.Like;
import com.sparta.i_am_delivery.domain.like.repository.LikeRepository;
import com.sparta.i_am_delivery.domain.store.entity.Store;
import com.sparta.i_am_delivery.domain.store.repository.StoreRepository;
import com.sparta.i_am_delivery.domain.user.entity.User;
import com.sparta.i_am_delivery.like.dto.info.StoreInfo;
import com.sparta.i_am_delivery.like.dto.response.LikeAddResponseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {

  private final StoreRepository storeRepository;
  private final LikeRepository likeRepository;

  @Transactional
  public LikeAddResponseDto addToStoreLike(Long storeId, User user) {
    Store store =
        storeRepository
            .findById(storeId)
            .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND));
    if (likeRepository.existsByUserIdAndStoreId(user.getId(), store.getId())) {
      throw new CustomException(ErrorCode.LIKE_DUPLICATE);
    }
    store.validateOwner(user.getId());
    Like like = Like.builder().user(user).store(store).build();
    likeRepository.save(like);
    StoreInfo storeInfo =
        StoreInfo.builder()
            .id(store.getId())
            .name(store.getName())
            .openTime(store.getOpenTime())
            .closeTime(store.getCloseTime())
            .minimumPrice(store.getMinimumPrice())
            .build();
    return LikeAddResponseDto.builder().id(like.getId()).storeInfo(storeInfo).build();
  }
}
