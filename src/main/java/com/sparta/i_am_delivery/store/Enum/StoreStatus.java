package com.sparta.i_am_delivery.store.Enum;

import lombok.Getter;

@Getter
public enum StoreStatus {
  OPEN,       // 가게 오픈 중
  CLOSED,     // 가게 마감
  DELETE, // 가게 폐업
  REPAIR      // 가게 수리 중
}
