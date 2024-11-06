package com.sparta.i_am_delivery.menu.service;

import com.sparta.i_am_delivery.common.exception.CustomException;
import com.sparta.i_am_delivery.common.exception.ErrorCode;
import com.sparta.i_am_delivery.domain.menu.entity.Menu;
import com.sparta.i_am_delivery.domain.menu.repository.MenuRepository;
import com.sparta.i_am_delivery.domain.store.entity.Store;
import com.sparta.i_am_delivery.domain.user.entity.User;
import com.sparta.i_am_delivery.domain.store.repository.StoreRepository;
import com.sparta.i_am_delivery.menu.dto.request.MenuRequestDto;
import com.sparta.i_am_delivery.menu.dto.response.MenuCreateResponseDto;
import com.sparta.i_am_delivery.menu.dto.response.MenuUpdateResponseDto;
import com.sparta.i_am_delivery.menu.dto.response.MenuPageReadResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sparta.i_am_delivery.domain.store.repository.StoreRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class MenuService {

  private final MenuRepository menuRepository;
  private final StoreRepository storeRepository;

    public MenuCreateResponseDto createMenu(Long storeId ,MenuRequestDto requestDto) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND));

        Menu menu = new Menu();
        menu.create(store, requestDto);
        menuRepository.save(menu);
        return new MenuCreateResponseDto(menu);
    }

    public MenuUpdateResponseDto updateMenu(Long Id, MenuRequestDto requestDto) {
        Menu menu = menuRepository.findById(Id)
                .orElseThrow(() -> new CustomException(ErrorCode.MENU_NOT_FOUND));

        menu.update(requestDto);
        menuRepository.save(menu);
        return new MenuUpdateResponseDto(menu);
    }

    public void deleteMenu(Long id, User user) {

        Menu menu = menuRepository.findById(id)
            .orElseThrow(() -> new CustomException(ErrorCode.MENU_NOT_FOUND));

        if (!menu.getOwner().getId().equals(user.getId())) {
            throw new CustomException(ErrorCode.INVALID_OWNER);
        }

        menuRepository.deleteById(id);
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
}
