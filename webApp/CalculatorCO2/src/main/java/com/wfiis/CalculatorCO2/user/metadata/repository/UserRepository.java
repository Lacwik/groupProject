package com.wfiis.CalculatorCO2.user.metadata.repository;

import com.wfiis.CalculatorCO2.user.metadata.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
