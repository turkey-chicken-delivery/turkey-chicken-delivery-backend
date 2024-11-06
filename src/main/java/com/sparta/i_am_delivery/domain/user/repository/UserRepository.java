package com.sparta.i_am_delivery.domain.user.repository;

import com.sparta.i_am_delivery.domain.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
  // 삭제된 유저의 email 이름을 조회할 수 있도록 쿼리 작성
  @Query(value = "SELECT * FROM users WHERE (email = :email OR name = :name)", nativeQuery = true)
  Optional<User> findByEmailOrNameIncludingDeleted(
      @Param("email") String email, @Param("name") String name);

  Optional<User> findByEmail(String email);

  boolean existsByName(String name);
}
