package com.wfiis.CalculatorCO2.request.metadata.repository;

import com.wfiis.CalculatorCO2.request.metadata.entity.UserRegisterRequestView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRegisterRequestsViewRepository extends JpaRepository<UserRegisterRequestView, Long> {
}
