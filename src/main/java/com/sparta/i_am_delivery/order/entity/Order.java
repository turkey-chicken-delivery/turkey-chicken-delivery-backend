package com.sparta.i_am_delivery.order.entity;

import com.sparta.i_am_delivery.common.entity.TimeStamped;
import com.sparta.i_am_delivery.order.entity.enums.OrderStatus;
import com.sparta.i_am_delivery.user.entity.User;
import com.sparta.i_am_delivery.store.entity.Store;
import jakarta.persistence.*;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    private Double totalPrice;

    private Double quantity;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;


    @Builder
    public Order(User user, Store store, Double totalPrice) {
        this.user = user;
        this.store = store;
        this.totalPrice = totalPrice;
    }
}
