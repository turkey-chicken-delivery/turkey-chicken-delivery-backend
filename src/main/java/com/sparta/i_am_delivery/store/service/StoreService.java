package com.sparta.i_am_delivery.store.service;

import com.sparta.i_am_delivery.common.exception.CustomException;
import com.sparta.i_am_delivery.common.exception.ErrorCode;
import com.sparta.i_am_delivery.domain.store.entity.Store;
import com.sparta.i_am_delivery.domain.store.repository.StoreRepository;
import com.sparta.i_am_delivery.domain.user.entity.User;
import com.sparta.i_am_delivery.store.dto.request.StoreUpdateRequestDto;
import com.sparta.i_am_delivery.store.dto.response.StoreCreateResponseDto;
import com.sparta.i_am_delivery.store.dto.request.StoreCreateRequestDto;
import com.sparta.i_am_delivery.store.dto.response.StoreUpdateResponseDto;
import com.sparta.i_am_delivery.user.enums.UserType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;


    @Transactional
    public StoreCreateResponseDto createStore(StoreCreateRequestDto requestDto, User user) {

        if (user.getType() != UserType.OWNER) {
            throw new CustomException(ErrorCode.INVALID_OWNER);
        }

        int storeCount = storeRepository.countByOwner(user);
        if (storeCount >= 3) {
            throw new CustomException(ErrorCode.TOO_MANY_STORE);
        }


        validateStoreTimes(requestDto.getOpenTime(), requestDto.getCloseTime());


        Store store = Store.builder()
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


    public StoreUpdateResponseDto updateStore(Long id, StoreUpdateRequestDto requestDto, User user) {

        Store updateStore = storeRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND));

        if (updateStore.getOwner().getId() != user.getId()) {
            throw new CustomException(ErrorCode.INVALID_OWNER);
        }
        updateStore.update(requestDto);
        storeRepository.save(updateStore);

        return new StoreUpdateResponseDto(updateStore);
    }
}
