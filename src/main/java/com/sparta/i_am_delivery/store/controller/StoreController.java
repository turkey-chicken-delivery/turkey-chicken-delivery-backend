package com.sparta.i_am_delivery.store.controller;

import com.sparta.i_am_delivery.common.annotation.AuthUser;
import com.sparta.i_am_delivery.domain.user.entity.User;
import com.sparta.i_am_delivery.store.dto.request.StoreCreateRequestDto;
import com.sparta.i_am_delivery.store.dto.request.StoreUpdateRequestDto;
import com.sparta.i_am_delivery.store.dto.response.StoreCreateResponseDto;
import com.sparta.i_am_delivery.store.dto.response.StoreDetailResponseDto;
import com.sparta.i_am_delivery.store.dto.response.StorePageReadResponseDto;
import com.sparta.i_am_delivery.store.dto.response.StoreUpdateResponseDto;
import com.sparta.i_am_delivery.store.service.StoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreController {
  private final StoreService storeService;

  @PostMapping()
  public ResponseEntity<StoreCreateResponseDto> createStore(
      @AuthUser User user, @Valid @RequestBody StoreCreateRequestDto requestDto) {

    StoreCreateResponseDto responseDto = storeService.createStore(requestDto, user);

    return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
  }

  @GetMapping("/{id}")
  public ResponseEntity<StoreDetailResponseDto> getDetailStore(@PathVariable Long id) {
    StoreDetailResponseDto store = storeService.getDetailStore(id);
    return ResponseEntity.status(HttpStatus.OK).body(store);
  }

  @PutMapping("/{id}")
  public ResponseEntity<StoreUpdateResponseDto> updateStore(
      @PathVariable Long id,
      @AuthUser User user,
      @Valid @RequestBody StoreUpdateRequestDto requestDto) {

    StoreUpdateResponseDto responseDto = storeService.updateStore(id, requestDto, user);

    return ResponseEntity.status(HttpStatus.OK).body(responseDto);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteStore(@Valid @PathVariable Long id, @AuthUser User user) {
    storeService.deleteStore(id, user);
    return ResponseEntity.noContent().build();
  }

  @GetMapping()
  public ResponseEntity<Page<StorePageReadResponseDto>> getAllStores(
      @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
    Page<StorePageReadResponseDto> storePage = storeService.getAllStores(page, size);
    return ResponseEntity.status(HttpStatus.OK).body(storePage);
  }
}
