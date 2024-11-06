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
  @Query("SELECT r FROM Review r JOIN FETCH r.user WHERE r.id = :storeId AND r.user.id = :userId")
  Optional<Store> findByIdAndUserId(@Param("storeId") Long reviewId, @Param("userId") Long userId);

  int countByOwner(User user);

  boolean existsByOwnerIdAndDeletedAtIsNull(Long id);
}
