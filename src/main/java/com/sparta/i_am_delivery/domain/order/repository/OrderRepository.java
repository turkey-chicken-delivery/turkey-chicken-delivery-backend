package com.sparta.i_am_delivery.domain.order.repository;

import com.sparta.i_am_delivery.domain.order.entity.Order;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

  Optional<Order> findById(Long orderId);
}
