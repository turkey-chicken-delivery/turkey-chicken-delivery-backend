package com.sparta.i_am_delivery.store.service;

import com.sparta.i_am_delivery.domain.user.entity.User;
import com.sparta.i_am_delivery.store.dto.StoreRequestDto;
import com.sparta.i_am_delivery.store.dto.StoreResponseDto;
import com.sparta.i_am_delivery.domain.store.entity.Store;
import com.sparta.i_am_delivery.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;


    public StoreResponseDto createStore(StoreRequestDto requestDto, User user){

        Store store = Store.builder()
                .owner(user)
                .name(requestDto.getName())
                .openTime(requestDto.getOpenTime())
                .closeTime(requestDto.getCloseTime())
                .minimumPrice(requestDto.getMinimumPrice())
                .build();

        Store saveStore = storeRepository.save(store);
        return new StoreResponseDto(saveStore);
    }
}
