package com.sparta.i_am_delivery.menu.dto.response;

import com.sparta.i_am_delivery.domain.menu.entity.Menu;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class MenuResponseDto {
    private Long id;
    private String name;
    private Long price;

    public MenuResponseDto(Menu menu) {
        this.id = menu.getId();
        this.name = menu.getName();
        this.price = menu.getPrice();
    }

}
