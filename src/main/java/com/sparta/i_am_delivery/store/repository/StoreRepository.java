package com.sparta.i_am_delivery.store.repository;

import com.sparta.i_am_delivery.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store,Long> {


}
