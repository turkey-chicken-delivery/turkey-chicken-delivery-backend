package com.sparta.i_am_delivery.store.controller;

import com.sparta.i_am_delivery.common.annotation.AuthUser;
import com.sparta.i_am_delivery.domain.user.entity.User;
import com.sparta.i_am_delivery.store.dto.request.StoreCreateRequestDto;
import com.sparta.i_am_delivery.store.dto.response.StoreCreateResponseDto;
import com.sparta.i_am_delivery.store.service.StoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreController {
    private  final StoreService storeService;

    @PostMapping()
    public ResponseEntity<StoreCreateResponseDto> createStore (@Valid @RequestBody StoreCreateRequestDto requestDto, @AuthUser User user) {

        StoreCreateResponseDto responseDto = storeService.createStore (requestDto,user);


        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

}
