package com.sparta.i_am_delivery.store.service;

import com.sparta.i_am_delivery.domain.user.entity.User;
import com.sparta.i_am_delivery.store.dto.request.StoreRequestDto;
import com.sparta.i_am_delivery.store.dto.StoreResponseDto;
import com.sparta.i_am_delivery.domain.store.entity.Store;
import com.sparta.i_am_delivery.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;


    public StoreResponseDto createStore(StoreRequestDto requestDto, User user){

        if (!user.getType().equals("OWNER")){
            throw new IllegalArgumentException("사장님 권한이 있는 유저만 가게를 생성할 수 있습니다.");
        }

        int storeCount = storeRepository.countByOwner(user);
        if (storeCount >= 3) {
            throw new IllegalArgumentException("가게는 최대 3개까지만 운영할 수 있습니다.");
        }


        validateStoreTimes(requestDto.getOpenTime(), requestDto.getCloseTime());

        validateMinimumPrice(requestDto.getMinimumPrice());


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

    private void validateMinimumPrice(Long minimumPrice) {
        if (minimumPrice == null || minimumPrice <= 18000) {
            throw new IllegalArgumentException("최소 주문 금액은 18000원 이상이어야 합니다.");
        }
    }

}
