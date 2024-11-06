package com.sparta.i_am_delivery.domain.order.repository;

import com.sparta.i_am_delivery.domain.order.entity.Order;
import com.sparta.i_am_delivery.order.enums.OrderStatus;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

  Optional<Order> findById(Long orderId);

  Optional<Order> findByIdAndStatus(Long orderId, OrderStatus status);

  Optional<Order> findByIdAndStoreId(Long orderId, Long storeId);
}
