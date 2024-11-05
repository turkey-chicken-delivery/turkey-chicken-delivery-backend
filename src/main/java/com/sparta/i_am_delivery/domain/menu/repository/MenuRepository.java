package com.sparta.i_am_delivery.domain.menu.repository;

import com.sparta.i_am_delivery.domain.menu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
  
}
