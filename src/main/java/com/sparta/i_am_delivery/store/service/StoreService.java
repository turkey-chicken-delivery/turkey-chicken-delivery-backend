package com.sparta.i_am_delivery.store.service;

import com.sparta.i_am_delivery.common.exception.CustomException;
import com.sparta.i_am_delivery.common.exception.ErrorCode;
import com.sparta.i_am_delivery.domain.comment.repository.CommentRepository;
import com.sparta.i_am_delivery.domain.like.repository.LikeRepository;
import com.sparta.i_am_delivery.domain.menu.entity.Menu;
import com.sparta.i_am_delivery.domain.menu.repository.MenuRepository;
import com.sparta.i_am_delivery.domain.order.repository.OrderRepository;
import com.sparta.i_am_delivery.domain.review.repository.ReviewRepository;
import com.sparta.i_am_delivery.domain.store.entity.Store;
import com.sparta.i_am_delivery.domain.store.repository.StoreRepository;
import com.sparta.i_am_delivery.domain.user.entity.User;
import com.sparta.i_am_delivery.store.dto.request.StoreCreateRequestDto;
import com.sparta.i_am_delivery.store.dto.request.StoreUpdateRequestDto;
import com.sparta.i_am_delivery.store.dto.response.StoreCreateResponseDto;
import com.sparta.i_am_delivery.store.dto.response.StoreDetailResponseDto;
import com.sparta.i_am_delivery.store.dto.response.StorePageReadResponseDto;
import com.sparta.i_am_delivery.store.dto.response.StoreUpdateResponseDto;
import com.sparta.i_am_delivery.user.enums.UserType;
import java.time.LocalTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
            .ownerMessage(requestDto.getOwnerMessage())
            .build();

    Store saveStore = storeRepository.save(store);
    return new StoreCreateResponseDto(saveStore);
  }

  private void validateStoreTimes(LocalTime openTime, LocalTime closeTime) {

    if (!openTime.isBefore(closeTime)) {
      throw new CustomException(ErrorCode.STORE_BAD_REQUEST);
    }
  }

  public StoreDetailResponseDto getDetailStore(Long id) {
    Store store =
        storeRepository
            .findById(id)
            .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND));

    List<Menu> menus = menuRepository.findAllByStoreId(id);
    return new StoreDetailResponseDto(store, menus);
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

  public Page<StorePageReadResponseDto> getAllStores(int page, int size) {
    PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.Direction.DESC, "modifiedAt");
    Page<Store> stores = storeRepository.findAll(pageRequest);
    return stores.map(
        store ->
            StorePageReadResponseDto.builder()
                .id(store.getId())
                .name(store.getName())
                .openTime(store.getOpenTime())
                .closeTime(store.getCloseTime())
                .minimumPrice(store.getMinimumPrice())
                .build());
  }
}
