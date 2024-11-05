package com.sparta.i_am_delivery.store.controller;

import com.sparta.i_am_delivery.common.annotation.AuthUser;
import com.sparta.i_am_delivery.domain.user.entity.User;
import com.sparta.i_am_delivery.store.dto.request.StoreUpdateRequestDto;
import com.sparta.i_am_delivery.store.dto.response.StoreUpdateResponseDto;
import com.sparta.i_am_delivery.store.dto.request.StoreCreateRequestDto;
import com.sparta.i_am_delivery.store.dto.response.StoreCreateResponseDto;
import com.sparta.i_am_delivery.store.service.StoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreController {
    private  final StoreService storeService;

    @PostMapping()
    public ResponseEntity<StoreCreateResponseDto> createStore (@Valid @AuthUser User user, @RequestBody StoreCreateRequestDto requestDto) {

        StoreCreateResponseDto responseDto = storeService.createStore (requestDto,user);


        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StoreUpdateResponseDto> updateStore (@PathVariable Long id, @AuthUser User user, @RequestBody StoreUpdateRequestDto requestDto){

        StoreUpdateResponseDto responseDto = storeService.updateStore (id,requestDto,user);

        return  ResponseEntity.ok().body(responseDto);

    }
}
