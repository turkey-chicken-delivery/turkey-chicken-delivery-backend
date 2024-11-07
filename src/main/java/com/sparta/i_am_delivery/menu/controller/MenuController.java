package com.sparta.i_am_delivery.menu.controller;

import com.sparta.i_am_delivery.common.annotation.AuthUser;
import com.sparta.i_am_delivery.common.exception.CustomException;
import com.sparta.i_am_delivery.common.exception.ErrorCode;
import com.sparta.i_am_delivery.domain.user.entity.User;
import com.sparta.i_am_delivery.menu.dto.request.MenuRequestDto;
import com.sparta.i_am_delivery.menu.dto.response.MenuPageReadResponseDto;
import com.sparta.i_am_delivery.menu.dto.response.MenuResponseDto;
import com.sparta.i_am_delivery.menu.service.MenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores/{storeId}")
public class MenuController {

  private final MenuService menuService;

  @PostMapping("/menus")
  public ResponseEntity<MenuResponseDto> createMenu(
      @Valid @AuthUser User user,
      @PathVariable Long storeId,
      @RequestBody MenuRequestDto requestDto) {
    MenuResponseDto menu = menuService.createMenu(storeId, requestDto, user);

    return ResponseEntity.status(HttpStatus.CREATED).body(menu);
  }

  @PutMapping("/menus/{Id}")
  public ResponseEntity<MenuResponseDto> updateMenu(
      @PathVariable Long Id,
      @AuthUser User user,
      @RequestBody MenuRequestDto requestDto,
      @PathVariable Long storeId) {
    MenuResponseDto menu = menuService.updateMenu(Id, requestDto, storeId, user);

    return ResponseEntity.status(HttpStatus.OK).body(menu);
  }

  @DeleteMapping("/menus/{id}")
  public ResponseEntity<Void> deleteMenu(@AuthUser User user, @PathVariable Long id) {
    menuService.deleteMenu(id, user);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/menus")
  public ResponseEntity<Page<MenuPageReadResponseDto>> getAllStoresMenu(
      @PathVariable Long storeId,
      @RequestParam(defaultValue = "1") int page,
      @RequestParam(defaultValue = "10") int size) {
    Page<MenuPageReadResponseDto> menuPage = menuService.getAllStoresMenu(storeId, page, size);
    return ResponseEntity.status(HttpStatus.OK).body(menuPage);
  }
}
