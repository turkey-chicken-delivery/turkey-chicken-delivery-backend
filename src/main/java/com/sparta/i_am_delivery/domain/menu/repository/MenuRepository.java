package com.sparta.i_am_delivery.domain.menu.repository;

import com.sparta.i_am_delivery.domain.menu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
  void deleteByStoreId(Long storeId);

  List<Menu> findAllByStoreId(Long storeId);
}
