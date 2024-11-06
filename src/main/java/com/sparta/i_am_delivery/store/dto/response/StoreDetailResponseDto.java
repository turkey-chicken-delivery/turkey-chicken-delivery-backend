package com.sparta.i_am_delivery.store.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sparta.i_am_delivery.domain.menu.entity.Menu;
import com.sparta.i_am_delivery.domain.store.entity.Store;
import com.sparta.i_am_delivery.menu.dto.response.MenuResponseDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class StoreDetailResponseDto {
  private Long id;
  @NotBlank(message = "이름을 입력해주세요.")
  private String name;
  @NotNull(message = "오픈시간을 입력해주세요.")
  @JsonFormat(pattern = "HH:mm:ss")
  private LocalTime openTime;
  @JsonFormat(pattern = "HH:mm:ss")
  @NotNull(message = "마감시간을 입력해주세요.")
  private LocalTime closeTime;
  private Long minimumPrice;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime createdAt;
  @NotNull(message = "최소 1자 이상 입력해주세요.")
  @Size(min = 0, max = 50, message = "최소 0자, 최대 50자까지 입력가능합니다.")
  private String ownerMessage;
  private List<MenuResponseDto> menuList;

  public StoreDetailResponseDto(Store store, List<Menu> menus) {
    this.id = store.getId();
    this.name = store.getName();
    this.openTime = store.getOpenTime();
    this.closeTime = store.getCloseTime();
    this.minimumPrice = store.getMinimumPrice();
    this.createdAt = store.getCreatedAt();
    this.ownerMessage = store.getOwnerMessage();
    this.menuList = menus.stream().map(MenuResponseDto::new).collect(Collectors.toList());
  }
}
