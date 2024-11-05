package com.sparta.i_am_delivery.domain.order.entity;

import com.sparta.i_am_delivery.common.entity.TimeStamped;
import com.sparta.i_am_delivery.domain.menu.entity.Menu;
import com.sparta.i_am_delivery.domain.store.entity.Store;
import com.sparta.i_am_delivery.domain.user.entity.User;
import com.sparta.i_am_delivery.order.enums.OrderStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "orders")
@NoArgsConstructor
public class Order extends TimeStamped {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // 사용자
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  // 가게
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "store_id", nullable = false)
  private Store store;

  // 메뉴
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "menu_id", nullable = false)
  private Menu menu;

  // 수량
  @Column(nullable = false)
  private Integer quantity;

  // 총 가격
  @Column(nullable = false)
  private Long totalPrice;

  // 주문 상태
  @Enumerated(EnumType.STRING)
  private OrderStatus status;

  @Builder
  public Order(User user, Store store, Menu menu, Integer quantity, Long totalPrice,
      OrderStatus status) {
    this.user = user;
    this.store = store;
    this.menu = menu;
    this.quantity = quantity;
    this.totalPrice = totalPrice;
    this.status = status;
  }

  // 주문 상태 업데이트 메서드
  public void updateStatus(OrderStatus newStatus) {
    this.status = newStatus;
  }
}
