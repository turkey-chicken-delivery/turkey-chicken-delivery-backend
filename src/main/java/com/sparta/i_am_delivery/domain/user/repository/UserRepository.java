package com.sparta.i_am_delivery.domain.user.repository;

import com.sparta.i_am_delivery.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailOrName(String email, String name);
}
