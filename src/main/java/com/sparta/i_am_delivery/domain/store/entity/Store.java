package com.sparta.i_am_delivery.domain.store.entity;

import com.sparta.i_am_delivery.common.entity.TimeStamped;
import com.sparta.i_am_delivery.common.exception.CustomException;
import com.sparta.i_am_delivery.common.exception.ErrorCode;
import com.sparta.i_am_delivery.domain.user.entity.User;
import com.sparta.i_am_delivery.store.dto.request.StoreUpdateRequestDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity
@Getter
@Table(name = "stores")
@NoArgsConstructor
public class Store extends TimeStamped {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User owner;

  @Column(nullable = false)
  private String name;

  private LocalTime openTime;
  private LocalTime closeTime;
  private Long minimumPrice;

  @Builder
  public Store(
      User owner, String name, LocalTime openTime, LocalTime closeTime, Long minimumPrice) {
    this.owner = owner;
    this.name = name;
    this.openTime = openTime;
    this.closeTime = closeTime;
    this.minimumPrice = minimumPrice;
  }


    public void update(StoreUpdateRequestDto requestDto) {
        this.openTime = requestDto.getOpenTime();
        this.closeTime = requestDto.getCloseTime();
        this.minimumPrice = requestDto.getMinimumPrice();

    }
  public void validateOwner(Long userId) {
    if (this.owner.getId().equals(userId)) {
      throw new CustomException(ErrorCode.STORE_OWNER_ACTION_NOT_ALLOWED);
    }
  }
}
