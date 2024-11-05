package com.sparta.i_am_delivery.store.service;

import com.sparta.i_am_delivery.common.exception.CustomException;
import com.sparta.i_am_delivery.common.exception.ErrorCode;
import com.sparta.i_am_delivery.domain.store.entity.Store;
import com.sparta.i_am_delivery.domain.store.repository.StoreRepository;
import com.sparta.i_am_delivery.domain.user.entity.User;
import com.sparta.i_am_delivery.store.dto.response.StoreResponseDto;
import com.sparta.i_am_delivery.store.dto.request.StoreRequestDto;
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
    public StoreResponseDto createStore(StoreRequestDto requestDto, User user){

        if (user.getType() != UserType.OWNER){
            throw new CustomException(ErrorCode.INVALID_OWNER);
        }

        int storeCount = storeRepository.countByOwner(user);
        if (storeCount >= 3) {
            throw new IllegalArgumentException("가게는 최대 3개까지만 운영할 수 있습니다.");
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
        return new StoreResponseDto(saveStore);
    }

    private void validateStoreTimes(LocalTime openTime, LocalTime closeTime) {

        if (!openTime.isBefore(closeTime)) {
            throw new IllegalArgumentException("오픈 시간은 마감 시간보다 이전이어야 합니다.");
        }
    }


}
