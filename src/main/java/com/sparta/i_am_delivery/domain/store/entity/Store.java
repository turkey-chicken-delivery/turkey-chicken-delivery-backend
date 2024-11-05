package com.sparta.i_am_delivery.domain.store.entity;

import com.sparta.i_am_delivery.common.entity.TimeStamped;
import com.sparta.i_am_delivery.domain.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "stores")
@NoArgsConstructor
public class Store extends TimeStamped {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User owner;

  @Column(nullable = false)
  private String name;

  private String openTime;
  private String closeTime;
  private Double minimumPrice;

  @Builder
  public Store(User owner, String name, String openTime, String closeTime, Double minimumPrice) {
    this.owner = owner;
    this.name = name;
    this.openTime = openTime;
    this.closeTime = closeTime;
    this.minimumPrice = minimumPrice;
  }
}
