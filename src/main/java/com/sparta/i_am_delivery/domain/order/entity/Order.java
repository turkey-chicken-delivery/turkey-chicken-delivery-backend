package com.sparta.i_am_delivery.domain.order.entity;

import com.sparta.i_am_delivery.common.entity.TimeStamped;
import com.sparta.i_am_delivery.domain.menu.entity.Menu;
import com.sparta.i_am_delivery.domain.review.entity.Review;
import com.sparta.i_am_delivery.domain.store.entity.Store;
import com.sparta.i_am_delivery.domain.user.entity.User;
import com.sparta.i_am_delivery.domain.comment.entity.Comment;
import com.sparta.i_am_delivery.order.enums.OrderStatus;
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

    // 리뷰: 주문 하나에 리뷰 하나만 연결
    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = "review_id")
    private Review review;

    // 댓글: 리뷰 하나에 댓글 하나만 연결
    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    // 수량
    @Column(nullable = false)
    private Integer quantity;

    // 총 가격
    @Column(nullable = false)
    private Long totalPrice;

    // 주문 상태
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

//    @Column(nullable = false)
//    private OrderStatus status;

    @Builder
    public Order(User user, Store store, Menu menu, Integer quantity, Long totalPrice, OrderStatus status) {
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
