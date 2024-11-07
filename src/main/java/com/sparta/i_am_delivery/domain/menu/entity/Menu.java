package com.sparta.i_am_delivery.domain.menu.entity;

import com.sparta.i_am_delivery.common.entity.TimeStamped;
import com.sparta.i_am_delivery.domain.store.entity.Store;
import com.sparta.i_am_delivery.menu.dto.request.MenuRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Table(name = "menus")
@NoArgsConstructor
@SQLDelete(sql = "UPDATE menus SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
public class Menu extends TimeStamped {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "store_id", nullable = false)
  private Store store;

  @Column(nullable = false)
  private String name;

  private Long price;

  public Menu(Store store, String name, Long price) {
    this.store = store;
    this.name = name;
    this.price = price;
  }

  public void create(Store store, MenuRequestDto requestDto) {
    this.store = store;
    this.name = requestDto.getName();
    this.price = requestDto.getPrice();
  }

  public void update(MenuRequestDto requestDto) {
    this.name = requestDto.getName();
    this.price = requestDto.getPrice();
  }
}
