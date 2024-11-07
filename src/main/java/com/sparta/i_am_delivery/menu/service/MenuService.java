package com.sparta.i_am_delivery.menu.service;

import com.sparta.i_am_delivery.common.exception.CustomException;
import com.sparta.i_am_delivery.common.exception.ErrorCode;
import com.sparta.i_am_delivery.domain.menu.entity.Menu;
import com.sparta.i_am_delivery.domain.menu.repository.MenuRepository;
import com.sparta.i_am_delivery.domain.store.entity.Store;
import com.sparta.i_am_delivery.domain.store.repository.StoreRepository;
import com.sparta.i_am_delivery.domain.user.entity.User;
import com.sparta.i_am_delivery.menu.dto.request.MenuRequestDto;
import com.sparta.i_am_delivery.menu.dto.response.MenuPageReadResponseDto;
import com.sparta.i_am_delivery.menu.dto.response.MenuResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MenuService {

  private final MenuRepository menuRepository;
  private final StoreRepository storeRepository;

  public MenuResponseDto createMenu(Long storeId, MenuRequestDto requestDto, User user) {
    Store store =
        storeRepository
            .findById(storeId)
            .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND));

    validateOwner(store, user);

    validateMenuNameUniqueness(storeId, requestDto.getName());

    Menu menu = new Menu();
    menu.create(store, requestDto);
    menuRepository.save(menu);
    return new MenuResponseDto(menu);
  }

  public MenuResponseDto updateMenu(Long id, MenuRequestDto requestDto, Long storeId, User user) {
    Menu menu =
        menuRepository
            .findById(id)
            .orElseThrow(() -> new CustomException(ErrorCode.MENU_NOT_FOUND));

    validateOwner(menu.getStore(), user);

    validateMenuNameUniqueness(menu.getStore().getId(), requestDto.getName());

    menu.update(requestDto);
    menuRepository.save(menu);
    return new MenuResponseDto(menu);
  }

  public void deleteMenu(Long id, User user) {
    Menu menu =
        menuRepository
            .findById(id)
            .orElseThrow(() -> new CustomException(ErrorCode.MENU_NOT_FOUND));
    validateOwner(menu.getStore(), user);
    menuRepository.delete(menu);
  }

  public Page<MenuPageReadResponseDto> getAllStoresMenu(Long storeId, int page, int size) {
    PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.Direction.DESC, "modifiedAt");
    Store store =
        storeRepository
            .findById(storeId)
            .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND));
    Page<Menu> menus = menuRepository.findAllByStoreId(store.getId(), pageRequest);
    return menus.map(
        menu ->
            MenuPageReadResponseDto.builder()
                .id(menu.getId())
                .name(menu.getName())
                .price(menu.getPrice())
                .createdAt(menu.getCreatedAt())
                .build());
  }

  private void validateOwner(Store store, User user) {
    if (!store.getOwner().getId().equals(user.getId())) {
      throw new CustomException(ErrorCode.INVALID_OWNER);
    }
  }

  private void validateMenuNameUniqueness(Long storeId, String menuName) {
    boolean exists = menuRepository.existsByStoreIdAndName(storeId, menuName);
    if (exists) {
      throw new CustomException(ErrorCode.DUPLICATE_MENU_NAME);
    }
  }
}
