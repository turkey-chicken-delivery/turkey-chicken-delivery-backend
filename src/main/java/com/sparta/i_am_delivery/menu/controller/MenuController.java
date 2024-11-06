package com.sparta.i_am_delivery.menu.controller;

import com.sparta.i_am_delivery.menu.dto.request.MenuRequestDto;
import com.sparta.i_am_delivery.menu.dto.response.MenuCreateResponseDto;
import com.sparta.i_am_delivery.menu.dto.response.MenuUpdateResponseDto;
import com.sparta.i_am_delivery.menu.dto.response.MenuPageReadResponseDto;
import com.sparta.i_am_delivery.menu.service.MenuService;
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
    public ResponseEntity<MenuCreateResponseDto> createMenu(@PathVariable Long storeId, @RequestBody MenuRequestDto requestDto) {
        MenuCreateResponseDto menu = menuService.createMenu(storeId, requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(menu);
    }

    @PutMapping("/menus/{Id}")
    public ResponseEntity<MenuUpdateResponseDto> updateMenu(@PathVariable Long Id, @RequestBody MenuRequestDto requestDto) {
        MenuUpdateResponseDto menu = menuService.updateMenu(Id, requestDto);

        return ResponseEntity.status(HttpStatus.OK).body(menu);
    }

    @DeleteMapping("/menus/{id}")
    public ResponseEntity<Void> deleteMenu(@PathVariable Long id) {
        menuService.deleteMenu(id);
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
