package com.sparta.i_am_delivery.store.dto.response;

import com.sparta.i_am_delivery.domain.store.entity.Store;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
@Getter
public class StoreUpdateResponseDto {
        private Long id;
        private LocalTime openTime;
        private LocalTime closeTime;
        private Long minimumPrice;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;

        public StoreUpdateResponseDto(Store saveStore) {
                this.id = saveStore.getId();
                this.openTime = saveStore.getOpenTime();
                this.closeTime = saveStore.getCloseTime();
                this.minimumPrice = saveStore.getMinimumPrice();
                this.createdAt = saveStore.getCreatedAt();
                this.modifiedAt = saveStore.getModifiedAt();
        }
}
