package com.sparta.i_am_delivery.domain.store.repository;

import com.sparta.i_am_delivery.domain.store.entity.Store;
import java.util.Optional;

import com.sparta.i_am_delivery.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {

  Optional<Store> findById(Long storeId);

  int countByOwner(User user);

  boolean existsByOwnerIdAndDeletedAtIsNull(Long id);
}
