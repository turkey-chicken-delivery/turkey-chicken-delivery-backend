package com.sparta.i_am_delivery.domain.order.repository;

import com.sparta.i_am_delivery.domain.order.entity.Order;
import com.sparta.i_am_delivery.order.enums.OrderStatus;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Long> {

  Optional<Order> findById(Long orderId);

  Optional<Order> findByIdAndStatus(Long orderId, OrderStatus status);

  Page<Order> findAllByUserId(@Param("userId") Long userId, Pageable pageable);

  void deleteByStoreId(Long storeId);
  Optional<Order> findByIdAndStoreId(Long orderId, Long storeId);
}
