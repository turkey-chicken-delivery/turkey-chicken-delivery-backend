package com.sparta.i_am_delivery.menu.service;

import com.sparta.i_am_delivery.common.exception.CustomException;
import com.sparta.i_am_delivery.common.exception.ErrorCode;
import com.sparta.i_am_delivery.domain.menu.entity.Menu;
import com.sparta.i_am_delivery.domain.menu.repository.MenuRepository;
import com.sparta.i_am_delivery.domain.store.entity.Store;
import com.sparta.i_am_delivery.menu.dto.request.MenuRequestDto;
import com.sparta.i_am_delivery.menu.dto.response.MenuResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sparta.i_am_delivery.domain.store.repository.StoreRepository;

@Service
@RequiredArgsConstructor
@Transactional

public class MenuService {

    private final MenuRepository menuRepository;
    private final StoreRepository storeRepository;

    public MenuResponseDto createMenu(Long storeId ,MenuRequestDto requestDto) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND));

        Menu menu = new Menu();
        menu.create(store, requestDto);
        menuRepository.save(menu);
        return new MenuResponseDto(menu);
    }

}
