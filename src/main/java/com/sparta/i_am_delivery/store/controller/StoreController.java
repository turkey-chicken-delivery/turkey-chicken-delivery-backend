package com.sparta.i_am_delivery.store.controller;

import com.sparta.i_am_delivery.domain.user.entity.User;
import com.sparta.i_am_delivery.store.dto.request.StoreRequestDto;
import com.sparta.i_am_delivery.store.dto.StoreResponseDto;
import com.sparta.i_am_delivery.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class StoreController {
    private  final StoreService storeService;

    @PostMapping("/api/stores")
    public ResponseEntity<StoreResponseDto> createStore (@RequestBody StoreRequestDto requestDto, User user) {

        StoreResponseDto responseDto = storeService.createStore (requestDto,user);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

}
