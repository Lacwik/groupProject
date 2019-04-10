package com.wfiis.CalculatorCO2.user.metadata.repository;

import com.wfiis.CalculatorCO2.user.metadata.entity.UserCompanyJob;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCompanyJobRepository extends JpaRepository<UserCompanyJob, Long> {
    void deleteAllByUserId(Long userId);
}
