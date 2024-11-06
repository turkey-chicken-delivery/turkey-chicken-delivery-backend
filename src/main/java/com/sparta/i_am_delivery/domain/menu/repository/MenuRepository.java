package com.sparta.i_am_delivery.domain.menu.repository;

import com.sparta.i_am_delivery.domain.menu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {

  @Query("SELECT m FROM Menu m WHERE m.id = :id")
  Menu findByIdIncludeDeleted(@Param("id") Long id);

  void deleteByStoreId(Long storeId);

  List<Menu> findAllByStoreId(Long storeId);
}
