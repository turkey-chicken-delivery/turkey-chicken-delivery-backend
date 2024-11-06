package com.sparta.i_am_delivery.domain.menu.entity;

import com.sparta.i_am_delivery.common.entity.TimeStamped;
import com.sparta.i_am_delivery.domain.store.entity.Store;
import com.sparta.i_am_delivery.domain.user.entity.User;
import com.sparta.i_am_delivery.menu.dto.request.MenuRequestDto;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Table(name = "menus")
@SQLDelete(sql = "UPDATE menus SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
@NoArgsConstructor
public class Menu extends TimeStamped {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "store_id", nullable = false)
  private Store store;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User owner;

  private LocalDateTime deletedAt;

  @Column(nullable = false)
  private String name;

    private Long price;

    public Menu(Store store, User owner, String name, Long price) {
        this.store = store;
      this.owner = owner;
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
