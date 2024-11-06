package com.sparta.i_am_delivery.domain.order.repository;

import com.sparta.i_am_delivery.domain.order.entity.Order;
import com.sparta.i_am_delivery.order.enums.OrderStatus;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Long> {

  Optional<Order> findById(Long orderId);

  Optional<Order> findByIdAndStatus(Long orderId, OrderStatus status);

  Optional<Order> findByIdAndStoreId(Long orderId, Long storeId);

  @Modifying
  @Query("UPDATE Order o SET o.deleted = true WHERE o.id = :orderId")
  void softDelete(Long orderId);

  @Modifying
  @Query("DELETE FROM Order o WHERE o.store.id = :storeId")
  void deleteByStoreId(@Param("storeId") Long storeId);
}
