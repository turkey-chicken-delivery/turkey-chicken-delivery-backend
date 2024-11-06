package com.sparta.i_am_delivery.domain.store.repository;

import com.sparta.i_am_delivery.domain.store.entity.Store;
import com.sparta.i_am_delivery.domain.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StoreRepository extends JpaRepository<Store, Long> {

  Optional<Store> findById(Long storeId);

  // JOIN FETCH를 사용해서 스토어 ID와 사용자 ID로 스토어를 조회하여 해당 스토어가 특정 사용자에 의해 개설된 것인지 확인
  @Query("SELECT s FROM Store s JOIN FETCH s.owner WHERE s.id = :storeId AND s.owner.id = :userId")
  Optional<Store> findByIdAndUserId(@Param("storeId") Long storeId, @Param("userId") Long userId);

  boolean existsByOwnerIdAndDeletedAtIsNull(Long id);
  int countByOwnerAndDeletedAtIsNull(User user);
}
