package com.sparta.i_am_delivery.domain.store.repository;

import com.sparta.i_am_delivery.domain.store.entity.Store;
import com.sparta.i_am_delivery.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {


  int countByOwnerAndDeletedAtIsNull(User user);
}