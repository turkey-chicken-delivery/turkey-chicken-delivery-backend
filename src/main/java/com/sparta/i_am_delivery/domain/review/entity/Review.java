package com.sparta.i_am_delivery.domain.review.entity;

import com.sparta.i_am_delivery.common.entity.TimeStamped;
import com.sparta.i_am_delivery.common.exception.CustomException;
import com.sparta.i_am_delivery.common.exception.ErrorCode;
import com.sparta.i_am_delivery.domain.comment.entity.Comment;
import com.sparta.i_am_delivery.domain.order.entity.Order;
import com.sparta.i_am_delivery.domain.store.entity.Store;
import com.sparta.i_am_delivery.domain.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "reviews")
@NoArgsConstructor
public class Review extends TimeStamped {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "store_id", nullable = false)
  private Store store;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id", nullable = false)
  private Order order;

  @Column(nullable = false)
  private String content;

  private Long star;

  // 리뷰에 달린 댓글을 일대일 관계로 설정
  @OneToOne(mappedBy = "review", fetch = FetchType.LAZY)
  private Comment comment;

  public void createReview(User user, Store store, Order order, String content, Long star) {
    if (star < 1 || star > 5) {
      throw new CustomException(ErrorCode.INVALID_STAR_RATING);
    }
    // 가게의 사장님 ID와 현재 사용자 ID를 비교
    if (store.getOwner().getId().equals(user.getId())) {
      throw new CustomException(ErrorCode.CANNOT_REVIEW_OWN_STORE);
    }
    this.user = user;
    this.store = store;
    this.order = order;
    this.content = content;
    this.star = star;
  }

  public void updateReview(String content, Long star) {
    this.content = content;
    this.star = star;
  }
}
