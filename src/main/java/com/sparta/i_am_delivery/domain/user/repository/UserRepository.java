package com.sparta.i_am_delivery.domain.user.repository;

import com.sparta.i_am_delivery.domain.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailOrName(String email, String name);

    Optional<User> findByEmail(String email);

    boolean existsByName(String name);
}
